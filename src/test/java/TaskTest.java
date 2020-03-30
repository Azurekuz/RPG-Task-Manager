import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TaskTest {
    @Test
    public void TaskTest(){
        //id, title, desc, quality, timeLimit, type, complete
        Task testTask = new Task(0, "title1","desc1",0, -1, 0, false);

        assertEquals(0, testTask.getID());
        assertEquals("title1", testTask.getTitle());
        assertEquals("desc1", testTask.getDesc());
        assertEquals(0, testTask.getQuality());

        assertEquals(-1, testTask.getTimeLimit());
        assertEquals(false, testTask.isTimed());

        assertEquals(0, testTask.getTypeInt());
        assertEquals("default", testTask.getTypeStr());
        assertEquals(false, testTask.isComplete());

        testTask.setID(1);
        assertEquals(1, testTask.getID());
        assertThrows(IllegalArgumentException.class, () -> testTask.setID(-1));

        testTask.setTitle("newtitle1");
        assertEquals("newtitle1", testTask.getTitle());

        testTask.setDesc("newdesc1", testTask.getDesc());
        assertEquals("newdesc1", testTask.getDesc());

        testTask.setQuality(10);
        assertEquals(10, testTask.getQuality());
        assertThrows(IllegalArgumentException.class, () -> testTask.setQuality(-1));

        testTask.setTimeLimit(1);
        assertEquals(1, testTask.getTimeLimit());
        assertEquals(true, testTask.isTimed());
        testTask.setTimeLimit(3600);
        assertEquals(3600, testTask.getTimeLimit());
        assertEquals(true, testTask.isTimed());
        assertThrows(IllegalArgumentException.class, () -> testTask.setTimeLimit(-2));

        testTask.setType(1);
        assertEquals(1, testTask.getTypeInt());
        assertEquals("main", testTask.getTypeStr());
        testTask.setType(2);
        assertEquals("daily", testTask.getTypeStr());
        testTask.setType(3);
        assertEquals("weekly", testTask.getTypeStr());
        assertThrows(IllegalArgumentException.class, () -> testTask.setType(-1));
        assertThrows(IllegalArgumentException.class, () -> testTask.setType(4));

        testTask.complete();
        assertEquals(true, testTask.isComplete());


    }
}
