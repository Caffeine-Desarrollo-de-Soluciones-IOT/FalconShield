package com.verysafe.falconshield.properties.application.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropertyResponseDto {
    private Long id;
    private String name;
    private String imageUrl;
    private String address;

    public PropertyResponseDto() {}
}
