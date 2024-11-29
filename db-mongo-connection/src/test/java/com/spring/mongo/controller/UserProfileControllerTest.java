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
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
                .andExpect(jsonPath("$.[0].username").value("johndoe"))
                .andExpect(jsonPath("$.[0].active").value(false));
    }

    @Test
    public void get_by_id_should_return_user_profile_and_status_200() throws Exception {
        String id = "1234";
        when(userProfileService.findUserProfileById(id)).thenReturn(Optional.ofNullable(profileResponse));

        mockMvc.perform(get("/api/user-profile/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("johndoe"))
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void update_by_id_should_update_user_profile_and_return_user_profile_with_status_updated() throws Exception {
        String id = "1234";
        profileRequest = new UserProfileRequest("john doe", "john.doe29@springbox.com","9876543200", true);
        profileResponse = new UserProfileResponse("1234", "john doe", "john.doe29@springbox.com","9876543200",true);

        when(userProfileService.updateUserProfileById(eq(id), any(UserProfileRequest.class))).thenReturn(profileResponse);

        mockMvc.perform(put("/api/user-profile/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(profileRequest)))
                .andExpect(jsonPath("$.username").value("john doe"))
                .andExpect(jsonPath("$.active").value(true));
    }
}