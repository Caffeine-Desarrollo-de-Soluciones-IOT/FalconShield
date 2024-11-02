package com.verysafe.falconshield.events.application.handlers.queries;

import com.verysafe.falconshield.events.application.dto.response.EventResponseDto;
import com.verysafe.falconshield.events.domain.services.queries.IEventQueries;
import com.verysafe.falconshield.events.infrastructure.repositories.IEventRepository;
import com.verysafe.falconshield.shared.model.dto.response.ApiResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventQueriesHandler implements IEventQueries {
    private final IEventRepository eventRepository;
    private final ModelMapper modelMapper;

    public EventQueriesHandler(IEventRepository eventRepository, ModelMapper modelMapper) {
        this.eventRepository = eventRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApiResponse<List<EventResponseDto>> getEventsByPropertyId(long propertyId) {
        var events = eventRepository.findAll();
        var res = events.stream()
                .map(event -> modelMapper.map(event, EventResponseDto.class))
                .toList();
        return new ApiResponse<>("Ok", true, res);
    }
}
