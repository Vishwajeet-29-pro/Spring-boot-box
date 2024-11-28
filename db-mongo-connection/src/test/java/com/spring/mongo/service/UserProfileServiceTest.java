package com.spring.mongo.service;

import com.spring.mongo.dto.UserProfileRequest;
import com.spring.mongo.dto.UserProfileResponse;
import com.spring.mongo.model.UserProfile;
import com.spring.mongo.repository.UserProfileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserProfileServiceTest {

    @Mock
    private UserProfileRepository userProfileRepository;
    private UserProfileService userProfileService;
    private UserProfile userProfile;

    @BeforeEach
    public void setup() {
        userProfileService = new UserProfileServiceImpl(userProfileRepository);
        userProfile = new UserProfile(null,"johndoe","john.doe@springbox.com","9876543210",true);
    }

    @Test
    public void create_userprofile_should_return_userprofile() {
        UserProfileRequest userProfileRequest = new UserProfileRequest("johndoe","john.doe@springbox.com","9876543210",true);
        when(userProfileRepository.save(any(UserProfile.class))).thenReturn(userProfile);

        UserProfileResponse userProfileResponse = userProfileService.createProfile(userProfileRequest);
        assertNotNull(userProfileResponse);
        assertEquals("johndoe",userProfileResponse.getUsername());
        assertTrue(userProfileResponse.isActive());
    }
}