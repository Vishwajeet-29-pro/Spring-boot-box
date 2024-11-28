package com.spring.mongo.dto;

import com.spring.mongo.model.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileRequest {
    private String username;
    private String email;
    private String phone;
    private boolean isActive;

    public static UserProfile toUserProfile(UserProfileRequest userProfileRequest) {
        UserProfile userProfile = new UserProfile();
        userProfile.setUsername(userProfileRequest.getUsername());
        userProfile.setEmail(userProfileRequest.getEmail());
        userProfile.setPhone(userProfileRequest.getPhone());
        userProfile.setActive(userProfileRequest.isActive());

        return userProfile;
    }
}
