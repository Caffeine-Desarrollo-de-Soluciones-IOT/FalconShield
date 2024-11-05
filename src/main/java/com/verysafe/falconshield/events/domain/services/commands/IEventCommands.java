package com.verysafe.falconshield.events.domain.services.commands;

import com.verysafe.falconshield.events.application.dto.request.EventRequestDto;
import com.verysafe.falconshield.shared.model.dto.response.ApiResponse;

public interface IEventCommands {
    ApiResponse<Void> createEvent(EventRequestDto request);
}
