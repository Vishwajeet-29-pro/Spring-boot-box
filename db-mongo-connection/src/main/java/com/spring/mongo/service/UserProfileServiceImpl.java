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
        return null;
    }

    @Override
    public UserProfileResponse updateUserProfileById(String id, UserProfileRequest userProfileRequest) {
        return null;
    }

    @Override
    public void deleteUserProfileById(String id) {

    }
}
