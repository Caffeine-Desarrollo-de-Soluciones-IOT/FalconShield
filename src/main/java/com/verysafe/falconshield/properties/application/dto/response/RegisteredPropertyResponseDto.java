package com.verysafe.falconshield.properties.application.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RegisteredPropertyResponseDto {
    private Long id;
    private LocalDateTime registeredAt;
    private PropertyResponseDto property;

    public RegisteredPropertyResponseDto() {}
}
