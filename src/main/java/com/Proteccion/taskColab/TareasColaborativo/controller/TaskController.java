package com.Proteccion.taskColab.TareasColaborativo.controller;

import com.Proteccion.taskColab.TareasColaborativo.DTO.TaskRequestDTO;
import com.Proteccion.taskColab.TareasColaborativo.DTO.TaskResponseDTO;
import com.Proteccion.taskColab.TareasColaborativo.service.task.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@Tag(name = "Task Management", description = "Endpoints para la gestión de tareas")
public class TaskController {

    @Autowired
    private  TaskService taskService;

    @Operation(summary = "Crear una nueva tarea", description = "Crea una nueva tarea y la asigna a un usuario especificado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tarea creada exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TaskResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Petición inválida (ej. datos faltantes)", content = @Content),
            @ApiResponse(responseCode = "401", description = "No autorizado", content = @Content)
    })
    @PostMapping
    public ResponseEntity<TaskResponseDTO> saveTask(@RequestBody TaskRequestDTO request) {
        return ResponseEntity.ok(taskService.saveTask(request));
    }

    @Operation(summary = "Obtener tareas del usuario", description = "Devuelve una lista de tareas. Los Admins ven todas; los Users solo las suyas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de tareas obtenida",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TaskResponseDTO.class))),
            @ApiResponse(responseCode = "401", description = "No autorizado", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> getAllUserTasks() {
        return ResponseEntity.ok(taskService.getAllUserTasks());
    }

    @Operation(summary = "Obtener una tarea", description = "Devuelve una tarea por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarea obtenida",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TaskResponseDTO.class))),
            @ApiResponse(responseCode = "401", description = "No autorizado", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @Operation(summary = "Actualizar una tarea", description = "Actualiza una tarea por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarea actualizada",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TaskResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Petición inválida (ej. datos faltantes)", content = @Content),
            @ApiResponse(responseCode = "401", description = "No autorizado", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable Long id, @RequestBody TaskRequestDTO request) {
        taskService.updateTask(request);
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @Operation(summary = "Eliminar una tarea", description = "Elimina una tarea por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarea eliminada",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TaskResponseDTO.class))),
            @ApiResponse(responseCode = "401", description = "No autorizado", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok().build();
    }
}
