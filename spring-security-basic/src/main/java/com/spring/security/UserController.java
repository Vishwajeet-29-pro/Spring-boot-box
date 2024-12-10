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
@SuppressWarnings("unused")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody RegisterUserRequest userRequest) {
        UserResponse userResponse = userService.createUser(userRequest);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

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

    @DeleteMapping("/admin/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{username}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<UserResponse> findByUsername(@PathVariable String username) {
        UserResponse userResponse = userService.findByUsername(username);
        return new ResponseEntity<>(userResponse, HttpStatus.FOUND);
    }

    @GetMapping("/user/check-username/{username}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Void> usernameExists(@PathVariable String username) {
        boolean exists = userService.usernameExists(username);
        return exists ? ResponseEntity.status(HttpStatus.FOUND).build()
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
