package com.spring.security.dto;

import com.spring.security.model.Role;
import com.spring.security.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String username;
    private String password;
    private Role role;

    public UserResponse(Long id, String username, Role role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public static UserResponse UserResponseWithOutPassword(User user) {
        return new UserResponse(user.getId(), user.getUsername(), user.getRole());
    }

    public static UserResponse userResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getRole()
        );
    }
}
