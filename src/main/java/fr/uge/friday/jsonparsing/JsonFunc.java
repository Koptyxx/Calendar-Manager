package fr.uge.friday.jsonparsing;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.uge.friday.entity.User;
import fr.uge.friday.repository.UserRepository;

import java.io.IOException;

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
}


