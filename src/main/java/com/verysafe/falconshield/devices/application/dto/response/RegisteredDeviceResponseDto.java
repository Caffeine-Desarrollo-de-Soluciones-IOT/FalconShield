package com.verysafe.falconshield.devices.application.dto.response;

import com.verysafe.falconshield.areas.application.dto.response.AreaResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RegisteredDeviceResponseDto {
    private Long id;
    private LocalDateTime registeredAt;
    private DeviceResponseDto device;
    private AreaResponseDto area;

    public RegisteredDeviceResponseDto() {}
}
