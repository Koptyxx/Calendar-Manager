package fr.uge.friday.converter;

import fr.uge.friday.dto.TaskResponseDTO;
import fr.uge.friday.entity.Task;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskResponseConverter {

    public TaskResponseDTO entityToDTO(Task task){
        return new TaskResponseDTO(task.getId(), task.getDate(), task.getDescription(), task.getLocation());
    }

    public List<TaskResponseDTO> entityToDto(List<Task> tasks){
        return tasks.stream().map(this::entityToDTO).collect(Collectors.toList());
    }
}
