import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
//import java.util.List;

/**
 * @author Original setup: Toby Dragon
 */
public class JsonUtil {
    public static String toJsonString(Object objectToSerialize) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper.writeValueAsString( objectToSerialize);
    }

    public static void toJsonFile(String filename, Object objectToSerialize) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.writeValue(new File(filename), objectToSerialize);
    }

    public static <T> T fromJsonFile(String filename, Class<? extends T> classToBeCreated) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return  mapper.readValue(new File(filename), classToBeCreated);
    }

    public static ArrayList<Task> listFromJsonFile(String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        //found this fix here: https://stackoverflow.com/questions/11659844/jackson-deserialize-generic-class-variable
        return mapper.readValue(new File(filename), mapper.getTypeFactory().constructParametricType(ArrayList.class, Task.class));
    }
}