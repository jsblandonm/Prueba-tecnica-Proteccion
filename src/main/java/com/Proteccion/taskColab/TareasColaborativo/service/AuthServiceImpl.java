package com.Proteccion.taskColab.TareasColaborativo.service;

import com.Proteccion.taskColab.TareasColaborativo.DTO.AuthResponse;
import com.Proteccion.taskColab.TareasColaborativo.DTO.LoginRequest;
import com.Proteccion.taskColab.TareasColaborativo.DTO.RegisterRequest;

public interface AuthServiceImpl {
    AuthResponse login(LoginRequest request);

    AuthResponse register(RegisterRequest request);
}
