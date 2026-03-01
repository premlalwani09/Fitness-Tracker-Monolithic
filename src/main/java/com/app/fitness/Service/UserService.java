package com.app.fitness.Service;

import com.app.fitness.DTO.LoginRequest;
import com.app.fitness.DTO.LoginResponse;
import com.app.fitness.DTO.RegisterRequest;
import com.app.fitness.DTO.UserResponse;

public interface UserService {

    UserResponse registerUser(RegisterRequest registerRequest);

    LoginResponse authenticate(LoginRequest loginRequest);
}
