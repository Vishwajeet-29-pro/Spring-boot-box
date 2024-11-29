package com.spring.mongo.controller;

import com.spring.mongo.dto.UserProfileRequest;
import com.spring.mongo.dto.UserProfileResponse;
import com.spring.mongo.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-profile")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @PostMapping
    public ResponseEntity<UserProfileResponse> createUserProfile(@RequestBody UserProfileRequest userProfileRequest) {
        UserProfileResponse userProfileResponse = userProfileService.createProfile(userProfileRequest);
        return new ResponseEntity<>(userProfileResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserProfileResponse>> findAllUserProfile() {
        List<UserProfileResponse> userProfileResponses = userProfileService.findAllUserProfile();
        return ResponseEntity.ok(userProfileResponses);
    }
}
