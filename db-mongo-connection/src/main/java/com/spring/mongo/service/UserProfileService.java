package com.spring.mongo.service;

import com.spring.mongo.dto.UserProfileRequest;
import com.spring.mongo.dto.UserProfileResponse;

import java.util.List;
import java.util.Optional;

public interface UserProfileService {
    UserProfileResponse createProfile(UserProfileRequest userProfileRequest);
    List<UserProfileResponse> findAllUserProfile();
    Optional<UserProfileResponse> findUserProfileById(String id);
    UserProfileResponse updateUserProfileById(String id, UserProfileRequest userProfileRequest);
    void deleteUserProfileById(String id);
}
