import java.util.Date;
public class TaskManager {

    private TaskList defaultTaskList;
    private TaskList currentTaskList;
    private TaskList completedTaskList;
    private TaskList customTaskList;
    private TaskList failedTaskList;
    private Task mainTask;
    private Date startTime;
    //TODO tie in with User

     TaskManager(){
        defaultTaskList = new TaskList();
        currentTaskList = new TaskList();
        completedTaskList = new TaskList();
        customTaskList = new TaskList();
        failedTaskList = new TaskList();
        mainTask = new Task();
        startTime = new Date();
    }
    TaskManager(boolean genTasks){ //FOR TESTING PURPOSES
        defaultTaskList = new TaskList();
        currentTaskList = new TaskList();
        completedTaskList = new TaskList();
        customTaskList = new TaskList();
        failedTaskList = new TaskList();
        mainTask = new Task();

        Task doDishes = new Task(0, "Do the Dishes", "Clean all your unwashed dishes.", 0, 0, 0, false);
        Task doLaundry = new Task(0, "Do your Laundry", "Clean your clothes.", 0, 0, 0, false);
        Task cleanRoom = new Task(0, "Clean your room", "Organize and dust off your room.", 0, 0, 0, false);
        Task flossTeeth = new Task(0, "Floss your teeth", "Floss under your gums too.", 0, 0, 0, false);
        Task finishSemester = new Task(0, "Finish 1st Semester", "Ithaca College", 1000, 0, 1, false);
        Task getJob = new Task(0, "Get a Job", "Money can be exchanged for goods & services", 500, 0, 1, false);

        defaultTaskList.addTask(doDishes);
        defaultTaskList.addTask(doLaundry);
        defaultTaskList.addTask(cleanRoom);
        defaultTaskList.addTask(flossTeeth);
        defaultTaskList.addTask(finishSemester);
        defaultTaskList.addTask(getJob);

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

    public String selectTask(String title) throws NonExistentTaskException{
      Task task;
      int id;
      try {
          if (defaultTaskList.findTask(title) != -1) {
              //index = defaultTaskList.findTask(title);
              task = defaultTaskList.getTask(title);
              if (!(mainTask.getTitle().isEmpty()) && task.getType() == 1) {
                  return "ERROR: Can't have more than one main task selected.";
              }
              task.startTime();
              if (task.getType() == 1) mainTask = task;
              else currentTaskList.addTask(task);
          } else if (customTaskList.findTask(title) != -1) {
              // index = customTaskList.findTask(title);
              task = customTaskList.getTask(title);
              if (!(mainTask.getTitle().isEmpty()) && task.getType() == 1) {
                  return "ERROR: Can't have more than one main task selected.";
              }
              task.startTime();
              if (task.getType() == 1) mainTask = task;
              else currentTaskList.addTask(task);
          } else {
              return "SELECTING TASK: task not found - not added to current tasks.";
          }
      }catch(NonExistentTaskException e){
          throw new NonExistentTaskException("Nonexistent or Invalid Task Requested!");
      }
        return "Task started!";
    }

    public void selectTask(int id, int listType) throws NonExistentTaskException{
      try{
          Task task;
          if(listType == 0) {
              task = defaultTaskList.getTask(id);
              currentTaskList.addTask(task);
              return;
          }else if(listType == 1) {
              task = customTaskList.getTask(id);
              currentTaskList.addTask(task);
              return;
          }
      }catch(NonExistentTaskException e){
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

    public void stopTask(int id) throws NonExistentTaskException{
        try{
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
                editedTask.setBaseQuality(quality);
                editedTask.setTimeLimit(timeLimit);
                editedTask.setType(type);
                return;
            }

            id = defaultTaskList.findTask(title);
            if(id != -1) {
                editedTask = defaultTaskList.getTask(id);
                editedTask.setTitle(newTitle);
                editedTask.setDesc(desc);
                editedTask.setBaseQuality(quality);
                editedTask.setTimeLimit(timeLimit);
                editedTask.setType(type);
                return;
            }

            id = customTaskList.findTask(title);
            if(id != -1) {
                editedTask = customTaskList.getTask(id);
                editedTask.setTitle(newTitle);
                editedTask.setDesc(desc);
                editedTask.setBaseQuality(quality);
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

    public void editTask(int id, String newTitle, String desc, int quality, int timeLimit, int type, int taskList) throws NonExistentTaskException{
        try {
            Task editedTask;
            switch(taskList) {
                case 0:
                    editedTask = currentTaskList.getTask(id);
                    editedTask.setTitle(newTitle);
                    editedTask.setDesc(desc);
                    editedTask.setBaseQuality(quality);
                    editedTask.setTimeLimit(timeLimit);
                    editedTask.setType(type);
                    break;
                case 1:
                    editedTask = defaultTaskList.getTask(id);
                    editedTask.setTitle(newTitle);
                    editedTask.setDesc(desc);
                    editedTask.setBaseQuality(quality);
                    editedTask.setTimeLimit(timeLimit);
                    editedTask.setType(type);
                    break;
                case 2:
                    editedTask = customTaskList.getTask(id);
                    editedTask.setTitle(newTitle);
                    editedTask.setDesc(desc);
                    editedTask.setBaseQuality(quality);
                    editedTask.setTimeLimit(timeLimit);
                    editedTask.setType(type);
                    break;
                default:
                    System.out.println("Internal Error");
            }
        }catch (NonExistentTaskException e) {
            throw new NonExistentTaskException("Nonexistent or Invalid Task Requested!");
        }
    }

    public void completeCurrentTask(int id, double completionQuality) throws NonExistentTaskException{
        try {
            Task completedTask = currentTaskList.getTask(id);
             completedTask.complete();
             completedTask.setCompletionQuality(completionQuality);
            completedTaskList.addTask(completedTask);
            currentTaskList.removeTask(id);
        }catch (NonExistentTaskException e){
            throw new NonExistentTaskException("Nonexistent or Invalid Task Requested!");
        }
    }

    public void complete(String title, double completionQuality) throws NonExistentTaskException, IllegalArgumentException{
      if(completionQuality < 0.1 || completionQuality > 1 ){
          throw new IllegalArgumentException("Invalid completion quality (needs between 0.1 and 1)");
      }
      int id = currentTaskList.findTask(title);
      completeCurrentTask(id, completionQuality);
    }

    public void complete(int id, double completionQuality) throws NonExistentTaskException, IllegalArgumentException{
        if(completionQuality < 0.1 || completionQuality > 1 ){
            throw new IllegalArgumentException("Invalid completion quality (needs between 0.1 and 1)");
        }
        completeCurrentTask(id, completionQuality);
    }

    public String viewCurrentTasks(){
        return currentTaskList.toString();
    }

    public TaskList getCurrentTaskList(){
      return currentTaskList;
    }

    public String viewCompletedTasks(){
        return completedTaskList.toString();
    }

    public TaskList getCompletedTaskList(){
        return completedTaskList;
    }

    public String viewCustomTasks(){
        return customTaskList.toString();
    }

    public TaskList getCustomTaskList(){
        return customTaskList;
    }

    public String viewDefaultTasks(){
        return defaultTaskList.toString();
    }

    public TaskList getDefaultTaskList(){
        return defaultTaskList;
    }

    public String viewFailedTasks(){
        return failedTaskList.toString();
    }

    public TaskList getFailedTaskList(){
        return failedTaskList;
    }

    public void startGame(){
        //TODO
    }

    public String checkTimedTasks(Date currentTime) throws NonExistentTaskException {
        String failedTasks="FAILED: ";  Task task;  Date time;
        TaskList newFailedTasks = new TaskList();

        for (int i = 0; i < currentTaskList.getSize(); i++){
            task = currentTaskList.getTaskAt(i);
            if (task.checkIfTimed()){
                time = new Date(task.getStartTime().getTime() + task.getTimeLimit()*60000); //time to finish task by
                if(currentTime.after(time)) {
                    failedTasks= failedTasks.concat(task.getTitle());
                    failedTasks = failedTasks.concat(", ");
                    newFailedTasks.addTask(task);
                }
            }
        }
        if (failedTasks.equals("FAILED: ")) return "No tasks failed.";
        else {
            for (int i = 0; i < newFailedTasks.getSize(); i++){ //only go through failed tasks lists if there were failed tasks
                task = newFailedTasks.getTaskAt(i);
                currentTaskList.removeTask(task.getTitle());
                failedTaskList.addTask(task);
            }
            failedTasks = failedTasks.substring(0, failedTasks.length()-2); //removes ending ", "
            return failedTasks;
        }
    }

    /* MAIN TASKS */

    public Task getMainTask(){
        return mainTask;
    }

    public String stopMainTask(){
        if (mainTask.getTitle().isEmpty()){
            return "ERROR: No main task selected to stop.";
        }
        mainTask = new Task();
        return "Main task stopped.";
    }

    public void incMainProgress(int progress) throws IllegalArgumentException, NonExistentTaskException{
        if(progress <= 0 || progress > 100){
            throw new IllegalArgumentException("Invalid progress amount.");
        }
        else if (mainTask.getTitle().isEmpty()){
            throw new NonExistentTaskException("No main task selected.");
        }
        mainTask.addProgress(progress);
    }

    public String completeMain(){
        //TODO EXP/Item gain
        if (mainTask.getTitle().isEmpty()){
            return "ERROR: No main task selected to complete.";
        }
        if (mainTask.getProgress() < 100){
            return "ERROR: Main task not at 100% progress, can't complete.";
        }
        mainTask.complete();
        completedTaskList.addTask(mainTask);
        mainTask = new Task();
        return "Main task completed!";

    }
}
