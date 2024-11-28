package com.spring.mongo.dto;

import com.spring.mongo.model.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileResponse {
    private String id;
    private String username;
    private String email;
    private String phone;
    private boolean isActive;

    public static UserProfileResponse userProfileResponse(UserProfile userProfile) {
        return new UserProfileResponse(
                userProfile.getId(),
                userProfile.getUsername(),
                userProfile.getEmail(),
                userProfile.getPhone(),
                userProfile.isActive()
        );
    }
}
