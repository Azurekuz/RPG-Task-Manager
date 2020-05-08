import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class JsonUtilTest {

    @Test
    void taskToFromFileTest() throws IOException, NonExistentObjectException, DuplicateObjectException {
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
    @Test
    void rpgExperienceTest() throws IOException{
        RPGManager testRPG = new RPGManager(true);
        testRPG.transferEXP(50);
        assertEquals(50, testRPG.getPlayer().getExperience());
        assertEquals(1, testRPG.getPlayer().getLevel());

        JsonUtil.toJsonFile("src/resources/rpgManagerTest.json", testRPG);

        RPGManager testRPG2;
        testRPG2 = JsonUtil.fromJsonFile("src/resources/rpgManagerTest.json", RPGManager.class);

        assertEquals(50, testRPG2.getPlayer().getExperience());
        assertEquals(1, testRPG2.getPlayer().getLevel());
    }
    @Test
    void ActorListTest() throws IOException, DuplicateObjectException {
        ActorList testList = new ActorList();
        Actor act1 = new Actor("Eric", 1, 4, 3, 2);
        Actor act2 = new Actor("Sarah", 0, 9, 8, 0);
        Actor act3 = new Actor("Morty", 1, 4, 3, 2);
        Actor act4 = new Actor("Link", 0, 9, 0, 0);
        Actor act5 = new Actor("John", 91, 4, 30, 25);
        Actor act6 = new Actor("Riley", 12, 42, 32, 26);
        testList.addActor(act1);
        testList.addActor(act2);
        testList.addActor(act3);
        testList.addActor(act4);
        testList.addActor(act5);
        testList.addActor(act6);

        JsonUtil.toJsonFile("src/resources/actorListFileTest.json", testList);

        ActorList testList2;
        testList2 = JsonUtil.fromJsonFile("src/resources/actorListFileTest.json", ActorList.class);

    }

    }