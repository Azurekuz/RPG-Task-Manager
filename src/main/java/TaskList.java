import java.awt.*;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskList extends TaskManager{
    List TaskList = new ArrayList<Task>();
    List<Task> taskList = new ArrayList<Task>();


    public void addTask(int id, String title, String desc, int quality, int timeLimit, int type, boolean complete){
        Task newTask = new Task(id, title,desc, quality,timeLimit,type,complete);
        taskList.add(newTask);


    }

    public void removeTask(int id){
        int i=0;
        while(i<taskList.size()){
            if((taskList.get(i).getID())==id){
                taskList.remove(i);

            }
            else{
                i=i+1;
            }

        }



    }
    public void editTask(int id){
        Scanner editedTask = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter Taskedit");


        String Userinput=editedTask.nextLine();
        int i=0;
        while(i<taskList.size()){
            if((taskList.get(i).getID())==id){
                taskList.get(i).setTitle(Userinput);

            }
            else{
                i=i+1;
            }

        }

    }
    public void findTask(String title, int id){
        int i=0;

        if((taskList.get(i).getID())==id && taskList.get(i).getTitle()==title){
            System.out.println("Task ID: "+"(taskList.get(i).getID())"+ "Task title"+" taskList.get(i).getTitle()");

        }
        else{
            i=i+1;
        }

    }



}


//Git, please commit and push this project.

