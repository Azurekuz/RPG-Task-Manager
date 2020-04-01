public class TaskManager {
    private TaskList defaultTaskList;
    private TaskList currentTaskList;
    private TaskList completedTaskList;

    TaskManager(){
        defaultTaskList = new TaskList();
        currentTaskList = new TaskList();
        completedTaskList = new TaskList();
    }

    public Task findCurrentTask(int id){
        return currentTaskList.getTask(id);
    }

    public void addCurrentTask(Task newTask){
        currentTaskList.addTask(newTask);
    }

    public void editCurrentTask(int id, Task newTask){
        currentTaskList.editTask(id, newTask);
    }

    public void completeCurrentTask(int id){
        currentTaskList.getTask(id).complete();
        completedTaskList.addTask(currentTaskList.getTask(id));
        currentTaskList.removeTask(id);
    }

    public TaskList getCurrentTasks(){
        return currentTaskList;
    }

    public TaskList viewCompletedTasks(){
        return completedTaskList;
    }

    public void saveTasks(){

    }

    public void startGame(){

    }

    public void selectTask(){

    }

    public void addCustomTask(){

    }

    public void viewDefaultTasks(){

    }
}
