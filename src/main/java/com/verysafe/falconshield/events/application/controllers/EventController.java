package com.verysafe.falconshield.events.application.controllers;

import com.verysafe.falconshield.events.application.dto.request.EventRequestDto;
import com.verysafe.falconshield.events.application.dto.response.EventResponseDto;
import com.verysafe.falconshield.events.domain.services.commands.IEventCommands;
import com.verysafe.falconshield.events.domain.services.queries.IEventQueries;
import com.verysafe.falconshield.shared.model.dto.response.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Device Events")
@RestController
@RequestMapping("/api/events")
public class EventController {
    private final IEventQueries eventQueries;
    private final IEventCommands eventCommands;

    public EventController(IEventQueries eventQueries, IEventCommands eventCommands) {
        this.eventQueries = eventQueries;
        this.eventCommands = eventCommands;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Void>> createEvent(@RequestBody @Valid EventRequestDto request) {
        var res = eventCommands.createEvent(request);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @GetMapping("/all/{propertyId}")
    public ResponseEntity<ApiResponse<List<EventResponseDto>>> getAllEventsByPropertyId(@PathVariable long propertyId) {
        var res = eventQueries.getEventsByPropertyId(propertyId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
