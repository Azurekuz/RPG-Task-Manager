import java.util.Date;
public class TaskManager {

    private TaskList defaultTaskList;
    private TaskList currentTaskList;
    private TaskList completedTaskList;
    private TaskList customTaskList;
    private TaskList failedTaskList;
    private Date startTime;
    //TODO tie in with User

  TaskManager(){
        defaultTaskList = new TaskList();
        currentTaskList = new TaskList();
        completedTaskList = new TaskList();
        customTaskList = new TaskList();
        failedTaskList = new TaskList();

        Task doDishes = new Task(0, "Do the Dishes", "Clean all your unwashed dishes.", 0, 0, 0, false);
        Task doLaundry = new Task(0, "Do your Laundry", "Clean your clothes.", 0, 0, 0, false);
        Task cleanRoom = new Task(0, "Clean your room", "Organize and dust off your room.", 0, 0, 0, false);
        Task flossTeeth = new Task(0, "Floss your teeth", "Floss under your gums too.", 0, 0, 0, false);

        defaultTaskList.addTask(doDishes);
        defaultTaskList.addTask(doLaundry);
        defaultTaskList.addTask(cleanRoom);
        defaultTaskList.addTask(flossTeeth);
        startTime = new Date();
    }
    public Task findCurrentTask(int id){
        return currentTaskList.getTask(id);
    }

    public void addCurrentTask(Task newTask){
        currentTaskList.addTask(newTask);
    }
    public void addCustomTask(String title, String desc, int quality, int timeLimit, int type){
        int id = customTaskList.getSize();
        Task newTask = new Task(id, title, desc, quality, timeLimit, type, false);
        customTaskList.addTask(newTask);
    }


    public void selectTask(String title){
      Task task;
      int id;
        if(defaultTaskList.findTask(title) != -1){
            id = defaultTaskList.findTask(title);
            task = defaultTaskList.getTask(id);
            task.startTime();
            currentTaskList.addTask(task);
        }
        else if(customTaskList.findTask(title) != -1){
            id = customTaskList.findTask(title);
            task = customTaskList.getTask(id);
            task.startTime();
            currentTaskList.addTask(task);
        }
        else{
            System.out.println("SELECTING TASK: task not found - not added to current tasks.");
        }
    }

    public void stopTask(String title){
        int id = currentTaskList.findTask(title);

        if (id != -1){
            currentTaskList.removeTask(id);
        }
        else{
            System.out.println("STOPPING TASK: task not found - not removed from current tasks.");
        }

    }


    public void editCurrentTask(int id, Task newTask){
        currentTaskList.editTask(id, newTask);
    }

    public void editTask(String title, String newTitle, String desc, int quality, int timeLimit, int type){
      int id;
      Task editedTask;
      if (currentTaskList.findTask(title) != -1){
          id = currentTaskList.findTask(title);
          editedTask = currentTaskList.getTask(id);
          editedTask.setTitle(newTitle);
          editedTask.setDesc(desc);
          editedTask.setQuality(quality);
          editedTask.setTimeLimit(timeLimit);
          editedTask.setType(type);
      }
      else if(defaultTaskList.findTask(title) != -1){
          id = defaultTaskList.findTask(title);
          editedTask = defaultTaskList.getTask(id);
          editedTask.setTitle(newTitle);
          editedTask.setDesc(desc);
          editedTask.setQuality(quality);
          editedTask.setTimeLimit(timeLimit);
          editedTask.setType(type);
      }
      else if(customTaskList.findTask(title) != -1){
          id = customTaskList.findTask(title);
          editedTask = customTaskList.getTask(id);
          editedTask.setTitle(newTitle);
          editedTask.setDesc(desc);
          editedTask.setQuality(quality);
          editedTask.setTimeLimit(timeLimit);
          editedTask.setType(type);
      }
      else{
          System.out.println("EDITNG TASK: task not found - no changes made.");
      }

    }

    public void completeCurrentTask(int id){
        currentTaskList.getTask(id).complete();
        completedTaskList.addTask(currentTaskList.getTask(id));
        currentTaskList.removeTask(id);
    }

    public void complete(String title){
      int id = currentTaskList.findTask(title);
      completeCurrentTask(id);
    }

    public String viewCurrentTasks(){
        return currentTaskList.toString();
    }
    public TaskList getCurrentTasks(){
      return currentTaskList;
    }

    public String viewCompletedTasks(){
        return completedTaskList.toString();
    }
    public TaskList getCompletedTasks(){
        return completedTaskList;
    }

    public String viewCustomTasks(){
        return customTaskList.toString();
    }
    public TaskList getCustomTasks(){
        return customTaskList;
    }

    public String viewDefaultTasks(){
        return defaultTaskList.toString();
    }
    public TaskList getDefaultTasks(){
        return defaultTaskList;
    }

    public String viewFailedTasks(){
        return failedTaskList.toString();
    }
    public TaskList getFailedTasks(){
        return failedTaskList;
    }

    public void save(){
        //TODO (not sprint 1)
    }

    public void load(){
        //TODO (not sprint 1)
    }

    public void startGame(){
        //TODO (not sprint 1)
    }

    public String checkTimedTasks(Date currentTime){
        String failedTasks="FAILED: ";  Task task;  Date time;

        for (int i = 0; i < currentTaskList.getSize(); i++){
            task = currentTaskList.getTaskAt(i);
            if (task.isTimed()){
                time = task.getStartTime();
                if(currentTime.after(time)) {
                    failedTasks= failedTasks.concat(task.getTitle());
                    failedTasks = failedTasks.concat(", ");
                    failedTaskList.addTask(task);
                }
            }
        }
        for (int i = 0; i < failedTaskList.getSize(); i++){
            task = failedTaskList.getTaskAt(i);
            currentTaskList.removeTask(task.getID());
        }

        if (failedTasks.equals("FAILED: ")) return "No tasks failed.";
        else {
            failedTasks = failedTasks.substring(0, failedTasks.length()-2); //removes ending ", "
            return failedTasks;
        }

    }

}
