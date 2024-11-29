package com.spring.mongo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.mongo.dto.UserProfileRequest;
import com.spring.mongo.dto.UserProfileResponse;
import com.spring.mongo.service.UserProfileService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserProfileController.class)
class UserProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserProfileService userProfileService;

    private UserProfileRequest profileRequest;
    private UserProfileResponse profileResponse;

    @BeforeEach
    public void setup() {
        profileRequest = new UserProfileRequest("johndoe", "john.doe@springbox.com","9876543210", false);
        profileResponse = new UserProfileResponse("1234", "johndoe", "john.doe@springbox.com","9876543210",false);
    }

    @Test
    public void create_user_profile_should_return_user_profile_and_status_code_201() throws Exception {
        when(userProfileService.createProfile(any(UserProfileRequest.class))).thenReturn(profileResponse);

        mockMvc.perform(post("/api/user-profile")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(profileRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("johndoe"))
                .andExpect(jsonPath("$.active", Matchers.is(false)));
    }

    @Test
    public void find_user_profile_should_return_list_of_user_profiles() throws Exception {
        when(userProfileService.findAllUserProfile()).thenReturn(List.of(profileResponse));

        mockMvc.perform(get("/api/user-profile")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$.[0].username").value(1))
                .andExpect(jsonPath("$.[0].active").value(false));
    }

}