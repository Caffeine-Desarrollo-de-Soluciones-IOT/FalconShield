package com.verysafe.falconshield.areas.application.dto.request;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RegisterAreaRequestDto(
    @NotNull(message = "Area ID is required")
    @Min(value = 1, message = "Area ID must be greater than 0")
    Long areaId,

    @NotEmpty(message = "Name is required" )
    String name,

    @NotEmpty(message = "Icon is required")
    String icon,

    @NotEmpty(message = "Color is required" )
    String color
) {}