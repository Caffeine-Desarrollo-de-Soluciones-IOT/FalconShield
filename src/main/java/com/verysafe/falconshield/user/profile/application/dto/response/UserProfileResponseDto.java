package com.verysafe.falconshield.user.profile.application.dto.response;

import com.verysafe.falconshield.user.account.model.Account;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserProfileResponseDto {
    private String id;
    private Account account;
    private String occupation;
    private String dni;

    public UserProfileResponseDto() {}
}
