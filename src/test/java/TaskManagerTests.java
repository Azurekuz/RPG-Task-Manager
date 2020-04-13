import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

public class TaskManagerTests { //TODO test new tests added by Elias
    @Test
    public void findTaskTest() throws NonExistentTaskException{
        TaskManager testManager = new TaskManager();
        Task testTask = new Task(0, "Test1", "A test task", 5, 20, 0, false);
        testManager.addCurrentTask(testTask);

        assertEquals(testTask,testManager.findCurrentTask(0));
    }

    @Test
    public void addTaskTest() throws NonExistentTaskException{
        TaskManager testManager = new TaskManager();
        Task testTask = new Task(0, "Test1", "A test task", 5, 20, 0, false);
        testManager.addCurrentTask(testTask);

        assertEquals("Test1", testManager.findCurrentTask(0).getTitle());

    }

    @Test
    public void editTaskTest() throws NonExistentTaskException{
        TaskManager testManager = new TaskManager();
        Task testTask = new Task(0, "Test1", "A test task", 5, 20, 0, false);
        testManager.addCurrentTask(testTask);
        assertEquals("Test1", testManager.findCurrentTask(0).getTitle());
        testTask.setTitle("Test2");
        testManager.editCurrentTask(0, testTask);
        assertEquals("Test2", testManager.findCurrentTask(0).getTitle());
    }

    @Test
    public void completeTaskTest() throws NonExistentTaskException{
        TaskManager testManager = new TaskManager();
        Task testTask = new Task(0, "Test1", "A test task", 5, 20, 0, false);
        testManager.addCurrentTask(testTask);
        testManager.completeCurrentTask(0);
        TaskList completedTasks = testManager.getCompletedTasks();
        assertEquals("Test1",completedTasks.getTask(0).getTitle());
    }

    @Test
    public void getCurrentTasksTest() throws NonExistentTaskException{
        TaskManager testManager = new TaskManager();
        Task testTask1 = new Task(0, "Test1", "A test task", 5, 20, 0, false);
        Task testTask2 = new Task(1, "Test2", "Another test task", 4, 25, 0, false);
        Task testTask3 = new Task(2, "Test2", "And another test task", 6, 15, 0, false);

        testManager.addCurrentTask(testTask1);
        testManager.addCurrentTask(testTask2);
        testManager.addCurrentTask(testTask3);

        TaskList currentTasks = testManager.getCurrentTasks();
        for(int taskID = 0; taskID < currentTasks.getSize();taskID++){
            assertEquals(currentTasks.getTask(taskID), testManager.findCurrentTask(taskID));
        }
        assertThrows(NonExistentTaskException.class, ()-> {currentTasks.getTask(currentTasks.getSize());});
        assertThrows(NonExistentTaskException.class, ()-> {currentTasks.getTask(-1);});
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
        TaskManager testManager = new TaskManager();
        TaskList defaultTasks = testManager.getDefaultTasks();
        String expected = "{id: Title | Description | Quality | TimeLimit | Type | Complete\n" +
                "0: " + "Do the Dishes" +" "+ "Clean all your unwashed dishes." +" "+ 0 +" "+ 0 +" "+ "default" +" "+ false + "\n" +
                "1: " + "Do your Laundry" +" "+ "Clean your clothes." +" "+ 0 +" "+ 0 +" "+ "default" +" "+ false + "\n" +
                "2: " + "Clean your room" +" "+ "Organize and dust off your room." +" "+ 0 +" "+ 0 +" "+ "default" +" "+ false + "\n" +
                "3: " + "Floss your teeth" +" "+ "Floss under your gums too." +" "+ 0 +" "+ 0 +" "+ "default" +" "+ false + "\n" + "}";

        assertEquals(testManager.viewDefaultTasks(), expected);

    }

    @Test
    public void timeTests() throws NonExistentTaskException {
        TaskManager testManager = new TaskManager();
        //Testing one timed task
        testManager.addCustomTask("Do Homework Before Class", "Due in an hour!", 10, 60, 0);
        testManager.selectTask("Do Homework Before Class");

        Date currentTime = new Date();
        assertEquals(testManager.checkTimedTasks(currentTime),"No tasks failed.");

        currentTime.setTime(currentTime.getTime() + 65*60000);

        assertEquals(testManager.checkTimedTasks(currentTime),"FAILED: Do Homework Before Class");
        assertTrue(testManager.viewCurrentTasks().contains("No tasks."));
        assertTrue(testManager.viewFailedTasks().contains("Do Homework Before Class"));

        //Testing two timed tasks
        TaskManager testManager2 = new TaskManager();

        testManager2.addCustomTask("Do Homework Before Class", "Due in an hour!", 10, 60, 0);
        testManager2.selectTask("Do Homework Before Class");
        testManager2.addCustomTask("Email Teacher", "Before class!", 10, 30, 0);
        testManager2.selectTask("Email Teacher");
        currentTime = new Date();

        assertEquals(testManager2.checkTimedTasks(currentTime),"No tasks failed.");
        currentTime.setTime(currentTime.getTime() + 65*60000); //faking time passage

        assertEquals(testManager2.checkTimedTasks(currentTime),"FAILED: Do Homework Before Class, Email Teacher");
        assertTrue(testManager2.viewCurrentTasks().contains("No tasks."));
        assertTrue(testManager2.viewFailedTasks().contains("Do Homework Before Class")
                && testManager2.viewFailedTasks().contains("Email Teacher"));


    }
}
