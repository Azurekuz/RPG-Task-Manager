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
        for(int task = 0; task < taskList.size(); task++){
            if(taskList.get(task).title.toLowerCase().equals(title.toLowerCase())){
                return task;
            }
        }
        return -1;
    }

    public Task getTask(int id) throws NonExistentTaskException{
        if(id >= taskList.size() || id < 0){
            throw new NonExistentTaskException("Nonexistent or Invalid Task Requested!");
        }
        return taskList.get(id);
    }

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
