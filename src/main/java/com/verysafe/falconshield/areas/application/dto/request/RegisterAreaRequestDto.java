package com.verysafe.falconshield.areas.application.dto.request;
import jakarta.validation.constraints.NotEmpty;

public record RegisterAreaRequestDto(
    @NotEmpty(message = "Name is required" )
    String name,

    String icon,

    String color
) {}