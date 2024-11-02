package com.verysafe.falconshield.areas.domain.services.queries;
import com.verysafe.falconshield.areas.application.dto.response.AreaResponseDto;
import com.verysafe.falconshield.areas.application.dto.response.RegisteredAreaResponseDto;
import com.verysafe.falconshield.shared.model.dto.response.ApiResponse;

import java.util.List;

public interface IAreaQueries {
    ApiResponse<AreaResponseDto> getAreaById(Long id);
    ApiResponse<List<RegisteredAreaResponseDto>> getRegisteredAreas(String propertyId);
}
