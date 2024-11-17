package com.verysafe.falconshield.properties.application.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * DTO for {@link com.verysafe.falconshield.properties.domain.model.entities.Property}
 */
@Getter
@Setter
public class PropertyResponseDto {
    private Long id;
    private String name;
    private String imageUrl;
    private String address;
    private LocalDateTime registeredAt;

    public PropertyResponseDto() {}
}
