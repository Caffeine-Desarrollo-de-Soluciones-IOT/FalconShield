package com.verysafe.falconshield.events.domain.services.commands;

import com.verysafe.falconshield.events.application.dto.request.EventRequestDto;

public interface IEventCommands {
    void createEvent(EventRequestDto request);
}
