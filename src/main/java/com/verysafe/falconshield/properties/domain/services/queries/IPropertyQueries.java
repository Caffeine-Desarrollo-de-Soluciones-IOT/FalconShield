package com.verysafe.falconshield.properties.domain.services.queries;

import com.verysafe.falconshield.properties.application.dto.response.PropertyResponseDto;
import com.verysafe.falconshield.properties.application.dto.response.RegisteredPropertyResponseDto;
import com.verysafe.falconshield.shared.model.dto.response.ApiResponse;

import java.util.List;

public interface IPropertyQueries {
    ApiResponse<PropertyResponseDto> getPropertyById(Long id);
    ApiResponse<List<RegisteredPropertyResponseDto>> getRegisteredProperties(String accountId);
}
