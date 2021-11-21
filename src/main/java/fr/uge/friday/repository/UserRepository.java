package fr.uge.friday.repository;

import fr.uge.friday.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    List<User> findAllByUsernameEquals(String str);
    User findUserByUsernameEquals(String str);

    @Transactional
    long deleteUserByUsername(String name);
}
