package fr.uge.friday.converter;

import fr.uge.friday.dto.TaskSaveDTO;
import fr.uge.friday.entity.Task;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskSaveConverter {

    public TaskSaveDTO entityToDTO(Task task){
        return new TaskSaveDTO(task.getDate(), task.getDescription(), task.getLocation(), task.getUser());
    }

    public Task dtoToEntity(TaskSaveDTO taskSaveDTO){
        return new Task(taskSaveDTO.date(), taskSaveDTO.desc(), taskSaveDTO.loc(), taskSaveDTO.user());
    }

    public List<TaskSaveDTO> entityToDTO(List<Task> tasks){
        return tasks.stream().map(this::entityToDTO).collect(Collectors.toList());
    }

    public List<Task> dtoToEntity(List<TaskSaveDTO> taskSaveDTOS){
        return taskSaveDTOS.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }
}
