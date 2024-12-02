package com.spring.security.service;

import com.spring.security.dto.RegisterUserRequest;
import com.spring.security.dto.UserResponse;
import com.spring.security.exception.UserNotFoundException;
import com.spring.security.model.Role;
import com.spring.security.model.User;
import com.spring.security.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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

    @Test
    public void findByUsername_shouldReturnUserResponse() {
        when(userRepository.findByUsername(mockUser.getUsername())).thenReturn(Optional.of(mockUser));

        UserResponse userResponse = userService.findByUsername(mockUser.getUsername());

        assertNotNull(userResponse);
        assertEquals(mockUser.getUsername(), userResponse.getUsername());
        assertEquals(mockUser.getRole(), userResponse.getRole());

        verify(userRepository).findByUsername(mockUser.getUsername());
    }

    @Test
    public void userNotFound_shouldThrowUserNotFoundException() {
        when(userRepository.findByUsername(any(String.class))).thenReturn(Optional.empty());

        UserNotFoundException ex = assertThrows(UserNotFoundException.class,
                () -> userService.findByUsername("non-existing-user"));

        assertEquals("User with non-existing-user not found", ex.getMessage());
    }

    @Test
    public void updateUserById_shouldReturnUpdatedUser() {
        Long id = 1L;
        RegisterUserRequest userRequest = new RegisterUserRequest("New John", "new-password", Role.ADMIN);
        User updatedUser = new User(id, "New John", "password123", Role.ADMIN);
        updatedUser.setPassword("new-password");
        when(userRepository.findById(id)).thenReturn(Optional.of(mockUser));
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);

        UserResponse userResponse = userService.updateUserById(id, userRequest);

        assertNotNull(userResponse);
        assertEquals("New John", userResponse.getUsername());
        assertEquals("new-password", userResponse.getPassword());
        assertEquals(Role.ADMIN, userResponse.getRole());
    }

    @Test
    public void ifUserNotFound_shouldThrowUserNotFoundException() {
        Long id = 22L;
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        UserNotFoundException exception = assertThrows(UserNotFoundException.class,
                () -> userService.updateUserById(id, mockUserRequest));

        assertEquals("User with 22 not found", exception.getMessage());
    }
}