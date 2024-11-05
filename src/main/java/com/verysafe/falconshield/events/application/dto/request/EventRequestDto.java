package com.verysafe.falconshield.events.application.dto.request;

import com.verysafe.falconshield.events.domain.model.enums.EEventType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record EventRequestDto (
        @NotBlank(message = "Title is required")
        String title,

        @NotBlank(message = "Title is required")
        String description,

        @NotNull(message = "Type is required")
        EEventType type,

        @NotNull(message = "Property ID is required")
        @Positive(message = "Property ID must be positive")
        long propertyId
) {}
