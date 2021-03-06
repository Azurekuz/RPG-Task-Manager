import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

public class TaskList{
    public ArrayList<Task> taskList;

    TaskList(){
        taskList = new ArrayList<Task>();
    }

    TaskList(ArrayList<Task> taskListIn) { //used for JSON loading if needed
        taskList = taskListIn;
    }

    public void addTask(Task newTask) throws DuplicateObjectException {
        try {
            if(newTask.getTimeLimit() != 0){
                newTask.startTime();
            }
            taskList.add(checkDuplicate(newTask));
        }catch(DuplicateObjectException e){
            throw new DuplicateObjectException(e.getMessage());
        }
    }
  
    public void addTask(String title, String desc, int quality, int timeLimit, int type) throws DuplicateObjectException {
        try {
            int id = this.getSize();
            Task newTask = checkDuplicate(new Task(id, title, desc,quality, timeLimit, type, false));
            taskList.add(newTask);
        }catch(DuplicateObjectException e){
            throw new DuplicateObjectException(e.getMessage());
        }
    }

    public Task checkDuplicate(Task newTask) throws DuplicateObjectException {
        for(int taskID = 0; taskID < taskList.size(); taskID++){
            if(taskList.get(taskID).title.toLowerCase().equals(newTask.title.toLowerCase())){
                throw new DuplicateObjectException("You already have a task with this name!");
            }
        }
        return newTask;
    }

    public void removeTask(int id) throws NonExistentObjectException {
        int index = findTask(id);
        if(index >= taskList.size() || index < 0){
            throw new NonExistentObjectException("Nonexistent or Invalid Task Requested!");
        }
        taskList.remove(index);
    }

    public void removeTask(String title) throws NonExistentObjectException {
        int index = findTask(title);
        if(index >= taskList.size() || index < 0){
            throw new NonExistentObjectException("Nonexistent or Invalid Task Requested!");
        }
        taskList.remove(index);
    }

    public void editTask(int id, Task updatedTask) throws NonExistentObjectException {
        if(id > taskList.size() || id < 0){
            throw new NonExistentObjectException("Nonexistent or Invalid Task Requested!");
        }
        taskList.set(id, updatedTask);
    }

    public int findTask(String title){
        for(int i = 0; i < taskList.size(); i++){
            if(taskList.get(i).title.toLowerCase().equals(title.toLowerCase())){
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
    public Task getTask(String title) throws NonExistentObjectException {
        int index = findTask(title);
        if(index >= taskList.size() || index < 0){
            throw new NonExistentObjectException("Nonexistent or Invalid Task Requested!");
        }
        return taskList.get(index);
    }

    public Task getTask(int id) throws NonExistentObjectException {
        int index = findTask(id);
        if(index >= taskList.size() || index < 0){
            throw new NonExistentObjectException("Nonexistent or Invalid Task Requested!");
        }
        return taskList.get(index);
    }

    public Task getTaskAt(int index) { return taskList.get(index); }

    @JsonIgnore
    public int getSize(){return taskList.size();}

    public String toString(){
        String s = "{id: \t Title \t |\t Description \t |\t Base Quality \t |\t TimeLimit \t |\t Type \t |\t Complete \t |\t Completion Quality }\n";
        Task task;

        for (int i = 0; i < taskList.size(); i++){
            s = s.concat("[ID][" + taskList.get(i).id +"]: ");
            task = taskList.get(i);
            s = s.concat("[TITLE][" + task.getTitle() +"] [DESC]["+ task.getDesc() +"] [BASE]["+ task.getBaseQuality() +"] [TIME]["+ task.getTimeLimit() +"] [TYPE]["+ task.getTypeStr() +"] [COMPLETE]["+ task.displayComplete()+"] [QUALITY]["+ task.getCompletionQuality()+"]");
            s = s.concat("\n");

        }
        if (taskList.size() == 0){
            s = s.concat("*No tasks.*\n");
        }
        //s = s.concat("}");
        return s;

    }
}
