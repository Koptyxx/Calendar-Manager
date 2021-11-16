package fr.uge.friday.repository;

import fr.uge.friday.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    List<User> findAllByNameEquals(String str);
    User findUserByNameEquals(String str);

    @Transactional
    long deleteUserByName(String name);
}
