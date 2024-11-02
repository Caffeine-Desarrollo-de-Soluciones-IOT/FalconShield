package com.verysafe.falconshield.events.application.dto.response;

import com.verysafe.falconshield.events.domain.model.enums.EEventType;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * DTO for {@link com.verysafe.falconshield.events.domain.model.entities.Event}
 */
@Setter
@Getter
public class EventResponseDto {
    private Long id;
    private String title;
    private String description;
    private Instant timestamp;
    private EEventType type;

    public EventResponseDto() {}
}
