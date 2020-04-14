import com.fasterxml.jackson.annotation.JsonIgnore;

import java.awt.*;
import java.util.ArrayList;

public class TaskList{
    public ArrayList<Task> taskList;

    TaskList(){
        taskList = new ArrayList<Task>();
    }

    TaskList(ArrayList<Task> taskListIn) {
        taskList = taskListIn;
    }

    public void addTask(Task newTask){
        taskList.add(newTask);
    }
    public void addTask(String title, String desc, int quality, int timeLimit, int type){
        int id = this.getSize();
        Task newTask = new Task(id, title, desc, quality, timeLimit, type, false);
        taskList.add(newTask);
    }


    public void removeTask(int id) throws NonExistentTaskException{
        if(id > taskList.size() || id < 0){
            throw new NonExistentTaskException("Nonexistent or Invalid Task Requested!");
        }
        taskList.remove(taskList.get(id));
    }

    public void editTask(int id, Task updatedTask) throws NonExistentTaskException{
        if(id > taskList.size() || id < 0){
            throw new NonExistentTaskException("Nonexistent or Invalid Task Requested!");
        }
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
    public Task getTask(String title) throws NonExistentTaskException{
        int index = findTask(title);
        if(index >= taskList.size() || index < 0){
            throw new NonExistentTaskException("Nonexistent or Invalid Task Requested!");
        }
        return taskList.get(index);
    }

    public Task getTask(int id) throws NonExistentTaskException{
        int index = findTask(id);
        if(index >= taskList.size() || index < 0){
            throw new NonExistentTaskException("Nonexistent or Invalid Task Requested!");
        }
        return taskList.get(index);
    }

    public Task getTaskAt(int index) { return taskList.get(index); }

    @JsonIgnore
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
