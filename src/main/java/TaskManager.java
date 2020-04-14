import java.time.LocalDateTime;
import java.util.Date;
public class TaskManager {

    private TaskList defaultTaskList;
    private TaskList currentTaskList;
    private TaskList dailyTaskList;
    private TaskList completedTaskList;
    private TaskList customTaskList;
    private TaskList failedTaskList;
    private Task mainTask;
    private Date lastTimeUsed = null;
    private Date startTime;
    //TODO tie in with User

     TaskManager(){
        defaultTaskList = new TaskList();
        currentTaskList = new TaskList();
        dailyTaskList = new TaskList();
        completedTaskList = new TaskList();
        customTaskList = new TaskList();
        failedTaskList = new TaskList();
        mainTask = new Task();
        startTime = new Date();

        startUp();
    }

    public void startUp(){
        try {
            if(lastTimeUsed == null) {
                generateDefaultTaskList();
                generateDefaultDailyTaskList();
            }
        }catch(DuplicateTaskException e){
            System.out.println("[WARNING][Default and/or Daily tasks have already been generated!]");
        }
        startTime = new java.util.Date(System.currentTimeMillis());
        populateDailyTasks();
    }

    public Task findCurrentTask(int id) throws NonExistentTaskException{
      try {
          return currentTaskList.getTask(id);
      } catch (NonExistentTaskException e){
          throw new NonExistentTaskException("Nonexistent or Invalid Task Requested!");
      }
    }

    public void generateDefaultTaskList() throws DuplicateTaskException{
        Task doDishes = new Task(0, "Do the Dishes", "Clean all your unwashed dishes.", 0, 0, 0, false);
        Task doLaundry = new Task(1, "Do your Laundry", "Clean your clothes.", 0, 0, 0, false);
        Task cleanRoom = new Task(2, "Clean your room", "Organize and dust off your room.", 0, 0, 0, false);
        Task finishSemester = new Task(3, "Finish 1st Semester", "Ithaca College", 1000, 0, 1, false);
        Task getJob = new Task(4, "Get a Job", "Money can be exchanged for goods & services", 500, 0, 1, false);

        defaultTaskList.addTask(doDishes);
        defaultTaskList.addTask(doLaundry);
        defaultTaskList.addTask(cleanRoom);
        defaultTaskList.addTask(finishSemester);
        defaultTaskList.addTask(getJob);
    }

    public void generateDefaultDailyTaskList() throws DuplicateTaskException{
        Task flossTeeth = new Task(0, "Floss your teeth", "Floss under your gums too.", 0, 0, 0, false);
        Task checkEmail = new Task(1, "Check your email", "Sift through work and spam mail.", 0, 0, 0, false);
        Task exercise = new Task(2, "Walk or Exercise", "Keep yourself in good shape.", 0, 0, 0, false);

        dailyTaskList.addTask(flossTeeth);
        dailyTaskList.addTask(checkEmail);
        dailyTaskList.addTask(exercise);
    }

    public void addCurrentTask(Task newTask) throws DuplicateTaskException{
        try {
            currentTaskList.addTask(newTask);
        }catch(DuplicateTaskException e){
            throw new DuplicateTaskException(e.getMessage());
        }
    }

    public void addCustomTask(String title, String desc, int quality, int timeLimit, int type){
        try {
            int id = customTaskList.getSize();
            Task newTask = new Task(id, title, desc, quality, timeLimit, type, false);
            customTaskList.addTask(newTask);
        }catch(DuplicateTaskException e){
            System.out.println("[Error][" + e.getMessage() + "]");
        }
    }

