package com.verysafe.falconshield.user.profile.domain.services.queries;

import com.verysafe.falconshield.shared.model.dto.response.ApiResponse;
import com.verysafe.falconshield.user.profile.application.dto.response.UserProfileResponseDto;

public interface IUserProfileQueries {
    ApiResponse<UserProfileResponseDto> getUserProfile(String accountId);
}
