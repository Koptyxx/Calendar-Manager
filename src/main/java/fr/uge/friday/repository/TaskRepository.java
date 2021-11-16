package fr.uge.friday.repository;

import fr.uge.friday.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByDate(Date date);
    Task findTaskByLocationEquals(String location);

    @Transactional
    long deleteTaskByDateEquals(Date date);

    @Transactional
    long deleteTaskByLocationEquals(String location);
}
