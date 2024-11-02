package com.verysafe.falconshield.events.application.dto.request;

import com.verysafe.falconshield.events.domain.model.enums.EEventType;

public record EventRequestDto (
        String title,
        String description,
        EEventType type
) {}
