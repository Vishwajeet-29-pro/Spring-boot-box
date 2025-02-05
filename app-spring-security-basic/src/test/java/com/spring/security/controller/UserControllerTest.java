package com.spring.security.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.security.config.SecurityConfig;
import com.spring.security.dto.RegisterUserRequest;
import com.spring.security.dto.UpdateUserRequest;
import com.spring.security.dto.UserResponse;
import com.spring.security.model.Role;
import com.spring.security.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@Import(SecurityConfig.class)
@SuppressWarnings("unused")
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private UserService userService;

    @Test
    void registerUser_shouldReturnCreatedAndUserResponse() throws Exception {
        RegisterUserRequest userRequest = new RegisterUserRequest( "john", "password", Role.USER);
        UserResponse userResponse = new UserResponse(1L, "john", Role.USER);
        when(userService.createUser(any(RegisterUserRequest.class))).thenReturn(userResponse);

        mockMvc.perform(post("/api/v1/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("john"))
                .andExpect(jsonPath("$.role").value(Role.USER.toString()));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldFetchAllUsers_whenAdminRole() throws Exception {
        when(userService.findAllUsers()).thenReturn(List.of(
                new UserResponse(1L, "John","", Role.ADMIN),
                new UserResponse(2L, "Robin", "", Role.USER)
                ));

        mockMvc.perform(get("/api/v1/admin")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].username").value("John"))
                .andExpect(jsonPath("$[1].role").value("USER"));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void shouldReturnForbidden_whenUserTriesToFetchesAllUsers() throws Exception {
        mockMvc.perform(get("/api/v1/admin")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void shouldUpdateUserDetails_whenAdminRole() throws Exception {
        Long id = 1L;
        UpdateUserRequest userRequest = new UpdateUserRequest("Roshan","password", Role.USER);
        UserResponse userResponse = new UserResponse(id, "Roshan","password", Role.USER);

        when(userService.updateUserById(id, userRequest)).thenReturn(userResponse);

        mockMvc.perform(put("/api/v1/admin/update/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("Roshan"))
                .andExpect(jsonPath("$.role").value(Role.USER.toString()));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void shouldUpdateUserRoleByUsername_whenAdminRole() throws Exception {
        String username = "john";
        UserResponse userResponse = new UserResponse(1L, "john", "password", Role.ADMIN);
        when(userService.updateRoleByUsername(username, Role.ADMIN)).thenReturn(userResponse);

        mockMvc.perform(patch("/api/v1/admin/update-role/{username}", username)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(Role.ADMIN)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.role").value(Role.ADMIN.toString()));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void shouldUpdatePassword_whenAdminRole() throws Exception {
        String username = "john";
        String password = "new-password";

        doNothing().when(userService).resetPassword(username, password);

        mockMvc.perform(patch("/api/v1/admin/update-password/{username}", username)
                .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(password)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void when_delete_by_should_delete_user() throws Exception {
        Long id = 1L;
        doNothing().when(userService).deleteUserById(id);

        mockMvc.perform(delete("/api/v1/admin/delete/{id}", id))
                .andExpect(status().isNoContent());

        verify(userService).deleteUserById(id);
    }

    @Test
    @WithMockUser(roles = "USER")
    public void findByUsernameShouldReturnUser_whenRoleIsUser() throws Exception {
        String username = "john";
        UserResponse userResponse = new UserResponse(1L, "john", Role.USER);
        when(userService.findByUsername(username)).thenReturn(userResponse);

        mockMvc.perform(get("/api/v1/user/{username}", username)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.username").value(userResponse.getUsername()))
                .andExpect(jsonPath("$.role").value(userResponse.getRole().toString()));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void findByUsernameShouldReturnUser_whenRoleIsAdmin() throws Exception {
        String username = "john";
        UserResponse userResponse = new UserResponse(1L, "john", Role.USER);
        when(userService.findByUsername(username)).thenReturn(userResponse);

        mockMvc.perform(get("/api/v1/user/{username}", username)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.username").value(userResponse.getUsername()))
                .andExpect(jsonPath("$.role").value(userResponse.getRole().toString()));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void userNameExistsShouldReturnFalse_whenUsernameNotFound() throws Exception {
        String username = "john";
        when(userService.usernameExists(username)).thenReturn(false);

        mockMvc.perform(get("/api/v1/user/check-username/{username}", username)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void usernameExistsShouldReturnTrue_whenUsernameFound() throws Exception {
        String username = "john";
        when(userService.usernameExists(username)).thenReturn(true);

        mockMvc.perform(get("/api/v1/user/check-username/{username}", username)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound());

    }
}
