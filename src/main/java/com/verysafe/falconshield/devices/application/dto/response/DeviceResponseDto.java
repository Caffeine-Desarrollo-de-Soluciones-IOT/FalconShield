package com.verysafe.falconshield.devices.application.dto.response;

import com.verysafe.falconshield.devices.domain.model.enums.EDeviceType;
import com.verysafe.falconshield.devices.domain.model.values.DeviceSpecs;

public record DeviceResponseDto(
    String id,
    String name,
    String description,
    String imageUrl,
    String brand,
    String model,
    EDeviceType type,
    DeviceSpecs specs
) {}
