package com.verysafe.falconshield.devices.domain.services.commands;

import com.verysafe.falconshield.devices.application.dto.request.RegisterDeviceRequestDto;
import com.verysafe.falconshield.shared.model.dto.response.ApiResponse;

public interface IDeviceCommands {
    ApiResponse<Object> registerDevice(String accountId, RegisterDeviceRequestDto request);
    ApiResponse<Object> unregisterDevice(String accountId, Long id);
}
