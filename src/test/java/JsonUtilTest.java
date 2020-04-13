import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonUtilTest {

    @Test
    void toJsonStringTest() throws JsonProcessingException {
        TaskManager testManager = new TaskManager();

        //Not actually testing anything just seeing if it works
        TaskList test = testManager.getDefaultTasks();
        String actualJsonString = JsonUtil.toJsonString(test);
        System.out.println(actualJsonString);

//        String expectedJsonString = "{\n" +
//                "  \"email\" : \"a@b.com\",\n" +
//                "  \"balance\" : 500.0\n" +
//                "}";
//        Assert.assertEquals(expectedJsonString, actualJsonString);
    }

    @Test
    void toFromFileTest() throws IOException, NonExistentTaskException {
        TaskManager testManager = new TaskManager();

        TaskList defaultList = testManager.getDefaultTasks();
        Task firstTask = defaultList.getTaskAt(0);
        JsonUtil.toJsonFile("src/resources/defaultTaskList.json", defaultList);

        ArrayList<Task> taskListFromFile = JsonUtil.listFromJsonFile("src/resources/defaultTaskList.json");
        //testManager.setDefaultTasks

//        Assert.assertEquals(firstObject.getEmail(), accountFromFile.getEmail());
//        Assert.assertEquals(firstObject.getBalance(), accountFromFile.getBalance(), 0.001);
    }
//
//    @Test
//    void listFromJsonFileTest() throws IOException{
//        List<TaskList> accountsFromFile = JsonUtil.listFromJsonFile("src/resources/listFromJsonFileTest.json", TaskList.class);
//        Assert.assertEquals(4, accountsFromFile.size());
//        Assert.assertEquals(1000, accountsFromFile.get(1).getBalance(), 0.001);
//        Assert.assertEquals("f@g.com", accountsFromFile.get(3).getEmail());
//    }
}