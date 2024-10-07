package com.verysafe.falconshield.devices.application.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record RegisterDeviceRequestDto(
        @NotEmpty(message = "Device ID is required")
        String deviceId,

        @NotEmpty(message = "Registration code is required")
        String registrationCode,

        @NotEmpty(message = "Area ID is required")
        String areaId
) {}
