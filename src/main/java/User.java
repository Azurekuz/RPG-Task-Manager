public class User {
    private String username;
    private String password;
    private TaskList currentTaskList, dailyTaskList, completedTaskList, customTaskList, failedTaskList;
    private Task mainTask;
    private PlayerCharacter character;

    public User(String username, String password){
        this.username =username;
        this.password=password;
        this.currentTaskList = new TaskList();
        this.customTaskList = new TaskList();
        this.completedTaskList = new TaskList();
        this.dailyTaskList = new TaskList();
        this.customTaskList = new TaskList();
        this.character = new PlayerCharacter();
    }

    //getters:


    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public PlayerCharacter getCharacter() {
        return character;
    }

    public TaskList getCompletedTaskList() {
        return completedTaskList;
    }

    public TaskList getCurrentTaskList() {
        return currentTaskList;
    }

    public TaskList getCustomTaskList() {
        return customTaskList;
    }

    public TaskList getDailyTaskList() { return dailyTaskList; }

    public TaskList getFailedTaskList() { return failedTaskList; }

    public void addTask(Task task){

    }


    public String toString(TaskList tasks){
        String toString = "";
        for (int i = 0; i < tasks.taskList.size(); i++) {
            toString+=(tasks.taskList.get(i)).id+" ";
        }
        return toString;
    }
}
