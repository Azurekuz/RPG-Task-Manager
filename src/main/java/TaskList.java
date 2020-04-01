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
        taskList.remove(taskList.get(id));
    }

    public void editTask(int id, Task updatedTask){
        taskList.set(id, updatedTask);
    }

    public int findTask(String title){
        for(int task = 0; task < taskList.size(); task++){
            if(taskList.get(task).title.equals(title)){
                return task;
            }
        }
        return -1;
    }

    public Task getTask(int id){
        return taskList.get(id);
    }

    public int getSize(){return taskList.size();}
}
