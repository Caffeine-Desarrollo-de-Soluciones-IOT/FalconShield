package com.verysafe.falconshield.areas.application.dto.response;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AreaResponseDto{
    private Long id;
    private String name;
    private String icon;
    
    public AreaResponseDto() {}
}