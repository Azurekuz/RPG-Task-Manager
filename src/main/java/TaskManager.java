public class TaskManager {

    private TaskList defaultTaskList;
    private TaskList currentTaskList;
    private TaskList completedTaskList;
    private TaskList customTaskList;
    //TODO tie in with User

  TaskManager(){
        defaultTaskList = new TaskList();
        currentTaskList = new TaskList();
        completedTaskList = new TaskList();
        customTaskList = new TaskList();

        Task doDishes = new Task(0, "Do the Dishes", "Clean all your unwashed dishes.", 0, 0, 0, false);
        Task doLaundry = new Task(0, "Do your Laundry", "Clean your clothes.", 0, 0, 0, false);
        Task cleanRoom = new Task(0, "Clean your room", "Organize and dust off your room.", 0, 0, 0, false);
        Task flossTeeth = new Task(0, "Floss your teeth", "Floss under your gums too.", 0, 0, 0, false);

        defaultTaskList.addTask(doDishes);
        defaultTaskList.addTask(doLaundry);
        defaultTaskList.addTask(cleanRoom);
        defaultTaskList.addTask(flossTeeth);
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
    public TaskList getCustomTasks(){
        return customTaskList;
    }

    public String viewDefaultTasks(){
        return defaultTaskList.toString();
    }

    public TaskList getDefaultTasks(){
        return defaultTaskList;
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

}
