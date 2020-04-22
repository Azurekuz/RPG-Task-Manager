import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    @Test
    public void TaskListTest() throws NonExistentTaskException, DuplicateTaskException{

        //TODO test toString and new addTask
        Task testTasks = new Task(0, "title1", "desc1", 0, 0, 0, false);
        Task testTasks2 = new Task(1, "title2", "desc2", 0, 0, 0, false);
        Task testTasks3 = new Task(2, "title3", "desc3", 0, 0, 0, false);
        Task testTasks4 = new Task(3, "title4", "desc4", 0, 0, 0, true);
        Task testTasks5 = new Task(4, "title5", "desc5", 0, 0, 0, true);
        TaskList testTaskList = new TaskList();
        //id, title, desc, baseQuality, timeLimit, type, complete
        testTaskList.addTask(testTasks);
        testTaskList.addTask(testTasks2);
        testTaskList.addTask(testTasks3);
        testTaskList.addTask(testTasks4);
        testTaskList.addTask(testTasks5);

//        testTaskList.removeTask("title1");
//        assertThrows(NonExistentTaskException.class, ()-> testTaskList.editTask(4, testTasks));
//        testTaskList.addTask(testTasks);

        assertEquals(0, testTasks.getId());
        assertEquals("title1", testTasks.getTitle());
        assertEquals("desc1", testTasks.getDesc());
        assertEquals(0, testTasks.getBaseQuality());

        assertEquals(0, testTasks.getTimeLimit());
        assertFalse(testTasks.checkIfTimed());

        assertEquals(0, testTasks.getType());
        assertEquals("default", testTasks.getTypeStr());
        assertEquals(false, testTasks.isComplete());

        testTasks.setID(1);
        assertEquals(1, testTasks.getId());
        assertThrows(IllegalArgumentException.class, () -> testTasks.setID(-1));

        testTasks.setTitle("newtitle1");
        assertEquals("newtitle1", testTasks.getTitle());

        testTasks.setDesc("newdesc1");
        assertEquals("newdesc1", testTasks.getDesc());

        testTasks.setBaseQuality(10);
        assertEquals(10, testTasks.getBaseQuality());
        assertThrows(IllegalArgumentException.class, () -> testTasks.setBaseQuality(-1));

        testTasks.setTimeLimit(1);
        assertEquals(1, testTasks.getTimeLimit());
        assertTrue(testTasks.checkIfTimed());
        testTasks.setTimeLimit(3600);
        assertEquals(3600, testTasks.getTimeLimit());
        assertTrue(testTasks.checkIfTimed());
        assertThrows(IllegalArgumentException.class, () -> testTasks.setTimeLimit(-1));

        testTasks.setType(1);
        assertEquals(1, testTasks.getType());
        assertEquals("main", testTasks.getTypeStr());
        testTasks.setType(2);
        assertEquals("daily", testTasks.getTypeStr());
        testTasks.setType(3);
        assertEquals("weekly", testTasks.getTypeStr());
        assertThrows(IllegalArgumentException.class, () -> testTasks.setType(-1));
        assertThrows(IllegalArgumentException.class, () -> testTasks.setType(4));

        testTasks.complete();
        assertEquals(true, testTasks.isComplete());

        //default constructor - blank object created.
        Task testTask2 = new Task();
        assertEquals(0, testTask2.getId());
        assertTrue(testTask2.getTitle().isEmpty());
        assertNull(testTask2.getDesc());
        assertEquals(0, testTask2.getBaseQuality());
        assertEquals(0, testTask2.getTimeLimit());
        assertFalse(testTask2.checkIfTimed());
        assertEquals(0, testTask2.getType());
        assertEquals("default", testTask2.getTypeStr());
        assertFalse(testTask2.isComplete());


    }
}
