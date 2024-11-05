package com.verysafe.falconshield.properties.application.handlers.commands;

import com.verysafe.falconshield.properties.application.dto.request.RegisterPropertyRequestDto;
import com.verysafe.falconshield.properties.domain.model.entities.Property;
import com.verysafe.falconshield.properties.domain.services.commands.IPropertyCommands;
import com.verysafe.falconshield.properties.infrastructure.repositories.IPropertyRepository;
import com.verysafe.falconshield.shared.exception.CustomException;
import com.verysafe.falconshield.shared.exception.ResourceNotFoundException;
import com.verysafe.falconshield.shared.model.dto.response.ApiResponse;
import com.verysafe.falconshield.user.profile.infrastructure.repositories.IUserProfileRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class PropertyCommandsHandler implements IPropertyCommands {
    private final IPropertyRepository propertyRepository;
    private final IUserProfileRepository userProfileRepository;

    public PropertyCommandsHandler(IPropertyRepository propertyRepository, IUserProfileRepository userProfileRepository) {
        this.propertyRepository = propertyRepository;
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public ApiResponse<Object> registerProperty(String accountId, RegisterPropertyRequestDto request) {
        var userProfile = userProfileRepository.findByAccountId(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("User Profile", "accountId", accountId));

        // Crear una nueva propiedad con el nombre y la dirección del DTO
        var newProperty = new Property();
        newProperty.setName(request.name());
        newProperty.setAddress(request.address());
        newProperty.setImageUrl(request.imageUrl());
        newProperty.setUserProfile(userProfile);
        propertyRepository.save(newProperty);

        return new ApiResponse<>("Property registered successfully", true, null);
    }

    @Override
    public ApiResponse<Object> unregisterProperty(String accountId, Long id) {
        var propertyRegistration = propertyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Property Registration", "id", id));

        //check if the property registration belongs to the user
        if (!propertyRegistration.getUserProfile().getAccountId().equals(accountId)) {
            throw new CustomException(HttpStatus.FORBIDDEN, "Unauthorized", "You are not authorized to unregister this property");
        }

        propertyRepository.delete(propertyRegistration);

        return new ApiResponse<>("Property unregistered successfully", true, null);
    }
}
