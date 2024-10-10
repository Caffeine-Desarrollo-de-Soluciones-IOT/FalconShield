package com.verysafe.falconshield.devices.application.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RegisteredDeviceResponseDto {
    private Long id;
    private LocalDateTime registeredAt;
    private DeviceResponseDto device;

    public RegisteredDeviceResponseDto() {}
}
