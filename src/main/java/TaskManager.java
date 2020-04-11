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
    public Task findCurrentTask(int id) throws NonExistentTaskException{
      try {
          return currentTaskList.getTask(id);
      } catch (NonExistentTaskException e){
          throw new NonExistentTaskException("Nonexistent or Invalid Task Requested!");
      }
  }

    public void addCurrentTask(Task newTask){
        currentTaskList.addTask(newTask);
    }
    public void addCustomTask(String title, String desc, int quality, int timeLimit, int type){
        int id = customTaskList.getSize();
        Task newTask = new Task(id, title, desc, quality, timeLimit, type, false);
        customTaskList.addTask(newTask);
    }

    public void selectTask(String title) throws NonExistentTaskException{
      try{
            Task task;
            int id = defaultTaskList.findTask(title);
            if(id != -1) {
                task = defaultTaskList.getTask(id);
                currentTaskList.addTask(task);
                return;
            }
            id = customTaskList.findTask(title);
            if(id != -1) {
                task = customTaskList.getTask(id);
                currentTaskList.addTask(task);
                return;
            }

            if(id==-1){
                throw new NonExistentTaskException("Nonexistent or Invalid Task Requested!");
            }
      }catch (NonExistentTaskException e){
          throw new NonExistentTaskException("Nonexistent or Invalid Task Requested!");
      }
    }

    public void stopTask(String title) throws NonExistentTaskException{
        try{
            int id = currentTaskList.findTask(title);
            currentTaskList.removeTask(id);
        }catch (NonExistentTaskException e){
            throw new NonExistentTaskException("Nonexistent or Invalid Task Requested!");
        }
    }


    public void editCurrentTask(int id, Task newTask) throws NonExistentTaskException{
        try {
            currentTaskList.editTask(id, newTask);
        }catch (NonExistentTaskException e) {
            throw new NonExistentTaskException("Nonexistent or Invalid Task Requested!");
        }
    }

    public void editTask(String title, String newTitle, String desc, int quality, int timeLimit, int type) throws NonExistentTaskException{
        try {
            Task editedTask;
            int id = currentTaskList.findTask(title);
            if(id != -1){
                editedTask = currentTaskList.getTask(id);
                editedTask.setTitle(newTitle);
                editedTask.setDesc(desc);
                editedTask.setQuality(quality);
                editedTask.setTimeLimit(timeLimit);
                editedTask.setType(type);
                return;
            }

            id = defaultTaskList.findTask(title);
            if(id != -1) {
                editedTask = defaultTaskList.getTask(id);
                editedTask.setTitle(newTitle);
                editedTask.setDesc(desc);
                editedTask.setQuality(quality);
                editedTask.setTimeLimit(timeLimit);
                editedTask.setType(type);
                return;
            }

            id = customTaskList.findTask(title);
            if(id != -1) {
                editedTask = customTaskList.getTask(id);
                editedTask.setTitle(newTitle);
                editedTask.setDesc(desc);
                editedTask.setQuality(quality);
                editedTask.setTimeLimit(timeLimit);
                editedTask.setType(type);
                return;
            }

            if(id == -1){
                throw new NonExistentTaskException("Nonexistent or Invalid Task Requested!");
            }
        }catch (NonExistentTaskException e) {
            throw new NonExistentTaskException("Nonexistent or Invalid Task Requested!");
        }
    }

    public void completeCurrentTask(int id) throws NonExistentTaskException{
        try {
            currentTaskList.getTask(id).complete();
            completedTaskList.addTask(currentTaskList.getTask(id));
            currentTaskList.removeTask(id);
        }catch (NonExistentTaskException e){
            throw new NonExistentTaskException("Nonexistent or Invalid Task Requested!");
        }
    }

    public void complete(String title) throws NonExistentTaskException{
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
                time = new Date(task.getStartTime().getTime() + task.getTimeLimit()*60000); //time to finish task by
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
