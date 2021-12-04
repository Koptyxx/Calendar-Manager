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

            var getUsername = root.get("username");
            var getPass = root.get("password");
            String username;
            String password;

            if(getUsername != null && getPass != null){
                username = root.path("username").asText();
                password = root.path("password").asText();
                userRepository.save(new User(username, password));
            }
        }
    }

    public static void addTaskWithJsonFile(UserRepository userRepository, TaskRepository taskRepository, String src) throws IOException {
        for(JsonNode root : parse(src)){

            var getDesc = root.get("description");
            var getLoc = root.get("location");
            var getUser = root.get("username");
            String description;
            String location;
            String username;

            if(getDesc != null && getLoc != null && getUser != null){
                description = root.path("description").asText();
                location = root.path("location").asText();
                username = root.path("username").asText();
                var user = userRepository.findUserByUsernameEquals(username);
                taskRepository.save(new Task(new Date(), description, location, user));
                System.out.println(description + " " + location);
            }
        }
    }
}


