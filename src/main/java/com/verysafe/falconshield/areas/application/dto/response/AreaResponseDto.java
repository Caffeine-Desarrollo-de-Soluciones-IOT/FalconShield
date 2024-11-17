package com.verysafe.falconshield.areas.application.dto.response;
import com.verysafe.falconshield.properties.application.dto.response.PropertyResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * DTO for {@link com.verysafe.falconshield.areas.domain.model.entities.Area}
 */
@Getter
@Setter
public class AreaResponseDto {
    private Long id;
    private String name;
    private String icon;
    private String color;
    private PropertyResponseDto property;
    private LocalDateTime registeredAt;

    public AreaResponseDto() {}
}