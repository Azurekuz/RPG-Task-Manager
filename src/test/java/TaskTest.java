import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TaskTest {
    @Test
    public void TaskTest(){
        //id, title, desc, baseQuality, timeLimit, type, complete
        Task testTask = new Task(0, "title1","desc1",0, 0, 0, false);

        assertEquals(0, testTask.getId());
        assertEquals("title1", testTask.getTitle());
        assertEquals("desc1", testTask.getDesc());
        assertEquals(0, testTask.getBaseQuality());

        assertEquals(0, testTask.getTimeLimit());
        assertFalse(testTask.checkIfTimed());

        assertEquals(0, testTask.getType());
        assertEquals("default", testTask.getTypeStr());
        assertFalse(testTask.isComplete());

        testTask.setID(1);
        assertEquals(1, testTask.getId());
        assertThrows(IllegalArgumentException.class, () -> testTask.setID(-1));

        testTask.setTitle("newtitle1");
        assertEquals("newtitle1", testTask.getTitle());

        testTask.setDesc("newdesc1");
        assertEquals("newdesc1", testTask.getDesc());

        testTask.setBaseQuality(10);
        assertEquals(10, testTask.getBaseQuality());
        assertThrows(IllegalArgumentException.class, () -> testTask.setBaseQuality(-1));

        testTask.setTimeLimit(1);
        assertEquals(1, testTask.getTimeLimit());
        assertTrue(testTask.checkIfTimed());
        testTask.setTimeLimit(3600);
        assertEquals(3600, testTask.getTimeLimit());
        assertTrue(testTask.checkIfTimed());
        assertThrows(IllegalArgumentException.class, () -> testTask.setTimeLimit(-1));

        testTask.setType(1);
        assertEquals(1, testTask.getType());
        assertEquals("main", testTask.getTypeStr());
        testTask.setType(2);
        assertEquals("daily", testTask.getTypeStr());
        testTask.setType(3);
        assertEquals("weekly", testTask.getTypeStr());
        assertThrows(IllegalArgumentException.class, () -> testTask.setType(-1));
        assertThrows(IllegalArgumentException.class, () -> testTask.setType(4));

        testTask.complete();
        assertTrue(testTask.isComplete());

        //default constructor - blank object created.
        Task testTask2 = new Task();
        assertEquals(0, testTask2.getId());
        assertTrue(testTask2.getTitle().isEmpty());
        assertNull(testTask2.getDesc());
        assertEquals(0, testTask2.getBaseQuality());
        assertEquals(0, testTask2.getTimeLimit());
        assertFalse(testTask2.checkIfTimed());
        assertEquals(0, testTask2.getType());
        assertEquals("default",testTask2.getTypeStr());
        assertFalse(testTask2.isComplete());


    }
}
