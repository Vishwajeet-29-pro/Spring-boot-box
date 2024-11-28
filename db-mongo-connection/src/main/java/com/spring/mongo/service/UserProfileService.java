package com.spring.mongo.service;

import com.spring.mongo.dto.UserProfileRequest;
import com.spring.mongo.dto.UserProfileResponse;

import java.util.List;

public interface UserProfileService {
    UserProfileResponse createProfile(UserProfileRequest userProfileRequest);
    List<UserProfileResponse> findAllUserProfile();
    UserProfileResponse findUserProfileById(String id);
    UserProfileResponse updateUserProfileById(String id, UserProfileRequest userProfileRequest);
    void deleteUserProfileById(String id);
}
