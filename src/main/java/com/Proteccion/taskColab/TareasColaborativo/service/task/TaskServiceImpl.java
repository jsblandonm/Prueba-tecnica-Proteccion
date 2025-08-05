package com.Proteccion.taskColab.TareasColaborativo.service.task;

import com.Proteccion.taskColab.TareasColaborativo.DTO.TaskRequestDTO;
import com.Proteccion.taskColab.TareasColaborativo.DTO.TaskResponseDTO;
import com.Proteccion.taskColab.TareasColaborativo.DTO.UserResponseDTO;
import com.Proteccion.taskColab.TareasColaborativo.Repository.TaskRepository;
import com.Proteccion.taskColab.TareasColaborativo.Repository.UserRepository;
import com.Proteccion.taskColab.TareasColaborativo.model.Task;
import com.Proteccion.taskColab.TareasColaborativo.model.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private  UserRepository userRepository;

    @Override
    public TaskResponseDTO saveTask(TaskRequestDTO taskRequestDTO) {

        User Creator = getCurrentUser();

        User assignedTo = userRepository.findById(taskRequestDTO.assignedToId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Task task = new Task();
        task.setTitle(taskRequestDTO.title());
        task.setDescription(taskRequestDTO.description());
        task.setStatus(taskRequestDTO.status());
        task.setCreatedAt(taskRequestDTO.createdAt());
        task.setAssignedTo(assignedTo);
        Task savedTask = taskRepository.save(task);
        return new TaskResponseDTO(savedTask.getId(), savedTask.getTitle(), savedTask.getDescription(),
                savedTask.getStatus(), savedTask.getCreatedAt(),
                new UserResponseDTO(savedTask.getAssignedTo().getId(), savedTask.getAssignedTo().getUsername(),
                        savedTask.getAssignedTo().getRole()));
    }

    @Override
    public List<TaskResponseDTO> getAllUserTasks() {
        User currentUser = getCurrentUser();
        List<Task> tasks;
        if ("ROLE_ADMIN".equals(currentUser.getRole())) {
            tasks = taskRepository.findAll();
        } else {
            tasks = taskRepository.findByAssignedToId(currentUser.getId());
        }

        return tasks.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TaskResponseDTO getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));
        checkPermission(task);
        return convertToDTO(task);
    }

    @Override
    public void updateTask(TaskRequestDTO taskRequestDTO) {
        Task task = taskRepository.findById(taskRequestDTO.assignedToId())
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));
        checkPermission(task);

        task.setTitle(taskRequestDTO.title());
        task.setDescription(taskRequestDTO.description());
        task.setStatus(taskRequestDTO.status());
        task.setCreatedAt(taskRequestDTO.createdAt());
        taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));
        checkPermission(task);

        taskRepository.delete(task);
    }


    private User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Current user not found"));
    }

    private void checkPermission(Task task) {
        User currentUser = getCurrentUser();
        if (!"ROLE_ADMIN".equals(currentUser.getRole()) && !task.getAssignedTo().getId().equals(currentUser.getId())) {
            throw new SecurityException("You do not have permission to access this task");
        }
    }

    private TaskResponseDTO convertToDTO(Task task) {
        UserResponseDTO userDTO = new UserResponseDTO(
                task.getAssignedTo().getId(),
                task.getAssignedTo().getUsername(),
                task.getAssignedTo().getRole()
        );

        return new TaskResponseDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getCreatedAt(),
                userDTO
        );
    }
}