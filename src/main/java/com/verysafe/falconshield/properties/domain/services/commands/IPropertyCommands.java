package com.verysafe.falconshield.properties.domain.services.commands;

import com.verysafe.falconshield.properties.application.dto.request.RegisterPropertyRequestDto;
import com.verysafe.falconshield.shared.model.dto.response.ApiResponse;

public interface IPropertyCommands {
    ApiResponse<Object> registerProperty(String accountId, RegisterPropertyRequestDto request);
    ApiResponse<Object> updateProperty(long id, RegisterPropertyRequestDto request);
    ApiResponse<Object> unregisterProperty(String accountId, Long id);
}
