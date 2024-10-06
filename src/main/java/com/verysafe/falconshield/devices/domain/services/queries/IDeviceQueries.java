package com.verysafe.falconshield.devices.domain.services.queries;

import com.verysafe.falconshield.devices.application.dto.response.DeviceCatalogResponseDto;
import com.verysafe.falconshield.devices.application.dto.response.DeviceResponseDto;
import com.verysafe.falconshield.shared.model.dto.response.ApiResponse;

import java.util.List;

public interface IDeviceQueries {
    ApiResponse<List<DeviceCatalogResponseDto>> getDeviceCatalog();
    ApiResponse<List<DeviceResponseDto>> getDevices();
    ApiResponse<DeviceResponseDto> getDeviceById(String id);
}
