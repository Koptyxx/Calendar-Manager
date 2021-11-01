package fr.uge.friday.dbManager.repository;

import fr.uge.friday.dbManager.entity.User;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {


    List<User> findAllByNameEquals(String str);
    List<User> findUsersByPasswordEquals(String str);
}
