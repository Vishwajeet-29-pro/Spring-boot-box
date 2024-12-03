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

import java.util.List;
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

    @Test
    public void delete_by_id_not_found_should_throw_exception(){
        Long id = 22L;
        when(userRepository.existsById(id)).thenReturn(false);

        UserNotFoundException exception = assertThrows(
                UserNotFoundException.class,
                () -> userService.deleteUserById(id)
        );

        assertEquals("User with id 22 not found", exception.getMessage());
    }

    @Test
    public void delete_by_should_delete_user() {
        Long id = 1L;
        when(userRepository.existsById(id)).thenReturn(true);
        userService.deleteUserById(id);

        verify(userRepository).deleteById(id);

    }

    @Test
    public void find_all_should_return_list_of_users() {
        when(userRepository.findAll()).thenReturn(List.of(mockUser));

        List<UserResponse> userResponses = userService.findAllUsers();

        assertEquals(1, userResponses.size());
        assertEquals("john", userResponses.getFirst().getUsername());
        assertEquals("encryptedPassword", userResponses.getFirst().getPassword());
        assertEquals(Role.USER, userResponses.getFirst().getRole());
    }

    @Test
    public void update_role_by_username_should_update_role() {
        String user = "john";
        when(userRepository.findByUsername(user)).thenReturn(Optional.of(mockUser));
        mockUser.setRole(Role.ADMIN);
        when(userRepository.save(mockUser)).thenReturn(mockUser);

        UserResponse userResponse = userService.updateRoleById(user, Role.ADMIN);

        assertEquals(Role.ADMIN, userResponse.getRole());
    }
}