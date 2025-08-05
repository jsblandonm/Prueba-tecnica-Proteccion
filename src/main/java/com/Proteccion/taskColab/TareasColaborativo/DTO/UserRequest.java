package com.Proteccion.taskColab.TareasColaborativo.DTO;

public record UserRequest(
        String username,
        String password,
        String role
) {
}
