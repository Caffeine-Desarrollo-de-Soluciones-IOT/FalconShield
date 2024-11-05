package com.verysafe.falconshield.properties.application.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record RegisterPropertyRequestDto(
        @NotEmpty(message = "Name is required")
        String name,

        @NotEmpty(message = "Image is required")
        String imageUrl,

        @NotEmpty(message = "Address is required")
        String address
) {}
