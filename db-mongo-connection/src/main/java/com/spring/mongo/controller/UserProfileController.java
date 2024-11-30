package com.spring.mongo.controller;

import com.spring.mongo.dto.UserProfileRequest;
import com.spring.mongo.dto.UserProfileResponse;
import com.spring.mongo.service.UserProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user-profile")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @Operation(summary = "Create a new user profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User profile created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserProfileResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input provided")
    })
    @PostMapping
    public ResponseEntity<UserProfileResponse> createUserProfile(@RequestBody UserProfileRequest userProfileRequest) {
        UserProfileResponse userProfileResponse = userProfileService.createProfile(userProfileRequest);
        return new ResponseEntity<>(userProfileResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Retrieve all user profiles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of user profiles retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserProfileResponse.class)))
    })
    @GetMapping
    public ResponseEntity<List<UserProfileResponse>> findAllUserProfile() {
        List<UserProfileResponse> userProfileResponses = userProfileService.findAllUserProfile();
        return ResponseEntity.ok(userProfileResponses);
    }

    @Operation(summary = "Retrieve a user profile by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User profile retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserProfileResponse.class))),
            @ApiResponse(responseCode = "404", description = "User profile not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Optional<UserProfileResponse>> findUserProfileById(@PathVariable("id") String id) {
        Optional<UserProfileResponse> userProfileResponse = userProfileService.findUserProfileById(id);
        return ResponseEntity.ok(userProfileResponse);
    }

    @Operation(summary = "Update a user profile by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User profile updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserProfileResponse.class))),
            @ApiResponse(responseCode = "404", description = "User profile not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<UserProfileResponse> updateUserProfileById(
            @PathVariable("id") String id, @RequestBody UserProfileRequest userProfileRequest) {
        return ResponseEntity.ok(userProfileService.updateUserProfileById(id, userProfileRequest));
    }

    @Operation(summary = "Delete a user profile by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User profile deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User profile not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserProfileById(@PathVariable("id") String id) {
        userProfileService.deleteUserProfileById(id);
        return ResponseEntity.noContent().build();
    }
}
