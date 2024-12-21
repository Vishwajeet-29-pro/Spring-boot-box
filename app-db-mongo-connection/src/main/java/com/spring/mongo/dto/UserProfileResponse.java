package com.spring.mongo.dto;

import com.spring.mongo.model.UserProfile;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO for user profile response")
public class UserProfileResponse {

    @Schema(description = "Unique identifier of the user profile", example = "64f51ae0e4b0c7d6f4f8a1a9")
    private String id;

    @Schema(description = "Username of the user", example = "johndoe")
    private String username;

    @Schema(description = "Email address of the user", example = "johndoe@example.com")
    private String email;

    @Schema(description = "Phone number of the user", example = "+1234567890")
    private String phone;

    @Schema(description = "Whether the user is active", example = "true")
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
