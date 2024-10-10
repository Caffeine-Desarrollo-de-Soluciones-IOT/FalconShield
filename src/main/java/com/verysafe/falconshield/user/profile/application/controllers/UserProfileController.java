package com.verysafe.falconshield.user.profile.application.controllers;

import com.verysafe.falconshield.shared.model.dto.response.ApiResponse;
import com.verysafe.falconshield.user.profile.application.dto.request.UserProfileRequestDto;
import com.verysafe.falconshield.user.profile.application.dto.response.UserProfileResponseDto;
import com.verysafe.falconshield.user.profile.domain.services.commands.IUserProfileCommands;
import com.verysafe.falconshield.user.profile.domain.services.queries.IUserProfileQueries;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User Profile")
@RestController
@RequestMapping("/api/profile")
public class UserProfileController {
    private final IUserProfileQueries userProfileQueries;
    private final IUserProfileCommands userProfileCommands;

    public UserProfileController(IUserProfileQueries userProfileQueries, IUserProfileCommands userProfileCommands) {
        this.userProfileQueries = userProfileQueries;
        this.userProfileCommands = userProfileCommands;
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse<UserProfileResponseDto>> getUserProfile(@AuthenticationPrincipal Jwt principal) {
        var userProfile = userProfileQueries.getUserProfile(principal.getSubject());
        return new ResponseEntity<>(userProfile, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Object>> createUserProfile(
            @AuthenticationPrincipal Jwt principal,
            @RequestBody @Valid UserProfileRequestDto request
    ) {
        var res = userProfileCommands.createUserProfile(principal.getSubject(), request);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
