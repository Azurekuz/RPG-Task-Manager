import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
//import java.util.List;

/**
 * @author Original setup: Toby Dragon
 * Modified by Elias Platt
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
        //Found this fix here:https://stackoverflow.com/questions/27952472/serialize-deserialize-java-8-java-time-with-jackson-json-mapper
        mapper.findAndRegisterModules();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        //mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        mapper.writeValue(new File(filename), objectToSerialize);
    }

    public static <T> T fromJsonFile(String filename, Class<? extends T> classToBeCreated) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        return  mapper.readValue(new File(filename), classToBeCreated);
    }

    public static ArrayList<Task> listFromJsonFile(String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        //found this fix here: https://stackoverflow.com/questions/11659844/jackson-deserialize-generic-class-variable
        return mapper.readValue(new File(filename), mapper.getTypeFactory().constructParametricType(ArrayList.class, Task.class));
    }
}