package com.verysafe.falconshield.devices.application.dto.response;

import com.verysafe.falconshield.devices.domain.model.enums.EDeviceType;
import com.verysafe.falconshield.devices.domain.model.values.DeviceSpecs;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeviceResponseDto {
    private String id;
    private String name;
    private String description;
    private String imageUrl;
    private String brand;
    private String model;
    private EDeviceType type;
    private DeviceSpecs specs;

    public DeviceResponseDto() {}
}