    public String selectTask(String title) throws NonExistentTaskException{
      Task task;
      int id;
      try {
          if (defaultTaskList.findTask(title) != -1) {
              //index = defaultTaskList.findTask(title);
              task = defaultTaskList.getTask(title);
              if (!(mainTask.getTitle().isEmpty()) && task.getTypeInt() == 1) {
                  return "ERROR: Can't have more than one main task selected.";
              }
              task.startTime();
              try {
                  if (task.getTypeInt() == 1) mainTask = task;
                  else currentTaskList.addTask(task);
              }catch(DuplicateTaskException e){
                  System.out.println("[ERROR][You already have this main task selected!]");
              }
          } else if (customTaskList.findTask(title) != -1) {
              // index = customTaskList.findTask(title);
              task = customTaskList.getTask(title);
              if (!(mainTask.getTitle().isEmpty()) && task.getTypeInt() == 1) {
                  return "ERROR: Can't have more than one main task selected.";
              }
              task.startTime();
              try {
                  if (task.getTypeInt() == 1) mainTask = task;
                  else currentTaskList.addTask(task);
              }catch(DuplicateTaskException e){
                  System.out.println("[ERROR][You already have this main task selected!]");
              }
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
        }catch(DuplicateTaskException e){
            System.out.println("[Error][" + e.getMessage() + "]");
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

    public void editTask(int id, String newTitle, String desc, int quality, int timeLimit, int type, int taskList) throws NonExistentTaskException{
        try {
            Task editedTask;
            switch(taskList) {
                case 0:
                    editedTask = currentTaskList.getTask(id);
                    editedTask.setTitle(newTitle);
                    editedTask.setDesc(desc);
                    editedTask.setQuality(quality);
                    editedTask.setTimeLimit(timeLimit);
                    editedTask.setType(type);
                    break;
                case 1:
                    editedTask = defaultTaskList.getTask(id);
                    editedTask.setTitle(newTitle);
                    editedTask.setDesc(desc);
                    editedTask.setQuality(quality);
                    editedTask.setTimeLimit(timeLimit);
                    editedTask.setType(type);
                    break;
                case 2:
                    editedTask = customTaskList.getTask(id);
                    editedTask.setTitle(newTitle);
                    editedTask.setDesc(desc);
                    editedTask.setQuality(quality);
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

    public void populateDailyTasks(){
        for(int dailyID = 0; dailyID < dailyTaskList.getSize(); dailyID++){
            try{
                currentTaskList.addTask(dailyTaskList.getTask(dailyID));
            }catch(NonExistentTaskException e){
                System.out.println("[ERROR][Internal error. Daily task no longer found.]");
            }catch(DuplicateTaskException e){
                System.out.println("[NOTICE][Daily task was not completed. Skipped!]");
            }
        }
    }

    public void completeCurrentTask(int id) throws NonExistentTaskException{
        try {
            currentTaskList.getTask(id).complete();
            completedTaskList.addTask(currentTaskList.getTask(id));
            currentTaskList.removeTask(id);
        }catch (NonExistentTaskException e){
            throw new NonExistentTaskException("Nonexistent or Invalid Task Requested!");
        }catch(DuplicateTaskException e){
            System.out.println("[Error][" + "Task already completed!" + "]");
        }
    }

    public void complete(String title) throws NonExistentTaskException{
      int id = currentTaskList.findTask(title);
      completeCurrentTask(id);
    }

    public void complete(int id) throws NonExistentTaskException{
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

    public String viewDailyTasks(){
         return dailyTaskList.toString();
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
        //TODO
    }

    public void load(){
        //TODO
    }

    public void startGame(){
        //TODO
    }

    public String checkTimedTasks(Date currentTime) throws NonExistentTaskException {
        String failedTasks="FAILED: ";  Task task;  Date time;

        for (int i = 0; i < currentTaskList.getSize(); i++){
            task = currentTaskList.getTaskAt(i);
            if (task.isTimed()){
                time = new Date(task.getStartTime().getTime() + task.getTimeLimit()*60000); //time to finish task by
                if(currentTime.after(time)) {
                    failedTasks= failedTasks.concat(task.getTitle());
                    failedTasks = failedTasks.concat(", ");
                    try {
                        failedTaskList.addTask(task);
                    }catch(DuplicateTaskException e){
                        System.out.println("[Error][" + e.getMessage() + "]");
                    }
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
        try {
            completedTaskList.addTask(mainTask);
        }catch (DuplicateTaskException e){
            System.out.println("[NOTICE][You have completed this main task before!]");
        }
        mainTask = new Task();
        return "Main task completed!";

    }

    public Date getDate(){
        return startTime;
    }

}
