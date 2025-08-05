package com.Proteccion.taskColab.TareasColaborativo.controller;

import com.Proteccion.taskColab.TareasColaborativo.DTO.TaskRequestDTO;
import com.Proteccion.taskColab.TareasColaborativo.DTO.TaskResponseDTO;
import com.Proteccion.taskColab.TareasColaborativo.service.task.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")

public class TaskController {

    @Autowired
    private  TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponseDTO> saveTask(@RequestBody TaskRequestDTO request) {
        return ResponseEntity.ok(taskService.saveTask(request));
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> getAllUserTasks() {
        return ResponseEntity.ok(taskService.getAllUserTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable Long id, @RequestBody TaskRequestDTO request) {
        taskService.updateTask(request);
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok().build();
    }
}
