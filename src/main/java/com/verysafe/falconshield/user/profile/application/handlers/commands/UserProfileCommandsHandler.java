package com.verysafe.falconshield.user.profile.application.handlers.commands;

import com.verysafe.falconshield.shared.exception.CustomException;
import com.verysafe.falconshield.shared.model.dto.response.ApiResponse;
import com.verysafe.falconshield.user.profile.application.dto.request.UserProfileRequestDto;
import com.verysafe.falconshield.user.profile.domain.model.entities.UserProfile;
import com.verysafe.falconshield.user.profile.domain.services.commands.IUserProfileCommands;
import com.verysafe.falconshield.user.profile.infrastructure.repositories.IUserProfileRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserProfileCommandsHandler implements IUserProfileCommands {
    private final IUserProfileRepository userProfileRepository;

    public UserProfileCommandsHandler(IUserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public ApiResponse<Object> createUserProfile(String accountId, UserProfileRequestDto request) {
        if (userProfileRepository.findByAccountId(accountId).isPresent()) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "Error creating profile", "User Profile already exists for this account");
        }

        var userProfile = new UserProfile();
        userProfile.setAccountId(accountId);
        userProfile.setDni(request.dni());
        userProfile.setOccupation(request.occupation());
        userProfileRepository.save(userProfile);

        return new ApiResponse<>("User Profile created", true, null);
    }
}
