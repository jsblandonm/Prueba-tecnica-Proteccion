package com.Proteccion.taskColab.TareasColaborativo.service.task;

import com.Proteccion.taskColab.TareasColaborativo.DTO.TaskRequestDTO;
import com.Proteccion.taskColab.TareasColaborativo.DTO.TaskResponseDTO;

import java.util.List;

public interface TaskService {
    TaskResponseDTO saveTask(TaskRequestDTO taskRequestDTO);

    List<TaskResponseDTO> getAllUserTasks();

    TaskResponseDTO getTaskById(Long id);

    void updateTask(TaskRequestDTO taskRequestDTO);

    void deleteTask(Long id);
}
