import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
public class TaskManager {

    private TaskList defaultTaskList;
    private TaskList currentTaskList;
    private TaskList dailyTaskList;
    private TaskList completedTaskList;
    private TaskList customTaskList;
    private TaskList failedTaskList;
    private Task mainTask;
    private LocalDateTime lastTimeUsed = null;
    private LocalDateTime startTime;
    //TODO tie in with User


     TaskManager(boolean genTasks){
        defaultTaskList = new TaskList();
        currentTaskList = new TaskList();
        dailyTaskList = new TaskList();
        completedTaskList = new TaskList();
        customTaskList = new TaskList();
        failedTaskList = new TaskList();
        mainTask = new Task();
        startTime = LocalDateTime.now();

        startUp();

        if(genTasks){
            try {
                generateDefaultTaskList();
            } catch (DuplicateTaskException ignored){} //this isn't going to happen
        }
    }
    TaskManager(){ //Need default constructor for json, just calls the other constructor and makes a blank object
         this(false);
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
        if(lastTimeUsed != null && ChronoUnit.HOURS.between(lastTimeUsed, startTime) > 24) {
            populateDailyTasks();
        }
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
        Task doLaundry = new Task(1, "Do your Laundry", "Clean your clothes.", 0, 0, 3, false);
        Task cleanRoom = new Task(2, "Clean your room", "Organize and dust off your room.", 0, 3, 0, false);
        Task finishSemester = new Task(3, "Finish 1st Semester", "Ithaca College", 1000, 0, 1, false);
        Task getJob = new Task(4, "Get a Job", "Money can be exchanged for goods & services", 500, 0, 1, false);
        Task flossTeeth = new Task(5, "Floss your teeth", "Floss under your gums too.", 0, 0, 2, false);
        Task checkEmail = new Task(6, "Check your email", "Sift through work and spam mail.", 0, 0, 2, false);
        Task exercise = new Task(7, "Walk or Exercise", "Keep yourself in good shape.", 0, 0, 2, false);

        defaultTaskList.addTask(doDishes);
        defaultTaskList.addTask(doLaundry);
        defaultTaskList.addTask(cleanRoom);
        defaultTaskList.addTask(finishSemester);
        defaultTaskList.addTask(getJob);
        defaultTaskList.addTask(flossTeeth);
        defaultTaskList.addTask(checkEmail);
        defaultTaskList.addTask(exercise);
    }

