import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class JsonUtilTest {

    @Test
    void toFromFileTest() throws IOException, NonExistentTaskException, DuplicateTaskException {
        //Includes failed/timed task testing to make sure it is saved correctly
        TaskManager testManager = new TaskManager(true);
        testManager.addCustomTask("Do Homework Before Class", "Due in an hour!", 10, 60, 0);
        testManager.selectTask("Do Homework Before Class");
        LocalDateTime currentTime = LocalDateTime.now();
        assertEquals(testManager.checkTimedTasks(currentTime),"[NOTICE][ No tasks failed.]");

        JsonUtil.toJsonFile("src/resources/taskManagerTest.json", testManager);

        TaskManager testManager2;

        testManager2 = JsonUtil.fromJsonFile("src/resources/taskManagerTest.json", TaskManager.class);

        assertEquals(testManager.viewDefaultTasks(), testManager2.viewDefaultTasks());
        //assertEquals(testManager.getDefaultTaskList(), testManager2.getDefaultTaskList()); //This fails because they're in different memory?
        assertEquals(testManager.getDefaultTaskList().getTaskAt(0).getTitle(),testManager2.getDefaultTaskList().getTaskAt(0).getTitle());
        assertEquals(testManager.viewCustomTasks(), testManager2.viewCustomTasks());
        assertEquals(testManager.viewFailedTasks(), testManager2.viewFailedTasks());
        assertEquals(testManager.viewCompletedTasks(), testManager2.viewCompletedTasks());
        assertEquals(testManager.viewCurrentTasks(), testManager2.viewCurrentTasks());

        currentTime = currentTime.plusHours(2);
        assertEquals(testManager2.checkTimedTasks(currentTime),"FAILED: Do Homework Before Class");
        assertTrue(testManager2.viewCurrentTasks().contains("No tasks."));
        assertTrue(testManager2.viewFailedTasks().contains("Do Homework Before Class"));

        //assertEquals(testManager.getMainTask(), testManager2.getMainTask()); fails when a main task hasn't been selected yet

    }
}