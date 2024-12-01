package com.spring.security.service;

import com.spring.security.dto.RegisterUserRequest;
import com.spring.security.dto.UserResponse;
import com.spring.security.model.Role;
import com.spring.security.model.User;
import com.spring.security.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    private UserService userService;
    private User mockUser;
    private RegisterUserRequest mockUserRequest;

    @BeforeEach
    public void setup() {
        userService = new UserService(userRepository, passwordEncoder);
        mockUser = new User(1L, "john","encryptedPassword", Role.USER);
        mockUserRequest = new RegisterUserRequest("john","password123", Role.USER);
    }

    @Test
    public void registerUser_shouldReturnUserAndResponse() {
        when(passwordEncoder.encode(mockUserRequest.getPassword())).thenReturn("encryptedPassword");
        when(userRepository.save(any(User.class))).thenReturn(mockUser);

        UserResponse userResponse = userService.createUser(mockUserRequest);

        assertNotNull(userResponse);
        assertEquals(mockUser.getId(), userResponse.getId());
        assertEquals(mockUser.getUsername(), userResponse.getUsername());
        assertEquals("encryptedPassword", userResponse.getPassword());
        assertEquals(mockUser.getRole(), userResponse.getRole());

        verify(passwordEncoder).encode("password123");
        verify(userRepository).save(any(User.class));
    }
}