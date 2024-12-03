package com.spring.security.service;

import com.spring.security.dto.RegisterUserRequest;
import com.spring.security.dto.UserResponse;
import com.spring.security.exception.UserNotFoundException;
import com.spring.security.model.User;
import com.spring.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse createUser(RegisterUserRequest userRequest) {
        String encryptedPassword = passwordEncoder.encode(userRequest.getPassword());
        userRequest.setPassword(encryptedPassword);
        User savedUser = userRepository.save(RegisterUserRequest.toUser(userRequest));
        return UserResponse.userResponse(savedUser);
    }

    public UserResponse findByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(UserResponse::userResponse)
                .orElseThrow(() -> new UserNotFoundException("User with "+username +" not found"));
    }

    public UserResponse updateUserById(Long id, RegisterUserRequest userRequest) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User with "+id+" not found")
        );
        user.setUsername(userRequest.getUsername());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setRole(userRequest.getRole());
        User updatedUser = userRepository.save(user);
        return UserResponse.userResponse(updatedUser);
    }

    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User with id "+id+" not found");
        }
        userRepository.deleteById(id);
    }

    public List<UserResponse> findAllUsers() {
        return userRepository.findAll().stream()
                .map(UserResponse::userResponse)
                .toList();
    }
}
