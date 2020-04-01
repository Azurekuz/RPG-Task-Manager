import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


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
            testTaskList.addTask(0, "title1", "desc1", 0, 0, 0, false);
            testTaskList.addTask(1, "title1", "desc2", 0, 0, 0, false);
            testTaskList.addTask(2, "title1", "desc3", 0, 0, 0, false);
            testTaskList.addTask(3, "title1", "desc4", 0, 0, 0, true);
            testTaskList.addTask(4, "title1", "desc5", 0, 0, 0, true);

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



        }
    }




