import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskManagerTests {
    @Test
    public void findTaskTest(){

    }

    @Test
    public void addTaskTest(){
        TaskManager testManager = new TaskManager();
        Task testTask = new Task(0, "Test1", "A test task", 5, 20, 0, false);
        testManager.addCurrentTask(testTask);

        assertEquals("Test1", testManager.findCurrentTask(0));

    }

    @Test
    public void editTaskTest(){

    }

    @Test
    public void completeTaskTest(){

    }

    @Test
    public void getCurrentTasksTest(){

    }

    @Test
    public void viewCompletedTasksTest(){

    }

    @Test
    public void saveTasksTest(){

    }

    @Test
    public void startGameTest(){

    }

    @Test
    public void selectTaskTest(){

    }

    @Test
    public void addCustomTaskTest(){

    }

    @Test
    public void viewDefaultTasksTest(){

    }
}
