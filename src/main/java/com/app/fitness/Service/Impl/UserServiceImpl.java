package com.app.fitness.Service.Impl;

import com.app.fitness.DTO.LoginRequest;
import com.app.fitness.DTO.LoginResponse;
import com.app.fitness.DTO.RegisterRequest;
import com.app.fitness.DTO.UserResponse;
import com.app.fitness.Enum.Role;
import com.app.fitness.Model.User;
import com.app.fitness.Repository.UserRepository;
import com.app.fitness.Security.JwtUtils;
import com.app.fitness.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    public JwtUtils jwtUtils;


    @Override
    public UserResponse registerUser(RegisterRequest registerRequest) {

        Role role = registerRequest.getRole() != null ? registerRequest.getRole() : Role.USER;

        User user = User.builder()
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .role(role)
                .build();

        User savedUser = userRepository.save(user);

        return mapToResponse(savedUser);
    }

    @Override
    public LoginResponse authenticate(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail());
        if (user == null){
            throw new RuntimeException("User not found");
        }
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid Credentials");
        }
        String token = jwtUtils.generateToken(user.getId(), user.getRole().name());
        return new LoginResponse(token, mapToResponse(user));
    }

    public UserResponse mapToResponse(User savedUser) {

        return UserResponse.builder()
                .id(savedUser.getId())
                .email(savedUser.getEmail())
                .password(savedUser.getPassword())
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .role(savedUser.getRole())
                .build();
    }
}
