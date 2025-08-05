package com.Proteccion.taskColab.TareasColaborativo.controller;

import com.Proteccion.taskColab.TareasColaborativo.DTO.UserResponseDTO;
import com.Proteccion.taskColab.TareasColaborativo.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User Management", description = "Endpoints para la gestión de usuarios")
public class UserController {

    @Autowired
    private  UserService userService;

    @Operation(
            summary = "Get all users",
            description = "Returns a list of all registered users",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of users returned successfully")
            }
    )
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }


    @Operation(
            summary = "Get user by ID",
            description = "Returns a user by its ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User returned successfully"),
                    @ApiResponse(responseCode = "404", description = "User not found")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        UserResponseDTO user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/admins-only")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> getAdminData() {
        return ResponseEntity.ok("This is a secret message for admins!");
    }
}
