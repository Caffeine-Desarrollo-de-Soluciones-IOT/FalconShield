package com.verysafe.falconshield.devices.application.handlers.queries;

import com.verysafe.falconshield.devices.application.dto.response.DeviceCatalogResponseDto;
import com.verysafe.falconshield.devices.application.dto.response.RegisteredDeviceResponseDto;
import com.verysafe.falconshield.devices.domain.services.queries.IDeviceQueries;
import com.verysafe.falconshield.devices.infrastructure.repositories.IDeviceCategoryRepository;
import com.verysafe.falconshield.devices.infrastructure.repositories.IDeviceRegistrationRepository;
import com.verysafe.falconshield.devices.infrastructure.repositories.IDeviceRepository;
import com.verysafe.falconshield.devices.application.dto.response.DeviceResponseDto;
import com.verysafe.falconshield.shared.exception.ResourceNotFoundException;
import com.verysafe.falconshield.shared.model.dto.response.ApiResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceQueriesHandler implements IDeviceQueries {
    private final ModelMapper modelMapper;
    private final IDeviceRepository deviceRepository;
    private final IDeviceCategoryRepository deviceCategoryRepository;
    private final IDeviceRegistrationRepository deviceRegistrationRepository;

    public DeviceQueriesHandler(ModelMapper modelMapper, IDeviceRepository deviceRepository, IDeviceCategoryRepository deviceCategoryRepository, IDeviceRegistrationRepository deviceRegistrationRepository) {
        this.modelMapper = modelMapper;
        this.deviceRepository = deviceRepository;
        this.deviceCategoryRepository = deviceCategoryRepository;
        this.deviceRegistrationRepository = deviceRegistrationRepository;
    }

    @Override
    public ApiResponse<List<DeviceCatalogResponseDto>> getDeviceCatalog() {
        var catalog = deviceCategoryRepository.findAll();
        var responseData = catalog.stream()
                .map(item -> modelMapper.map(item, DeviceCatalogResponseDto.class))
                .toList();

        return new ApiResponse<>("Ok", true, responseData);
    }

    @Override
    public ApiResponse<DeviceResponseDto> getDeviceById(Long id) {
        var workspace = deviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Device", "id", id));
        var responseData = modelMapper.map(workspace, DeviceResponseDto.class);

        return new ApiResponse<>("Ok", true, responseData);
    }

    @Override
    public ApiResponse<List<RegisteredDeviceResponseDto>> getRegisteredDevices(String accountId) {
        var devices = deviceRegistrationRepository.findAllByUserProfileAccountId(accountId);
        var responseData = devices.stream()
                .map(item -> modelMapper.map(item, RegisteredDeviceResponseDto.class))
                .toList();

        return new ApiResponse<>("Ok", true, responseData);
    }
}