package com.spring.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.security.config.SecurityConfig;
import com.spring.security.dto.RegisterUserRequest;
import com.spring.security.dto.UserResponse;
import com.spring.security.model.Role;
import com.spring.security.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@Import(SecurityConfig.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;

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
        RegisterUserRequest userRequest = new RegisterUserRequest("Roshan","password", Role.USER);
        UserResponse userResponse = new UserResponse(id, "Roshan","password", Role.USER);

        when(userService.updateUserById(id, userRequest)).thenReturn(userResponse);

        mockMvc.perform(put("/api/v1/admin/update/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("Roshan"))
                .andExpect(jsonPath("$.role").value(Role.USER.toString()));
    }
}