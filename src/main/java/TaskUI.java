import java.util.Scanner;
public class TaskUI {
    public TaskManager taskManager;

    public void commandHandler(){
        taskManager = new TaskManager();
        //taskManager.load();

        Scanner input = new Scanner(System.in);
        String userStr = input.nextLine();
        while (!(userStr.equals("quit"))){
            System.out.println("Enter your command or 'help' to see a list of commands.");

            if (userStr.equals("help")){
                System.out.println("***AVAILABLE COMMANDS***");
                System.out.println("'help' : Displays this");
                System.out.println("'quit' : Stops the program");
                System.out.println();
            }
            //else if (){
            //
            //}
            else{
                System.out.println("***Command not recognized.***");
            }

            userStr = input.nextLine();
        }

        System.out.println("Saving and quitting...");
        //taskManager.save();
    }

    public static void main(String args[]){
        TaskUI taskUI = new TaskUI();

        System.out.println("Welcome to RPG Task Manager!");
        taskUI.commandHandler();
    }
}
