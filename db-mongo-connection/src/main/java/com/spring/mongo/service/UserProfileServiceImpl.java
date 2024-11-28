package com.spring.mongo.service;

import com.spring.mongo.dto.UserProfileRequest;
import com.spring.mongo.dto.UserProfileResponse;
import com.spring.mongo.model.UserProfile;
import com.spring.mongo.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;

    @Override
    public UserProfileResponse createProfile(UserProfileRequest userProfileRequest) {
        UserProfile userProfile = userProfileRepository.save(UserProfileRequest.toUserProfile(userProfileRequest));
        return UserProfileResponse.userProfileResponse(userProfile);
    }

    @Override
    public List<UserProfileResponse> findAllUserProfile() {
        return userProfileRepository.findAll().stream()
                .map(UserProfileResponse::userProfileResponse)
                .toList();
    }

    @Override
    public Optional<UserProfileResponse> findUserProfileById(String id) {
        UserProfile userProfile = userProfileRepository.findById(id).orElseThrow();
        return Optional.of(UserProfileResponse.userProfileResponse(userProfile));
    }

    @Override
    public UserProfileResponse updateUserProfileById(String id, UserProfileRequest userProfileRequest) {
        Optional<UserProfile> existingUserProfile = userProfileRepository.findById(id);
        if (existingUserProfile.isPresent()) {
            UserProfile userProfile = existingUserProfile.get();
            userProfile.setUsername(userProfileRequest.getUsername());
            userProfile.setEmail(userProfileRequest.getEmail());
            userProfile.setPhone(userProfileRequest.getPhone());
            userProfile.setActive(userProfile.isActive());

            UserProfile updatedUserProfile = userProfileRepository.save(userProfile);
            return UserProfileResponse.userProfileResponse(updatedUserProfile);
        } else {
            throw new RuntimeException("User Profile not found");
        }
    }

    @Override
    public void deleteUserProfileById(String id) {

    }
}
