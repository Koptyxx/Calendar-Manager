package fr.uge.friday.repository;

import fr.uge.friday.entity.Event;
import fr.uge.friday.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {

    List<Event> findByUserEquals(User user);

    @Transactional
    void deleteEventByIdCal(UUID id);

    Event findEventByUserEquals(User user);
}
