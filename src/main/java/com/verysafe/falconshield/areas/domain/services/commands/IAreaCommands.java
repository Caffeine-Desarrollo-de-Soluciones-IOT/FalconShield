package com.verysafe.falconshield.areas.domain.services.commands;
import com.verysafe.falconshield.areas.application.dto.request.RegisterAreaRequestDto;
import com.verysafe.falconshield.shared.model.dto.response.ApiResponse;

public interface IAreaCommands {
    ApiResponse<Object> registerArea(long propertyId, RegisterAreaRequestDto request);
    ApiResponse<Object> unregisterArea(long areaId);
}
