package com.verysafe.falconshield.properties.domain.services.queries;
import com.verysafe.falconshield.properties.application.dto.response.AreaResponseDto;
import com.verysafe.falconshield.properties.application.dto.response.RegisteredAreaResponseDto;
import com.verysafe.falconshield.shared.model.dto.response.ApiResponse;

import java.util.List;

public interface IPropertyQueries {
    ApiResponse<AreaResponseDto> getPropertyById(Long id);
    ApiResponse<List<RegisteredAreaResponseDto>> getRegisteredProperties(String accountId);
}
