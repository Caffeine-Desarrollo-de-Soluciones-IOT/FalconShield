package com.verysafe.falconshield.user.profile.application.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public record UserProfileRequestDto(
        @Pattern(regexp = "^\\d{8}$", message = "DNI must be 8 digits and no letters")
        @NotEmpty(message = "DNI is required")
        String dni,

        String occupation
) {}
