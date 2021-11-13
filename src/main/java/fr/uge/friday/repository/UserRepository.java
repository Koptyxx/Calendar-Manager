package fr.uge.friday.repository;

import fr.uge.friday.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {

    /*List<User> findAllByNameEquals(String str);
    List<User> findUsersByPasswordEquals(String str);*/
}
