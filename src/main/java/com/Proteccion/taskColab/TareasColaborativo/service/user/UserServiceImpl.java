package com.Proteccion.taskColab.TareasColaborativo.service.user;

import com.Proteccion.taskColab.TareasColaborativo.DTO.UserRequest;
import com.Proteccion.taskColab.TareasColaborativo.DTO.UserResponseDTO;
import com.Proteccion.taskColab.TareasColaborativo.Repository.UserRepository;
import com.Proteccion.taskColab.TareasColaborativo.model.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + id + " not found"));
        return convertToDTO(user);
    }

    @Override
    public UserResponseDTO createUser(UserRequest userRequestDTO) {
        User user = new User();
        user.setUsername(userRequestDTO.username());
        user.setPassword(passwordEncoder.encode(userRequestDTO.password()));
        user.setRole(userRequestDTO.role());
        userRepository.save(user);
        return convertToDTO(user);
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserRequest userRequestDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + id + " not found"));
        user.setUsername(userRequestDTO.username());
        user.setPassword(passwordEncoder.encode(userRequestDTO.password()));
        user.setRole(userRequestDTO.role());
        userRepository.save(user);
        return convertToDTO(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + id + " not found"));
        userRepository.delete(user);
    }

    private UserResponseDTO convertToDTO(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getRole()
        );
    }
}