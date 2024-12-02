package com.spring.security.service;

import com.spring.security.dto.RegisterUserRequest;
import com.spring.security.dto.UserResponse;
import com.spring.security.model.User;
import com.spring.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
        return user.map(UserResponse::userResponse).orElseThrow();
    }
}
