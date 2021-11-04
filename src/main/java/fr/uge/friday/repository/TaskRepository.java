package fr.uge.friday.repository;

import fr.uge.friday.entity.Task;
import org.springframework.data.repository.CrudRepository;
import java.util.Date;
import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {

    List<Task> findByDate(Date date);
}
