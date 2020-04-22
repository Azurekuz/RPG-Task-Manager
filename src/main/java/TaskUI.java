import org.codehaus.stax2.ri.evt.NamespaceEventImpl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class TaskUI {
    public TaskManager taskManager;

    public void commandHandler(){
        String title = "", desc="", newTitle="", answer="";
        int quality=0, timeLimit=0, type=0, progress=0;
        double completionQuality=0, xp=0;
        LocalDateTime currentTime;

        try {
            load();
        } catch (IOException e) {
            System.out.println("[ERROR-LOAD FAILED:][ " + e.getMessage() + " ]");
            System.out.println("[NOTICE][ Creating blank task manager... ]");
            taskManager = new TaskManager(true);
        }

        Scanner input = new Scanner(System.in);
        System.out.println("***[STARTING TASK INTERFACE]***");
        System.out.println("[TIME]["+ taskManager.getStartTime() +"]\n");
        String userStr = "";
        String failedTasks;
        String numOnlyCheck = "0123456789";
        while (!(userStr.equals("quit"))){
            currentTime = LocalDateTime.now();
            boolean byID = true;
            try {
                failedTasks = taskManager.checkTimedTasks(currentTime);
            } catch (NonExistentTaskException | DuplicateTaskException e) {
                System.out.println("[ERROR][ " + e.getMessage() + "]");
                failedTasks="Some tasks may have failed but were unsuccessfully handled. Please review your current and failed tasks.";
            }
            if (!failedTasks.equals("[NOTICE][ No tasks failed.]")){
                System.out.println("[ATTENTION][ You have failed some of your selected tasks because you went over the time limit. ]");
                System.out.println(failedTasks);
                System.out.println("\n");
            }
            System.out.println("[?][ Enter your command or 'help' to see a list of commands. ]");
            System.out.println("--------------------");
            System.out.print("[COMMAND][> ");
            try {
                userStr = input.nextLine();
            }catch(IllegalArgumentException e){
                System.out.println("[ERROR][Invalid input entered!]");
            }
            System.out.println();

            switch (userStr.toLowerCase()) {
                case "help":
                    System.out.println("***[AVAILABLE COMMANDS]***");
                    System.out.println("['help'     : Displays this ]");
                    System.out.println("['quit'     : Stops the program ]");
                    System.out.println("['rpg'      : STARTS THE RPG GAME INTERFACE. You cannot access tasks from the game. ]");
                    System.out.println("['select'   : Select a task. Shows available tasks and" +
                            "\n prompts you for the title of the one you're picking ]");
                    System.out.println("['complete' : Complete a task. You will be prompted for the task's title. ]");
                    System.out.println("['progress' : Add progress to your main task. You will be prompted for a number from 1-100. ]");
                    System.out.println("['compmain' : Complete your main task. It must be at 100%. ]");
                    System.out.println("['stop'     : Stop working on a task. You lose all progress. Prompted for title. ]");
                    System.out.println("['stopmain' : Stop your main task. You lose all progress. ]");
                    System.out.println("['custom'   : Add a custom task. Several prompts for relevant info. ]");
                    System.out.println("['edit'     : Edit a task. Prompted for it's title, then for things you want to change. ]");
                    System.out.println("['viewdef'  : View all default tasks. ]");
                    System.out.println("['viewcust' : View all the custom tasks you've made. ]");
                    System.out.println("['viewcomp' : View all tasks you've completed. ]");
                    System.out.println("['viewcur'  : View all tasks you have selected currently. ]");
                    System.out.println("['viewday'  : View all your current daily tasks. ]");
                    System.out.println("['viewfail' : View all task you've failed. ]");
                    System.out.println("['viewmain' : View your current main task and it's information. ]");
                    System.out.println("['save'     : Manually save everything. ]");
                    break;

                case "rpg":
                    System.out.println("[NOTICE][ Starting RPG interface... ]");
                    taskManager.startGame();
                    System.out.println("***[RETURNED TO TASK INTERFACE]***");
                    break;

                case "complete":
                    System.out.println("[?][Which task would you like to complete? ]");
                    System.out.print("[TASK][> ");
                    try{
                        title = input.nextLine();
                    }catch(IllegalArgumentException e){
                        System.out.println("[ERROR][Invalid input entered!]");
                    }
                    System.out.println("[?][ How well did you do? (Enter a number from 0.1 to 1) ]");
                    System.out.print("[QUALITY][> ");
                    try {
                        completionQuality =  Double.parseDouble(input.nextLine());
                    } catch(IllegalArgumentException e){
                        System.out.println("[ERROR][ " + e.getMessage() + " ]"); break;
                    }
                    try {
                        for(int curChar = 0; curChar < title.length(); curChar++){
                            if(numOnlyCheck.indexOf(title.charAt(curChar)) == -1){
                                byID = false;
                                break;
                            }
                        }
                        if(byID){

                            xp = taskManager.complete(Integer.parseInt(title),completionQuality);
                        }else {
                            xp = taskManager.complete(title,completionQuality);
                        }
                        System.out.println("[SUCCESS][ Task completed! ]");
                        System.out.println("[EXP][ "+xp+" experience gained.]");
                    }catch(NonExistentTaskException e){
                        System.out.println("[ERROR][ " + e.getMessage() + " ]");
                    }catch(IllegalArgumentException e){
                        System.out.println("[ERROR][ Invalid input entered! ]");
                    }
                    break;

                case "progress":
                    if(taskManager.getMainTask().toString().equals("Empty task object.")) {
                        System.out.println("[NOTICE][ Please select a main task first. ]"); break;
                    }
                    System.out.println("[?][ How much progress would you like to add to your main task? ]");
                    System.out.print("[PROGRESS][> ");
                    if (input.hasNextInt()){
                        progress=input.nextInt();
                        input.nextLine(); //prevents reading user's newline as an unrecognized cmd
                        try {
                            taskManager.incMainProgress(progress);
                        } catch(IllegalArgumentException e){
                            System.out.println("[ERROR][ Please only enter a number from 1-100. ]"); break;
                        } catch (NonExistentTaskException e){
                            System.out.println("[ERROR][ " + e.getMessage() + " ]"); break;
                        }
                        System.out.println("[NOTICE][ Progress added! ]");
                    }
                    else {
                        System.out.println("[ERROR][ Please only enter a whole number. ]");
                    }
                    break;

                case "compmain":
                    System.out.println("[NOTICE][ Completing main task... ]");
                    System.out.println(taskManager.completeMain());
                    break;

                case "test_nextday":
                    taskManager.populateDailyTasks();
                    break;

                case "select":
                    System.out.println("***[ Available Custom Tasks ]***");
                    System.out.println(taskManager.viewCustomTasks());
                    System.out.println("***[ Available Default Tasks ]***");
                    System.out.println(taskManager.viewDefaultTasks());
                    System.out.println("[?][ Which task would you like to select & start? ]");
                    System.out.print("[TASK][> ");
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
                        System.out.println("[NOTICE][ Task started! ]");
                    }catch(NonExistentTaskException e){
                        System.out.println("[ERROR][ " + e.getMessage() + " ]");
                    }
                    break;

                case "stop":
                    System.out.println("[?][ Which task would you like to stop? ]");
                    System.out.print("[STOP TASK][> ");
                    try {
                        title = input.nextLine();
                    }catch (IllegalArgumentException e){
                        System.out.println("[ERROR][ Invalid input entered! ]");
                    }
                    try {
                        for(int curChar = 0; curChar < title.length(); curChar++){
                            if(numOnlyCheck.indexOf(title.charAt(curChar)) == -1){
                                byID = false;
                                break;
                            }
                        }
                        if(byID) {
                            taskManager.stopTask(Integer.parseInt(title));
                            System.out.println("[NOTICE][ Task stopped! ]");
                        }else{
                            taskManager.stopTask(title);
                            System.out.println("[NOTICE][ Task stopped! ]");
                        }
                    }catch(NonExistentTaskException e){
                        System.out.println("[ERROR][ " + e.getMessage() + " ]");
                    }
                    break;

                case "stopmain":
                    System.out.println("[ALERT][ Are you sure you want to stop your main task? You will lose all progress! (y/n) ]");
                    try {
                        answer = input.nextLine();
                    }catch(IllegalArgumentException e){
                        System.out.println("[ERROR][ Invalid input entered! ]");
                    }
                    if(answer.toLowerCase().equals("y") || answer.toLowerCase().equals("yes")) {
                        System.out.println(taskManager.stopMainTask());
                    }
                    else {
                        System.out.println("[NOTICE][ Okay, your main task is unchanged. ]");
                    }
                    break;

                case "addcust": //Kept trying to do this despite knowing the custom cmd so might as well add it as an alias
                case "custom":
                    try {
                        System.out.println("***Making a custom task.***");
                        System.out.println("[Enter task title][> ");
                        title = input.nextLine();
                        System.out.println("[Enter task description][> ");
                        desc = input.nextLine();
                        System.out.println("[Enter task base quality (integer)][> ");
                        quality = input.nextInt();
                        System.out.println("[Enter task time limit (in minutes, 0 for not timed][> ");
                        timeLimit = input.nextInt();
                        System.out.println("[Enter task type (0 for default, 1 for main, 2 for daily, 3 for weekly][> ");
                        type = input.nextInt();
                        input.nextLine(); //prevents reading user's newline as an unrecognized cmd
                    }catch(IllegalArgumentException e){
                        System.out.println("[ERROR][ Invalid input entered! ]");
                    }
                    taskManager.addCustomTask(title, desc, quality, timeLimit, type);
                    System.out.println("[SUCCESS][ Task created!]");
                    break;

                case "edit":
                    System.out.println("***Editing task.***");
                    System.out.println("[?][ Which task would you like to edit? ]");
                    System.out.print("[SELECT TASK][> ");
                    title = input.nextLine();
                    for(int curChar = 0; curChar < title.length(); curChar++){
                        if(numOnlyCheck.indexOf(title.charAt(curChar)) == -1){
                            byID = false;
                            break;
                        }
                    }
                    try {
                        System.out.println("[Enter new title][> ");
                        newTitle = input.nextLine();
                        System.out.println("[Enter new description][> ");
                        desc = input.nextLine();
                        System.out.println("[Enter new Base Quality (integer)][> ");
                        quality = input.nextInt();
                        System.out.println("[Enter new time limit (in  minutes, 0 for not timed)][> ");
                        timeLimit = input.nextInt();
                        System.out.println("[Enter new type (0 for default, 1 for main, 2 for daily, 3 for weekly][> ");
                        type = input.nextInt();
                        input.nextLine(); //prevents reading user's newline as an unrecognized cmd
                    }catch(IllegalArgumentException e){
                        System.out.println("[ERROR][ Invalid input entered! ]");
                    }
                    try {
                        if(byID){
                            taskManager.editTask(Integer.parseInt(title), newTitle, desc, quality, timeLimit, type, 0); //editListPrompt(input)
                        }else {
                            taskManager.editTask(title, newTitle, desc, quality, timeLimit, type);
                        }
                        System.out.println("[SUCCESS][ Task edited! ]");
                    }catch(NonExistentTaskException e){
                        System.out.println("[ERROR][ " + e.getMessage() + " ]");
                    }
                    break;

                case "viewdef":
                    System.out.println("***[ DEFAULT TASK POOL ]***");
                    System.out.println(taskManager.viewDefaultTasks());
                    break;

                case "viewcust":
                    System.out.println("***[ YOUR CUSTOM TASKS ]***");
                    System.out.println(taskManager.viewCustomTasks());
                    break;

                case "viewday":
                    System.out.println("***[ YOUR DAILY TASKS ]***");
                    System.out.println(taskManager.viewDailyTasks());
                    break;

                case "viewcomp":
                    System.out.println("***[ YOUR COMPLETED TASKS ]***");
                    System.out.println(taskManager.viewCompletedTasks());
                    break;

                case "viewcur":
                    System.out.println("***[ YOUR ACTIVE TASKS ]***");
                    System.out.println(taskManager.viewCurrentTasks());
                    break;
                case "viewfail":
                    System.out.println("***[ YOUR FAILED TASKS ]***");
                    System.out.println(taskManager.viewFailedTasks());
                    break;
                case "viewmain":
                    System.out.println("***[ YOUR MAIN TASK ]***");
                    if (taskManager.getMainTask().toString().equals("Empty task object.")){
                        System.out.println("[NOTICE][ No main task selected. ]");
                    }else {
                        System.out.println(taskManager.getMainTask().toString());
                    }
                    break;
                case "save":
                    System.out.println("***[ SAVING... ]***");
                    try {
                        save();
                    } catch (IOException e) {
                        System.out.println("[ERROR-SAVE FAILED:][ " + e.getMessage() + " ]");
                    }
                    break;
                case "quit": break; //avoids triggering default case
                default:
                    System.out.println("[ERROR][ Command not recognized. ]");
                    break;
            }
        }

        System.out.println("[NOTICE][ Saving and quitting... ]");
        System.out.println("[ GOOD-BYE! ]");
        try {
            save();
        } catch (IOException e) {
            System.out.println("[ERROR-SAVE FAILED:][ " + e.getMessage() + " ]");
        }
    }

    public void selectListPrompt(int id, Scanner input) throws NonExistentTaskException{
        try {
            /*System.out.println("[?][Which tasklist are you selecting from? ]");
            System.out.println("[1] Default");
            System.out.println("[2] Custom");
            System.out.print("[CHOICE][> ");
            int choice = input.nextInt();
            while (choice < 1 || choice > 2) {
                System.out.println("[ERROR][ Invalid choice entered! ]");
                choice = input.nextInt();
            }*/
            taskManager.selectTask(id, 1);
        }catch(NonExistentTaskException e){
            throw new NonExistentTaskException(e.getMessage());
        }
    }

    public int editListPrompt(Scanner input){
            System.out.println("[?][ Which tasklist are you editing from? ]");
            System.out.println("[1] Current");
            System.out.println("[2] Daily");
            System.out.println("[3] Default");
            System.out.println("[4] Custom");
            System.out.print("[CHOICE][> ");
            int choice = input.nextInt();
            while (choice < 1 || choice > 4) {
                System.out.println("[Error][ Invalid choice entered! ]");
                choice = input.nextInt();
            }
            input.nextLine();
            return choice-1;
    }

    public static void main(String[] args){
        TaskUI taskUI = new TaskUI();

        System.out.println("***[ Welcome to RPG Task Manager! ]***");
        taskUI.commandHandler();
    }
    public void save() throws IOException {
        JsonUtil.toJsonFile("src/resources/taskManager.json", taskManager);
    }
    public void load() throws IOException {
        taskManager = JsonUtil.fromJsonFile("src/resources/taskManager.json", TaskManager.class);
    }
}


