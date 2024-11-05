package com.verysafe.falconshield.events.application.handlers.commands;

import com.verysafe.falconshield.events.application.dto.request.EventRequestDto;
import com.verysafe.falconshield.events.domain.model.entities.Event;
import com.verysafe.falconshield.events.domain.services.commands.IEventCommands;
import com.verysafe.falconshield.events.infrastructure.repositories.IEventRepository;
import com.verysafe.falconshield.properties.infrastructure.repositories.IPropertyRepository;
import com.verysafe.falconshield.shared.exception.ResourceNotFoundException;
import com.verysafe.falconshield.shared.model.dto.response.ApiResponse;
import org.springframework.stereotype.Service;

@Service
public class EventCommandsHandler implements IEventCommands {
    private final IEventRepository eventRepository;
    private final IPropertyRepository propertyRepository;

    public EventCommandsHandler(IEventRepository eventRepository, IPropertyRepository propertyRepository) {
        this.eventRepository = eventRepository;
        this.propertyRepository = propertyRepository;
    }

    @Override
    public ApiResponse<Void> createEvent(EventRequestDto request) {
        var property = propertyRepository.findById(request.propertyId())
                .orElseThrow(() -> new ResourceNotFoundException("Property", "id", request.propertyId()));

        var newEvent = new Event();
        newEvent.setTitle(request.title());
        newEvent.setDescription(request.description());
        newEvent.setType(request.type());
        newEvent.setProperty(property);
        eventRepository.save(newEvent);

        return new ApiResponse<>("Event created successfully", true, null);
    }
}
