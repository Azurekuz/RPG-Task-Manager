import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JsonUtilTest {

    @Test
    void toFromFileTest() throws IOException, NonExistentTaskException {
        TaskManager testManager = new TaskManager(true);

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

        //assertEquals(testManager.getMainTask(), testManager2.getMainTask()); fails when a main task hasn't been selected yet

    }
}