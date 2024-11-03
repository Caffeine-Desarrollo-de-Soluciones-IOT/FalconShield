package com.verysafe.falconshield.user.profile.application.handlers.queries;

import com.verysafe.falconshield.shared.exception.ResourceNotFoundException;
import com.verysafe.falconshield.shared.model.dto.response.ApiResponse;
import com.verysafe.falconshield.user.account.service.IAccountService;
import com.verysafe.falconshield.user.profile.application.dto.response.UserProfileResponseDto;
import com.verysafe.falconshield.user.profile.domain.services.queries.IUserProfileQueries;
import com.verysafe.falconshield.user.profile.infrastructure.repositories.IUserProfileRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserProfileQueriesHandler implements IUserProfileQueries {
    private final ModelMapper modelMapper;
    private final IAccountService accountService;
    private final IUserProfileRepository userProfileRepository;

    public UserProfileQueriesHandler(ModelMapper modelMapper, IAccountService accountService, IUserProfileRepository userProfileRepository) {
        this.modelMapper = modelMapper;
        this.accountService = accountService;
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public ApiResponse<UserProfileResponseDto> getUserProfile(String accountId) {
        var userAccount = accountService.getAccountById(accountId);
        var userProfile = userProfileRepository.findByAccountId(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("User Profile", "accountId", accountId));

        var userProfileRes = modelMapper.map(userProfile, UserProfileResponseDto.class);
        userProfileRes.setAccount(userAccount);

        return new ApiResponse<>("Ok", true, userProfileRes);
    }
}
