package com.spring.security.dto;

import com.spring.security.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateUserRequest {
    private String username;
    private String password;
    private Role role;
}
