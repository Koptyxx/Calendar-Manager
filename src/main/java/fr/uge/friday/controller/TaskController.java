package fr.uge.friday.controller;


import fr.uge.friday.converter.TaskResponseConverter;
import fr.uge.friday.converter.TaskSaveConverter;
import fr.uge.friday.dto.TaskResponseDTO;
import fr.uge.friday.dto.TaskSaveDTO;
import fr.uge.friday.entity.Task;
import fr.uge.friday.repository.TaskRepository;
import fr.uge.friday.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskRepository taskRepository;
    private final TaskResponseConverter taskResponseConverter;
    private final TaskSaveConverter taskSaveConverter;
    private final UserRepository userRepository;

    public TaskController(TaskRepository taskRepository, TaskResponseConverter taskResponseConverter, TaskSaveConverter taskSaveConverter, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.taskResponseConverter = taskResponseConverter;
        this.taskSaveConverter = taskSaveConverter;
        this.userRepository = userRepository;
    }

    @GetMapping("/findAll")
    public List<TaskResponseDTO> findAll(){
        List<Task> taskList = taskRepository.findAll();
        return taskResponseConverter.entityToDto(taskList);
    }

    @GetMapping("/find/{date}")
    public TaskResponseDTO findByDate(@PathVariable Date date){
        var task = taskRepository.findTaskByDateEquals(date);
        return task.map(taskResponseConverter::entityToDTO).orElse(null);
    }

    @GetMapping("/find/username/{username}")
    public List<TaskResponseDTO> findByDate(@PathVariable String username){
        var user = userRepository.findUserByUsernameEquals(username);
        return taskResponseConverter.entityToDto(Objects.requireNonNull(user.map(taskRepository::findTaskByUserEquals).orElse(null)));
    }

    @PostMapping("/save")
    public TaskSaveDTO save(@RequestBody TaskSaveDTO taskSaveDTO){
        Task task = taskSaveConverter.dtoToEntity(taskSaveDTO);
        task = taskRepository.save(task);
        return taskSaveConverter.entityToDTO(task);
    }
}
