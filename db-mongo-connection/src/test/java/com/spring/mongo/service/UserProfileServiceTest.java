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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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

    @Test
    public void find_all_should_return_list_of_user_profiles() {
        when(userProfileRepository.findAll()).thenReturn(List.of(userProfile));
        List<UserProfileResponse> userProfileResponses = userProfileService.findAllUserProfile();

        assertEquals(1, userProfileResponses.size());
        assertEquals("johndoe",userProfileResponses.getFirst().getUsername());
        assertTrue(userProfileResponses.getFirst().isActive());
    }

    @Test
    public void find_by_should_return_user_profile() {
        when(userProfileRepository.findById(any(String.class))).thenReturn(Optional.ofNullable(userProfile));

        Optional<UserProfileResponse> userProfileResponse = userProfileService.findUserProfileById(userProfile.getId());

        assertTrue(userProfileResponse.isPresent());
        assertEquals("johndoe",userProfileResponse.get().getUsername());
    }

    @Test
    public void update_by_id_should_return_updated_user_profile() {
        UserProfile existingProfile = new UserProfile("1", "john_doe", "john@example.com", "1234567890", true);
        UserProfile updatedProfile = new UserProfile("1", "john_updated", "john_updated@example.com", "9876543210", false);
        UserProfileRequest userProfileRequest = new UserProfileRequest("john_updated", "john_updated@example.com", "9876543210", false);

        when(userProfileRepository.findById("1")).thenReturn(Optional.of(existingProfile));
        when(userProfileRepository.save(any(UserProfile.class))).thenReturn(updatedProfile);

        UserProfileResponse userProfileResponse = userProfileService.updateUserProfileById("1", userProfileRequest);

        assertEquals("john_updated", userProfileResponse.getUsername());
        assertEquals("john_updated@example.com", userProfileResponse.getEmail());
        assertFalse(userProfileResponse.isActive());
    }

    @Test
    public void delete_user_by_id_should_delete_user_profile() {
        String id = "1";
        when(userProfileRepository.existsById(id)).thenReturn(true);
        userProfileRepository.deleteById(id);

        verify(userProfileRepository, times(1)).deleteById(id);

    }
}