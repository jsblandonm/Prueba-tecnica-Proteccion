package com.Proteccion.taskColab.TareasColaborativo.DTO;

import java.time.LocalDateTime;

public record TaskRequestDTO(
        String title,
        String description,
        String status,
        LocalDateTime createdAt,
        Long assignedToId
) {
}
