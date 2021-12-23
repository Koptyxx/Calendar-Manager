package fr.uge.friday.repository;

import fr.uge.friday.entity.Task;
import fr.uge.friday.entity.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {

    Optional<Task> findTaskByDateEquals(Date date);
    List<Task> findTaskByUserEquals(User user);
    @NotNull Optional<Task> findById(@NotNull UUID uuid);

    @Transactional
    void deleteTasksByUser(User user);

    @Transactional
    void deleteTaskByIdTask(UUID uuid);

}
