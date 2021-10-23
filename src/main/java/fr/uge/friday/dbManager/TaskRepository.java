package fr.uge.friday.dbManager;

import org.springframework.data.repository.CrudRepository;
import java.util.Date;
import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {

    List<Task> findByDate(Date date);
}
