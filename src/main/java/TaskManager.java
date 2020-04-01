public class TaskManager {

    private TaskList defaultTaskList;
    private TaskList currentTaskList;
    private TaskList completedTaskList;
    private TaskList customTaskList;

  TaskManager(){
        defaultTaskList = new TaskList();
        currentTaskList = new TaskList();
        completedTaskList = new TaskList();
        customTaskList = new TaskList();
    }
    public Task findCurrentTask(int id){
        return currentTaskList.getTask(id);
    }

    public void addCurrentTask(Task newTask){
        currentTaskList.addTask(newTask);
    }

    public void selectTask(String title){
      Task task;
      int id;
        if(defaultTaskList.findTask(title) != -1){
            id = defaultTaskList.findTask(title);
            task = defaultTaskList.getTask(id);
            currentTaskList.addTask(task);
        }
        else if(customTaskList.findTask(title) != -1){
            id = customTaskList.findTask(title);
            task = customTaskList.getTask(id);
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

    public TaskList getCurrentTasks(){
        return currentTaskList;
    }

    public TaskList viewCompletedTasks(){
        return completedTaskList;
    }

    public void save(){

    }

    public void load(){


    }

    public void startGame(){

    }

    public void addCustomTask(String title, String desc, int quality, int timeLimit, int type){

    }

    public void viewCustomTasks(){

    }

    public void viewDefaultTasks(){

    }
}
