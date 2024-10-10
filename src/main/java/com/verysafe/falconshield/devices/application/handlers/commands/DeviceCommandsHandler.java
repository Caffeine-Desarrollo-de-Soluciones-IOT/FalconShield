package com.verysafe.falconshield.devices.application.handlers.commands;

import com.verysafe.falconshield.devices.application.dto.request.RegisterDeviceRequestDto;
import com.verysafe.falconshield.devices.domain.model.entities.DeviceRegistration;
import com.verysafe.falconshield.devices.domain.services.commands.IDeviceCommands;
import com.verysafe.falconshield.devices.infrastructure.repositories.IDeviceRegistrationRepository;
import com.verysafe.falconshield.devices.infrastructure.repositories.IDeviceRepository;
import com.verysafe.falconshield.shared.exception.CustomException;
import com.verysafe.falconshield.shared.exception.ResourceNotFoundException;
import com.verysafe.falconshield.shared.model.dto.response.ApiResponse;
import com.verysafe.falconshield.user.profile.infrastructure.repositories.IUserProfileRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class DeviceCommandsHandler implements IDeviceCommands {
    private final IDeviceRegistrationRepository deviceRegistrationRepository;
    private final IDeviceRepository deviceRepository;
    private final IUserProfileRepository userProfileRepository;

    public DeviceCommandsHandler(IDeviceRegistrationRepository deviceRegistrationRepository, IDeviceRepository deviceRepository, IUserProfileRepository userProfileRepository) {
        this.deviceRegistrationRepository = deviceRegistrationRepository;
        this.deviceRepository = deviceRepository;
        this.userProfileRepository = userProfileRepository;
    }

    //TODO add area
    @Override
    public ApiResponse<Object> registerDevice(String accountId, RegisterDeviceRequestDto request) {
        var targetDevice = deviceRepository.findById(request.deviceId())
                .orElseThrow(() -> new ResourceNotFoundException("Device", "id", request.registrationCode()));
        var userProfile = userProfileRepository.findByAccountId(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("User Profile", "accountId", accountId));

        var deviceRegistration = new DeviceRegistration();
        deviceRegistration.setRegistrationCode(request.registrationCode());
        deviceRegistration.setDevice(targetDevice);
        deviceRegistration.setUserProfile(userProfile);
        deviceRegistrationRepository.save(deviceRegistration);

        return new ApiResponse<>("Device registered successfully", true, null);
    }

    @Override
    public ApiResponse<Object> unregisterDevice(String accountId, Long id) {
        var deviceRegistration = deviceRegistrationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Device Registration", "id", id));

        //check if the device registration belongs to the user
        if (!deviceRegistration.getUserProfile().getAccountId().equals(accountId)) {
            throw new CustomException(HttpStatus.FORBIDDEN, "Unauthorized", "You are not authorized to unregister this device");
        }

        deviceRegistrationRepository.delete(deviceRegistration);

        return new ApiResponse<>("Device unregistered successfully", true, null);
    }
}
