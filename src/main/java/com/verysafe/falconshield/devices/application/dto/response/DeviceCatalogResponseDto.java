package com.verysafe.falconshield.devices.application.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class DeviceCatalogResponseDto {
    private String id;
    private String name;
    private String icon;
    private List<DeviceResponseDto> devices;

    public DeviceCatalogResponseDto() {}
}
