package fr.uge.friday.dbManager;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {


    List<User> findAllByNameEquals(String str);
    List<User> findUsersByPasswordEquals(String str);
}
