import java.util.Iterator;

public class User {
    private String ID;
    private TaskList currentTasks,customTasks,completedTasks;
   // private PlayerCharacter character;

    public User(String ID){
        this.ID=ID;
        this.currentTasks = new TaskList();
        this.customTasks = new TaskList();
        this.completedTasks = new TaskList();
        //this.character = new PlayerCharacter();
    }

    //getters:

    public String getID() {
        return ID;
    }

//    public PlayerCharacter getCharacter() {
//        return character;
//    }

    public TaskList getCompletedTasks() {
        return completedTasks;
    }

    public TaskList getCurrentTasks() {
        return currentTasks;
    }

    public TaskList getCustomTasks() {
        return customTasks;
    }



    public String toString(TaskList tasks){
        String toString = "";
        for (int i = 0; i < tasks.taskList.size(); i++) {
            toString+=(tasks.taskList.get(i)).id+" ";
        }
        return toString;
    }
}
