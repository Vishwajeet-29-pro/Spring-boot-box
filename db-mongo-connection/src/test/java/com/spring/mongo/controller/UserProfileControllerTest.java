package com.spring.mongo.controller;

import com.spring.mongo.dto.UserProfileRequest;
import com.spring.mongo.dto.UserProfileResponse;
import com.spring.mongo.service.UserProfileService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

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



}