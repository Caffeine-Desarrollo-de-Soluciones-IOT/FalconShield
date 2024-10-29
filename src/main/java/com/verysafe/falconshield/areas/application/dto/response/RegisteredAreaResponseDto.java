package com.verysafe.falconshield.areas.application.dto.response;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class RegisteredAreaResponseDto{
    private Long id;
    private LocalDateTime registeredAt;
    private AreaResponseDto area;

    public RegisteredAreaResponseDto() {}
}