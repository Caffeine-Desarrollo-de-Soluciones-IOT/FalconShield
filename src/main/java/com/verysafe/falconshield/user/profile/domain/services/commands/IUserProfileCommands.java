package com.verysafe.falconshield.user.profile.domain.services.commands;

import com.verysafe.falconshield.shared.model.dto.response.ApiResponse;
import com.verysafe.falconshield.user.profile.application.dto.request.UserProfileRequestDto;

public interface IUserProfileCommands {
    ApiResponse<Object> createUserProfile(String accountId, UserProfileRequestDto request);
}
