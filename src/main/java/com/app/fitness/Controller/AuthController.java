package com.app.fitness.Controller;

import com.app.fitness.DTO.LoginRequest;
import com.app.fitness.DTO.LoginResponse;
import com.app.fitness.DTO.RegisterRequest;
import com.app.fitness.DTO.UserResponse;
import com.app.fitness.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    public UserService userService;


    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody RegisterRequest registerRequest){
        return new ResponseEntity<>(userService.registerUser(registerRequest),HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest){
        try {
            return ResponseEntity.ok(userService.authenticate(loginRequest));
        }
        catch (Exception e){
            return ResponseEntity.status(401).build();
        }
    }

}
