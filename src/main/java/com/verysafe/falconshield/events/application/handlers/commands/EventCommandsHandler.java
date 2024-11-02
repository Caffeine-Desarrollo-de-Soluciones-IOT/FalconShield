package com.verysafe.falconshield.events.application.handlers.commands;

import com.verysafe.falconshield.events.application.dto.request.EventRequestDto;
import com.verysafe.falconshield.events.domain.model.entities.Event;
import com.verysafe.falconshield.events.domain.services.commands.IEventCommands;
import com.verysafe.falconshield.events.infrastructure.repositories.IEventRepository;
import org.springframework.stereotype.Service;

@Service
public class EventCommandsHandler implements IEventCommands {
    private final IEventRepository eventRepository;

    public EventCommandsHandler(IEventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public void createEvent(EventRequestDto request) {
        var newEvent = new Event();
        newEvent.setTitle(request.title());
        newEvent.setDescription(request.description());
        newEvent.setType(request.type());
        eventRepository.save(newEvent);

    }
}
