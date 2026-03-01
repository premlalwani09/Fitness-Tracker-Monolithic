package com.app.fitness.DTO;

import com.app.fitness.Enum.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {

    private String id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Role role;
}
