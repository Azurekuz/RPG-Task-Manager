import java.awt.*;
import java.util.ArrayList;

public class TaskList{
    public ArrayList<Task> taskList;

    TaskList(){
        taskList = new ArrayList<Task>();
    }


    public void addTask(Task newTask){
        taskList.add(newTask);
    }

    public void addTask(String title, String desc, int quality, int timeLimit, int type){
        int id = this.getSize();
        Task newTask = new Task(id, title, desc, quality, timeLimit, type, false);
        taskList.add(newTask);
    }

    public void removeTask(int id){
        Task task = getTask(id);
        taskList.remove(task);
    }

   // public void removeTask(String title){
       // int id = findTask(title); Task task = getTask(id);
        //taskList.remove(task);
    //}

    public void editTask(int id, Task updatedTask){
        taskList.set(id, updatedTask);
    }

    public int findTask(String title){
        for(int i = 0; i < taskList.size(); i++){
            if(taskList.get(i).title.equals(title)){
                return i; //returns index of task
            }
        }
        return -1;
    }

    public int findTask(int id){
        for(int i = 0; i < taskList.size(); i++){
            if(taskList.get(i).id == id){
                return i; //returns index of task
            }
        }
        return -1;
    }
    public Task getTask(int id){
        int index = findTask(id);
        return taskList.get(index);
    }

    public Task getTaskAt(int index) { return taskList.get(index); }

    public int getSize(){return taskList.size();}

    public String toString(){
        String s = "{id: Title | Description | Quality | TimeLimit | Type | Complete\n";
        Task task;

        for (int i = 0; i < taskList.size(); i++){
            s = s.concat(i +": ");
            task = taskList.get(i);
            s = s.concat(task.getTitle() +" "+ task.getDesc() +" "+ task.getQuality() +" "+ task.getTimeLimit() +" "+ task.getTypeStr() +" "+ task.isComplete());
            s = s.concat("\n");

        }
        if (taskList.size() == 0){
            s = s.concat("*No tasks.*\n");
        }
        s = s.concat("}");
        return s;

    }
}
