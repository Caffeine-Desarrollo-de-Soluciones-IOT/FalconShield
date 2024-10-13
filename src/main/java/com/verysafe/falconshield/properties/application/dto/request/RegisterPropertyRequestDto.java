package com.verysafe.falconshield.properties.application.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RegisterPropertyRequestDto(
        /*@NotNull(message = "Property ID is required")
        @Min(value = 1, message = "Property ID must be greater than 0")
        Long propertyId,*/

        @NotEmpty(message = "Name is required")
        String name,

        @NotEmpty(message = "Image is required")
        String image_url,

        @NotEmpty(message = "Address is required")
        String address
) {}
