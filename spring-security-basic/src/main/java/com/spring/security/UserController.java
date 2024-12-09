package com.spring.security;

import com.spring.security.dto.RegisterUserRequest;
import com.spring.security.dto.UpdateUserRequest;
import com.spring.security.dto.UserResponse;
import com.spring.security.model.Role;
import com.spring.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponse>> listAllUsers() {
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    @PutMapping("/admin/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> updateUserDetailsById(
            @PathVariable Long id, @RequestBody UpdateUserRequest userRequest
    ){
        UserResponse userResponse = userService.updateUserById(id, userRequest);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @PatchMapping("/admin/update-role/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> updateRoleByUsername(
            @PathVariable String username,
            @RequestBody Role role
            ) {
        return new ResponseEntity<>(userService.updateRoleByUsername(username, role), HttpStatus.OK);
    }

    @PatchMapping("/admin/update-password/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> resetPasswordByUsername(
            @PathVariable String username, @RequestBody String password
    ) {
        userService.resetPassword(username, password);
        return ResponseEntity.ok().build();
    }
}
