package com.Proteccion.taskColab.TareasColaborativo.service;

import com.Proteccion.taskColab.TareasColaborativo.DTO.AuthResponse;
import com.Proteccion.taskColab.TareasColaborativo.DTO.LoginRequest;
import com.Proteccion.taskColab.TareasColaborativo.DTO.RegisterRequest;
import com.Proteccion.taskColab.TareasColaborativo.Repository.UserRepository;
import com.Proteccion.taskColab.TareasColaborativo.config.jwt.JwtService;
import com.Proteccion.taskColab.TareasColaborativo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements AuthServiceImpl {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthService(PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, UserRepository userRepository, JwtService jwtService) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        User user = userRepository.findByUsername(request.username()).orElseThrow();
        String token = jwtService.getToken(user);
        return new AuthResponse(token);
    }


    @Override
    public AuthResponse register(RegisterRequest request) {
        User user = new User();
        user.setUsername(request.username());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole("ROLE_USER");

        userRepository.save(user);

        String token = jwtService.getToken(user);

        System.out.println("Generated Token on Register: " + token);
        return new AuthResponse(token);
    }
}

