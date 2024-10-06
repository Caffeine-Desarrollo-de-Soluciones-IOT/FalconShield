package com.verysafe.falconshield.devices.application.dto.response;

import java.util.List;

public record DeviceCatalogResponseDto(
    String id,
    String name,
    String icon,
    List<DeviceResponseDto> devices
) {}
