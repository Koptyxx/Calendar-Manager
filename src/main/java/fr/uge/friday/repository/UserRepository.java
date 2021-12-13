package fr.uge.friday.repository;

import fr.uge.friday.entity.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findUserByUsernameEquals(String str);

    @Transactional
    long deleteUserByUsername(String name);

    @NotNull Optional<User> findById(@NotNull UUID id);
}
