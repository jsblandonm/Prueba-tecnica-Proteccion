package com.Proteccion.taskColab.TareasColaborativo.DTO;

import com.Proteccion.taskColab.TareasColaborativo.model.Task;

import java.time.LocalDateTime;

public record TaskResponseDTO(
        Long id,
        String title,
        String description,
        String status,
        LocalDateTime createdAt,
        UserResponseDTO assignedToId
) {
}
