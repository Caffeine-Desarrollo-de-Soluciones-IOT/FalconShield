package com.verysafe.falconshield.areas.domain.services.queries;
import com.verysafe.falconshield.areas.application.dto.response.AreaResponseDto;
import com.verysafe.falconshield.shared.model.dto.response.ApiResponse;

import java.util.List;

public interface IAreaQueries {
    ApiResponse<AreaResponseDto> getAreaById(long id);
    ApiResponse<List<AreaResponseDto>> getAllAreas(String accountId);
    ApiResponse<List<AreaResponseDto>> getAreasByPropertyId(long propertyId);
}
