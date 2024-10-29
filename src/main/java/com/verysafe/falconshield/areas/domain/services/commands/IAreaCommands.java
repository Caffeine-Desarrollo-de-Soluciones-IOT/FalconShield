package com.verysafe.falconshield.areas.domain.services.commands;
import com.verysafe.falconshield.areas.application.dto.request.RegisterAreaRequesDto;
import com.verysafe.falconshield.shared.model.dto.response.ApiResponse;

public interface IAreaCommands {
    ApiResponse<Object> registerArea(String propertyId, RegisterAreaRequesDto request);
    ApiResponse<Object> unregisterArea(String propertyId, Long id);
}
