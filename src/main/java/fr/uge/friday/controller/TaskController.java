package fr.uge.friday.controller;


import fr.uge.friday.converter.TaskResponseConverter;
import fr.uge.friday.converter.TaskSaveConverter;
import fr.uge.friday.dto.TaskResponseDTO;
import fr.uge.friday.dto.TaskSaveDTO;
import fr.uge.friday.entity.Task;
import fr.uge.friday.repository.TaskRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskRepository taskRepository;
    private final TaskResponseConverter taskResponseConverter;
    private final TaskSaveConverter taskSaveConverter;

    private TaskController(TaskRepository taskRepository, TaskResponseConverter taskResponseConverter, TaskSaveConverter taskSaveConverter) {
        this.taskRepository = taskRepository;
        this.taskResponseConverter = taskResponseConverter;
        this.taskSaveConverter = taskSaveConverter;
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

    @PostMapping("/save")
    public TaskSaveDTO save(@RequestBody TaskSaveDTO taskSaveDTO){
        Task task = taskSaveConverter.dtoToEntity(taskSaveDTO);
        task = taskRepository.save(task);
        return taskSaveConverter.entityToDTO(task);
    }
}
