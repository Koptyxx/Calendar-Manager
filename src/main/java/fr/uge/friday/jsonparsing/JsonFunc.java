package fr.uge.friday.jsonparsing;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.uge.friday.entity.Task;
import fr.uge.friday.entity.User;
import fr.uge.friday.repository.TaskRepository;
import fr.uge.friday.repository.UserRepository;

import java.io.IOException;
import java.util.Date;

public class JsonFunc {

    private static final ObjectMapper objectMapper = getDefaultObjectMapper();

    private static ObjectMapper getDefaultObjectMapper(){
        return new ObjectMapper();
    }

    public static JsonNode parse(String src) throws IOException {
        return objectMapper.readTree(src);
    }

    public static void addUserWithJsonFile(UserRepository userRepository, String src) throws IOException {
        for(JsonNode root : parse(src)){

            var getResult = root.get("user");
            var userPath = root.path("user");
            String username;
            String password;

            if(getResult != null){
                username = userPath.path("username").asText();
                password = userPath.path("password").asText();
                userRepository.save(new User(username, password));
            }
        }
    }

    public static void addTaskWithJsonFile(UserRepository userRepository, TaskRepository taskRepository, String src) throws IOException {
        for(JsonNode root : parse(src)){

            var getResult = root.get("task");
            var taskPath = root.path("task");
            String description;
            String location;
            String username;

            if(getResult != null){
                description = taskPath.path("description").asText();
                location = taskPath.path("location").asText();
                username = taskPath.path("username").asText();
                var user = userRepository.findUserByUsernameEquals(username);
                taskRepository.save(new Task(new Date(), description, location, user));
            }

        }
    }
}


