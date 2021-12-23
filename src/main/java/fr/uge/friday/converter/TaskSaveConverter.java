package fr.uge.friday.converter;

import fr.uge.friday.dto.TaskSaveDTO;
import fr.uge.friday.entity.Task;
import fr.uge.friday.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskSaveConverter {

    private final UserRepository userRepository;

    private TaskSaveConverter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public TaskSaveDTO entityToDTO(Task task){
        return new TaskSaveDTO(task.getDate(), task.getDescription(), task.getLocation(), task.getUser().getName());
    }

    public Task dtoToEntity(TaskSaveDTO taskSaveDTO){

        var user = userRepository.findUserByUsernameEquals(taskSaveDTO.user());
        return user.map(value -> new Task(taskSaveDTO.date(), taskSaveDTO.desc(), taskSaveDTO.loc(), value)).orElse(null);
    }

    public List<TaskSaveDTO> entityToDTO(List<Task> tasks){
        return tasks.stream().map(this::entityToDTO).collect(Collectors.toList());
    }

    public List<Task> dtoToEntity(List<TaskSaveDTO> taskSaveDTOS){
        return taskSaveDTOS.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }
}
