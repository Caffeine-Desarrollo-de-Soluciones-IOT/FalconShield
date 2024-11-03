package com.verysafe.falconshield.areas.application.dto.response;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AreaResponseDto {
    private Long id;
    private String name;
    private String icon;
    private String color;
    private LocalDateTime registeredAt;

    public AreaResponseDto() {}
}