    public void generateDefaultDailyTaskList() throws DuplicateTaskException{

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
      try {
          if (defaultTaskList.findTask(title) != -1) { //if it exists in defaultTaskList
              task = defaultTaskList.getTask(title);
              if (!(mainTask.getTitle().isEmpty()) && task.getType() == 1) {
                  return "ERROR: Can't have more than one main task selected.";
              }
              task.startTime();
              try {
                  if (task.getType() == 1) mainTask = task;
                  else if (task.getType() == 2){
                      dailyTaskList.addTask(recalculateHoursLeft(task));
                  }else currentTaskList.addTask(task);
              }catch(DuplicateTaskException e){
                  System.out.println("[ERROR][You already have this main task selected!]");
              }
          } else if (customTaskList.findTask(title) != -1) { //if it exists in customTaskList
              task = customTaskList.getTask(title);
              if (!(mainTask.getTitle().isEmpty()) && task.getType() == 1) {
                  return "ERROR: Can't have more than one main task selected.";
              }
              task.startTime();
              try {
                  if (task.getType() == 1) mainTask = task;
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
              checkIfDaily(task);
          }else if(listType == 1) {
              task = customTaskList.getTask(id);
              checkIfDaily(task);
          }
        }catch(NonExistentTaskException e){
            throw new NonExistentTaskException("Nonexistent or Invalid Task Requested!");
        }catch(DuplicateTaskException e){
            System.out.println("[Error][" + e.getMessage() + "]");
        }
    }

    public void checkIfDaily(Task task) throws DuplicateTaskException{
        //System.out.println(startTime);
        //System.out.println((new Date(startTime.getYear(), startTime.getMonth(), startTime.getDay()+1, 0,0)));
        if(task.getType() == 2) {
            dailyTaskList.addTask(recalculateHoursLeft(task));
        }else{
            currentTaskList.addTask(task);
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
            int index = currentTaskList.findTask(title);
            if(index != -1){
                editedTask = currentTaskList.getTaskAt(index);
                editedTask.setTitle(newTitle);
                editedTask.setDesc(desc);
                editedTask.setBaseQuality(quality);
                editedTask.setTimeLimit(timeLimit);
                editedTask.setType(type);
                return;
            }

            index = defaultTaskList.findTask(title);
            if(index != -1) {
                editedTask = defaultTaskList.getTaskAt(index);
                editedTask.setTitle(newTitle);
                editedTask.setDesc(desc);
                editedTask.setBaseQuality(quality);
                editedTask.setTimeLimit(timeLimit);
                editedTask.setType(type);
                return;
            }

            index = dailyTaskList.findTask(title);
            if(index != -1) {
                editedTask = dailyTaskList.getTaskAt(index);
                editedTask.setTitle(newTitle);
                editedTask.setDesc(desc);
                editedTask.setBaseQuality(quality);
                editedTask.setTimeLimit(timeLimit);
                editedTask.setType(type);
                return;
            }

            index = customTaskList.findTask(title);
            if(index != -1) {
                editedTask = customTaskList.getTaskAt(index);
                editedTask.setTitle(newTitle);
                editedTask.setDesc(desc);
                editedTask.setBaseQuality(quality);
                editedTask.setTimeLimit(timeLimit);
                editedTask.setType(type);
                return;
            }
            throw new NonExistentTaskException("Nonexistent or Invalid Task Requested!");
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
                    editedTask = dailyTaskList.getTask(id);
                    editedTask.setTitle(newTitle);
                    editedTask.setDesc(desc);
                    editedTask.setBaseQuality(quality);
                    editedTask.setTimeLimit(timeLimit);
                    editedTask.setType(type);
                    break;
                case 2:
                    editedTask = defaultTaskList.getTask(id);
                    editedTask.setTitle(newTitle);
                    editedTask.setDesc(desc);
                    editedTask.setBaseQuality(quality);
                    editedTask.setTimeLimit(timeLimit);
                    editedTask.setType(type);
                    break;
                case 3:
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

    public void populateDailyTasks(){
        for(int dailyID = 0; dailyID < dailyTaskList.getSize(); dailyID++){
            try{
                currentTaskList.addTask(recalculateHoursLeft(dailyTaskList.getTask(dailyID)));
            }catch(NonExistentTaskException e){
                System.out.println("[ERROR][Internal error. Daily task no longer found.]");
            }catch(DuplicateTaskException e){
                System.out.println("[NOTICE][Daily task was not completed. Skipped!]");
            }
        }
    }

    public Task recalculateHoursLeft(Task task){
        LocalDateTime nextDay = LocalDateTime.now().minusDays(-1);
        nextDay = nextDay.minusHours(nextDay.getHour());nextDay = nextDay.minusMinutes(nextDay.getMinute());nextDay = nextDay.minusSeconds(nextDay.getSecond());
        task.setTimeLimit((int) ChronoUnit.HOURS.between(startTime, nextDay));
        return task;
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
        }catch(DuplicateTaskException e){
            System.out.println("[Error][" + "Task already completed!" + "]");
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

    public String viewDailyTasks(){
         return dailyTaskList.toString();
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

    public String checkTimedTasks(LocalDateTime currentTime) throws NonExistentTaskException, DuplicateTaskException {
        String failedTasks="FAILED: ";  Task task;  LocalDateTime time;
        TaskList newFailedTasks = new TaskList();

        for (int i = 0; i < currentTaskList.getSize(); i++){
            task = currentTaskList.getTaskAt(i);
            if (task.checkIfTimed()){
                //time = new Date(task.getStartTime().getTime() + task.getTimeLimit()*60000);
                time = task.getStartTime().plusMinutes(task.getTimeLimit()); //time to finish task by
                if(currentTime.isAfter(time)) {
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
        try {
            completedTaskList.addTask(mainTask);
        }catch (DuplicateTaskException e){
            System.out.println("[NOTICE][You have completed this main task before!]");
        }
        mainTask = new Task();
        return "Main task completed!";

    }

    //@JsonSerialize(using = LocalDateTimeSerializer.class)
    public LocalDateTime getStartTime(){
        return startTime;
    }
}
