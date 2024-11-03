package com.verysafe.falconshield.events.domain.services.queries;

import com.verysafe.falconshield.events.application.dto.response.EventResponseDto;
import com.verysafe.falconshield.shared.model.dto.response.ApiResponse;

import java.util.List;

public interface IEventQueries {
    ApiResponse<List<EventResponseDto>> getEventsByPropertyId(long propertyId);
}
