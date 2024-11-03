package com.verysafe.falconshield.devices.application.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RegisterDeviceRequestDto(
        @NotNull(message = "Device ID is required")
        @Min(value = 1, message = "Device ID must be greater than 0")
        Long deviceId,

        @NotEmpty(message = "Registration code is required")
        String registrationCode,

        @NotNull(message = "Area ID is required")
        @Min(value = 1, message = "Area ID must be greater than 0")
        Long areaId
) {}
