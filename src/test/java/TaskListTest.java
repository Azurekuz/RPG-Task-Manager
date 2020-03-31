import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TaskListTest {

    public class TaskListTest {
        @Test
        public void TaskListTest() {
            Task testTasks = new Task(0, "title1", "desc1", 0, 0, 0, false);
            Task testTasks2 = new Task(1, "title1", "desc2", 0, 0, 0, false);
            Task testTasks3 = new Task(2, "title1", "desc3", 0, 0, 0, false);
            Task testTasks4 = new Task(3, "title1", "desc4", 0, 0, 0, true);
            Task testTasks5 = new Task(4, "title1", "desc5", 0, 0, 0, true);
            TaskList testTaskList = new TaskList();
            //id, title, desc, quality, timeLimit, type, complete
            testTaskList.addTask(testTasks);
            testTaskList.addTask(testTasks2);
            testTaskList.addTask(testTasks3);
            testTaskList.addTask(testTasks4);
            testTaskList.addTask(testTasks5);

            testTaskList.removeTask(0);
            testTaskList.removeTask(0);
            testTaskList.removeTask(0);
            testTaskList.removeTask(0);
            testTaskList.removeTask(0);
            testTaskList.editTask(0);


            testTaskList.editTask(0);
            testTaskList.editTask(0);
            testTaskList.editTask(0);
            testTaskList.editTask(0);
            testTaskList.editTask(0);

            assertEquals(0, testTask.getID());
            assertEquals("title1", testTask.getTitle());
            assertEquals("desc1", testTask.getDesc());
            assertEquals(0, testTask.getQuality());

            assertEquals(0, testTask.getTimeLimit());
            assertEquals(false, testTask.isTimed());

            assertEquals(0, testTask.getTypeInt());
            assertEquals("default", testTask.getTypeStr());
            assertEquals(false, testTask.isComplete());

            testTask.setID(1);
            assertEquals(1, testTask.getID());
            assertThrows(IllegalArgumentException.class, () -> testTask.setID(-1));

            testTask.setTitle("newtitle1");
            assertEquals("newtitle1", testTask.getTitle());

            testTask.setDesc("newdesc1");
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
            assertThrows(IllegalArgumentException.class, () -> testTask.setTimeLimit(-1));

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

            //default constructor - blank object created.
            Task testTask2 = new Task();
            assertEquals(0, testTask2.getID());
            assertNull(testTask2.getTitle());
            assertNull(testTask2.getDesc());
            assertEquals(0, testTask2.getQuality());
            assertEquals(0, testTask2.getTimeLimit());
            assertFalse(testTask2.isTimed());
            assertEquals(0, testTask2.getTypeInt());
            assertEquals("default", testTask2.getTypeStr());
            assertFalse(testTask2.isComplete());


        }
    }
}
