package com.Proteccion.taskColab.TareasColaborativo.service.user;

import com.Proteccion.taskColab.TareasColaborativo.DTO.UserRequest;
import com.Proteccion.taskColab.TareasColaborativo.DTO.UserResponseDTO;

import java.util.List;

public interface UserService {
    List<UserResponseDTO> getAllUsers();

    UserResponseDTO getUserById(Long id);

    UserResponseDTO createUser(UserRequest userRequestDTO);

    UserResponseDTO updateUser(Long id, UserRequest userRequestDTO);

    void deleteUser(Long id);
}
