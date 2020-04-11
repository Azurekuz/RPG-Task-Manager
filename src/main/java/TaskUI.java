import java.util.Date;
import java.util.Scanner;

public class TaskUI {
    public TaskManager taskManager;

    public void commandHandler(){
        taskManager = new TaskManager();
        String title, desc, newTitle, answer;
        int quality, timeLimit, type, progress;
        Date currentTime;

        taskManager.load();

        Scanner input = new Scanner(System.in);
        System.out.println("***STARTING TASK INTERFACE***");
        String userStr = "";
        String failedTasks;

        while (!(userStr.equals("quit"))){
            System.out.println("Enter your command or 'help' to see a list of commands.");
            userStr = input.nextLine();
            currentTime = new Date();
            failedTasks = taskManager.checkTimedTasks(currentTime);

            if (!failedTasks.equals("No tasks failed.")){
                System.out.println("*ATTENTION:* You have failed some of your selected tasks because you went over the time limit.");
                System.out.println(failedTasks);
                System.out.println("\n");
            }

            switch (userStr) {
                case "help":
                    System.out.println("***AVAILABLE COMMANDS***");
                    System.out.println("'help'     : Displays this");
                    System.out.println("'quit'     : Stops the program");
                    System.out.println("'rpg'      : STARTS THE RPG GAME INTERFACE. You cannot access tasks from the game.");
                    System.out.println("'select'   : Select a task. Shows available tasks and" +
                            "\n prompts you for the title of the one you're picking");
                    System.out.println("'complete' : Complete a task. You will be prompted for the task's title.");
                    System.out.println("'progress' : Add progress to your main task. You will be prompted for a number from 1-100.");
                    System.out.println("'compmain' : Complete your main task. It must be at 100%.");
                    System.out.println("'stop'     : Stop working on a task. You lose all progress. Prompted for title.");
                    System.out.println("'stopmain' : Stop your main task. You lose all progress.");
                    System.out.println("'custom'   : Add a custom task. Several prompts for relevant info.");
                    System.out.println("'edit'     : Edit a task. Prompted for it's title, then for things you want to change.");
                    System.out.println("'viewdef'  : View all default tasks.");
                    System.out.println("'viewcust' : View all the custom tasks you've made.");
                    System.out.println("'viewcomp' : View all tasks you've completed.");
                    System.out.println("'viewcur'  : View all tasks you have selected currently.");
                    System.out.println("'viewfail' : View all task you've failed.");
                    System.out.println("'viewmain' : View your current main task and it's information.");
                    break;

                case "rpg":
                    System.out.println("Starting RPG interface...");
                    taskManager.startGame();
                    break;

                case "complete":
                    System.out.println("Which task would you like to complete?: ");
                    title = input.nextLine();
                    taskManager.complete(title);
                    System.out.println("Task completed!");
                    break;

                case "progress":
                    if(taskManager.getMainTask().toString().equals("Empty task object.")) {
                        System.out.println("Please select a main task first."); break;
                    }
                    System.out.println("How much progress would you like to add to your main task?: ");
                    if (input.hasNextInt()){
                        progress=input.nextInt();
                        input.nextLine(); //prevents reading user's newline as an unrecognized cmd
                        try {
                            taskManager.incMainProgress(progress);
                        } catch(IllegalArgumentException e){
                            System.out.println("Please only enter a number from 1-100."); break;
                        }
                        System.out.println("Progress added!");
                    }
                    else {
                        System.out.println("Please only enter a whole number.");
                    }
                    break;

                case "compmain":
                    System.out.println("Completing main task...");
                    System.out.println(taskManager.completeMain());
                    break;

                case "select":
                    System.out.println("Available Custom Tasks:");
                    System.out.println(taskManager.viewCustomTasks());
                    System.out.println("Available Default Tasks:");
                    System.out.println(taskManager.viewDefaultTasks());
                    System.out.println("Which task would you like to select & start?: ");
                    title = input.nextLine();
                    System.out.println(taskManager.selectTask(title));
                    break;

                case "stop":
                    System.out.println("Which task would you like to stop?: ");
                    title = input.nextLine();
                    taskManager.stopTask(title);
                    System.out.println("Task stopped.");
                    break;

                case "stopmain":
                    System.out.println("Are you sure you want to stop your main task? You lost all progress. (y/n):");
                    answer = input.nextLine();
                    if(answer.toLowerCase().equals("y") || answer.toLowerCase().equals("yes")) {
                        System.out.println(taskManager.stopMainTask());
                    }
                    else {
                        System.out.println("Okay, your main task is unchanged.");
                    }
                    break;

                case "custom":
                    System.out.println("***Making a custom task.***");
                    System.out.println("Enter task title:"); title = input.nextLine();
                    System.out.println("Enter task descriptipn:"); desc = input.nextLine();
                    System.out.println("Enter task quality (integer):"); quality = input.nextInt();
                    System.out.println("Enter task time limit (in minutes, 0 for not timed):"); timeLimit = input.nextInt();
                    System.out.println("Enter task type (0 for default, 1 for main, 2 for daily, 3 for weekly:"); type = input.nextInt();
                    input.nextLine(); //prevents reading user's newline as an unrecognized cmd
                    taskManager.addCustomTask(title, desc, quality, timeLimit, type);
                    System.out.println("Task created!");
                    break;

                case "edit":
                    System.out.println("***Editing task.***");
                    System.out.println("Which task would you like to edit?: ");
                    title = input.nextLine();
                    System.out.println("Enter new title:"); newTitle = input.nextLine();
                    System.out.println("Enter new descriptipn:"); desc = input.nextLine();
                    System.out.println("Enter new quality (integer):"); quality = input.nextInt();
                    System.out.println("Enter new time limit (in  minutes, 0 for not timed):"); timeLimit = input.nextInt();
                    System.out.println("Enter new type (0 for default, 1 for main, 2 for daily, 3 for weekly:"); type = input.nextInt();
                    input.nextLine(); //prevents reading user's newline as an unrecognized cmd
                    taskManager.editTask(title, newTitle, desc, quality, timeLimit, type);
                    System.out.println("Task edited!");
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
                case "viewfail":
                    System.out.println("***Your failed tasks:***");
                    System.out.println(taskManager.viewFailedTasks());
                case "viewmain":
                    System.out.println("***Your main task:***");
                    if (taskManager.getMainTask().toString().equals("Empty task object.")){
                        System.out.println("No main task selected.");
                    }else {
                        System.out.println(taskManager.getMainTask().toString());
                    }
                    break;

                default:
                    System.out.println("***Command not recognized.***");
                    break;
            }
        }

        System.out.println("Saving and quitting...");
        taskManager.save();
    }

    public static void main(String[] args){
        TaskUI taskUI = new TaskUI();

        System.out.println("Welcome to RPG Task Manager!");
        taskUI.commandHandler();
    }
}
