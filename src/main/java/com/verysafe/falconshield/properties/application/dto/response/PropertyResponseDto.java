package com.verysafe.falconshield.properties.application.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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
