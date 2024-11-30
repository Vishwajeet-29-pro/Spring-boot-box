package com.spring.mongo.dto;

import com.spring.mongo.model.UserProfile;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO for user profile creation")
public class UserProfileRequest {

    @Schema(description = "Username of the user", example = "johndoe")
    private String username;

    @Schema(description = "Email address of the user", example = "johndoe@example.com")
    private String email;

    @Schema(description = "Phone number of the user", example = "+1234567890")
    private String phone;

    @Schema(description = "Whether the user is active", example = "true")
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
