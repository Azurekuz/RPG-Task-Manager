import java.util.Scanner;
public class TaskUI {
    public TaskManager taskManager;

    public void commandHandler(){
        taskManager = new TaskManager();
        String title, desc, newTitle;
        int quality, timeLimit, type;

        taskManager.load();

        Scanner input = new Scanner(System.in);
        System.out.println("***STARTING TASK INTERFACE***");
        String userStr = "";
        String numOnlyCheck = "0123456789";
        boolean byID = true;
        while (!(userStr.equals("quit"))){
            System.out.println("Enter your command or 'help' to see a list of commands.");
            userStr = input.nextLine();
            switch (userStr.toLowerCase()) {
                case "help":
                    System.out.println("***AVAILABLE COMMANDS***");
                    System.out.println("'help'    : Displays this");
                    System.out.println("'quit'    : Stops the program");
                    System.out.println("'rpg'     : STARTS THE RPG GAME INTERFACE. You cannot access tasks from the game.");
                    System.out.println("'complete : Complete a task. You will be prompted for the task's title.");
                    System.out.println("'select   : Select a task. Shows available tasks and" +
                            "\n prompts you for the title of the one you're picking"); //TODO show available tasks
                    System.out.println("'stop     : Stop working on a task. You lose all progress. Prompted for title.");
                    System.out.println("'custom   : Add a custom task. Several prompts for relevant info.");
                    System.out.println("'edit     : Edit a task. Prompted for it's title, then for things you want to change.");
                    System.out.println("'viewdef  : View all default tasks.");
                    System.out.println("'viewcust : View all the custom tasks you've made.");
                    System.out.println("'viewcomp : View all tasks you've completed.");
                    System.out.println("'viewcur' : View all tasks you have selected currently.");
                    break;

                case "rpg":
                    System.out.println("Starting RPG interface...");
                    taskManager.startGame();
                    break;

                case "complete":
                    System.out.println("Which task would you like to complete?: ");
                    title = input.nextLine();
                    try {
                        for(int curChar = 0; curChar < title.length(); curChar++){
                            if(numOnlyCheck.indexOf(title.charAt(curChar)) == -1){
                                byID = false;
                                break;
                            }
                        }
                        if(byID){
                            taskManager.complete(Integer.parseInt(title));
                        }else {
                            taskManager.complete(title);
                        }
                        System.out.println("Task completed!");
                    }catch(NonExistentTaskException e){
                        System.out.println("[ERROR][ " + e.getMessage() + "]");
                    }
                    break;

                case "select":
                    System.out.println("Which task would you like to select & start?: ");
                    title = input.nextLine();
                    try {
                        for(int curChar = 0; curChar < title.length(); curChar++){
                            if(numOnlyCheck.indexOf(title.charAt(curChar)) == -1){
                                byID = false;
                                break;
                            }
                        }
                        if(byID){
                            selectListPrompt(Integer.parseInt(title), input);
                        }else {
                            taskManager.selectTask(title);
                        }
                        System.out.println("Task started!");
                    }catch(NonExistentTaskException e){
                        System.out.println("[ERROR][ " + e.getMessage() + "]");
                    }
                    break;

                case "stop":
                    System.out.println("Which task would you like to stop?: ");
                    title = input.nextLine();
                    try {
                        for(int curChar = 0; curChar < title.length(); curChar++){
                            if(numOnlyCheck.indexOf(title.charAt(curChar)) == -1){
                                byID = false;
                                break;
                            }
                        }
                        if(byID) {
                            taskManager.stopTask(Integer.parseInt(title));
                        }else{
                            taskManager.stopTask(title);
                        }
                        System.out.println("Task stopped!");
                    }catch(NonExistentTaskException e){
                        System.out.println("[ERROR][ " + e.getMessage() + "]");
                    }
                    break;

                case "custom":
                    System.out.println("***Making a custom task.***");
                    System.out.println("Enter task title:"); title = input.nextLine();
                    System.out.println("Enter task descriptipn:"); desc = input.nextLine();
                    System.out.println("Enter task quality (integer):"); quality = input.nextInt();
                    System.out.println("Enter task time limit (0 for not timed):"); timeLimit = input.nextInt();
                    System.out.println("Enter task type (0 for default, 1 for main, 2 for daily, 3 for weekly:"); type = input.nextInt();

                    taskManager.addCustomTask(title, desc, quality, timeLimit, type);
                    System.out.println("Task created!");
                    break;

                case "edit":
                    System.out.println("***Editing task.***");
                    System.out.println("Which task would you like to edit?: ");
                    title = input.nextLine();
                    for(int curChar = 0; curChar < title.length(); curChar++){
                        if(numOnlyCheck.indexOf(title.charAt(curChar)) == -1){
                            byID = false;
                            break;
                        }
                    }
                    System.out.println("Enter new title:"); newTitle = input.nextLine();
                    System.out.println("Enter new descriptipn:"); desc = input.nextLine();
                    System.out.println("Enter new quality (integer):"); quality = input.nextInt();
                    System.out.println("Enter new time limit (0 for not timed):"); timeLimit = input.nextInt();
                    System.out.println("Enter new type (0 for default, 1 for main, 2 for daily, 3 for weekly:"); type = input.nextInt();

                    try {
                        if(byID){
                            taskManager.editTask(Integer.parseInt(title), newTitle, desc, quality, timeLimit, type, editListPrompt(input));
                        }else {
                            taskManager.editTask(title, newTitle, desc, quality, timeLimit, type);
                        }
                        System.out.println("Task edited!");
                    }catch(NonExistentTaskException e){
                        System.out.println("[ERROR][ " + e.getMessage() + "]");
                    }
                    break;

                case "viewdef":
                    System.out.println("***Default task pool:***");
                    System.out.println(taskManager.viewDefaultTasks());
                    break;

                case "viewcust":
                    System.out.println("***Your custom tasks:***");
                    System.out.println(taskManager.viewCustomTasks());
                    break;

                case "viewcomp":
                    System.out.println("***Your completed tasks:***");
                    System.out.println(taskManager.viewCompletedTasks());
                    break;

                case "viewcur":
                    System.out.println("***Your currently active tasks:***");
                    System.out.println(taskManager.viewCurrentTasks());
                    break;

                default:
                    System.out.println("***Command not recognized.***");
                    break;
            }

            //userStr = input.nextLine();
        }

        System.out.println("Saving and quitting...");
        taskManager.save();
    }

    public void selectListPrompt(int id, Scanner input) throws NonExistentTaskException{
        try {
            System.out.println("Which tasklist are you selecting from?");
            System.out.println("[1] Default");
            System.out.println("[2] Custom");
            int choice = input.nextInt();
            while (choice != 1 && choice != 2) {
                System.out.println("[Error][ Invalid choice entered!]");
                choice = input.nextInt();
            }
            taskManager.selectTask(id, choice - 1);
        }catch(NonExistentTaskException e){
            throw new NonExistentTaskException(e.getMessage());
        }
    }

    public int editListPrompt(Scanner input){
            System.out.println("Which tasklist are you editing from?");
            System.out.println("[1] Current");
            System.out.println("[2] Default");
            System.out.println("[3] Custom");
            int choice = input.nextInt();
            while (choice != 1 && choice != 2 && choice != 3) {
                System.out.println("[Error][ Invalid choice entered!]");
                choice = input.nextInt();
            }
            return choice-1;
    }

    public static void main(String[] args){
        TaskUI taskUI = new TaskUI();

        System.out.println("Welcome to RPG Task Manager!");
        taskUI.commandHandler();
    }
}
