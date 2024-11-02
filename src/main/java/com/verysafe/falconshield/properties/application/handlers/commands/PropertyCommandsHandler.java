package com.verysafe.falconshield.properties.application.handlers.commands;

import com.verysafe.falconshield.properties.application.dto.request.RegisterPropertyRequestDto;
import com.verysafe.falconshield.properties.domain.model.entities.Property;
import com.verysafe.falconshield.properties.domain.model.entities.PropertyRegistration;
import com.verysafe.falconshield.properties.domain.services.commands.IPropertyCommands;
import com.verysafe.falconshield.properties.infrastructure.repositories.IPropertyRegistrationRepository;
import com.verysafe.falconshield.properties.infrastructure.repositories.IPropertyRepository;
import com.verysafe.falconshield.shared.exception.CustomException;
import com.verysafe.falconshield.shared.exception.ResourceNotFoundException;
import com.verysafe.falconshield.shared.model.dto.response.ApiResponse;
import com.verysafe.falconshield.user.profile.infrastructure.repositories.IUserProfileRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class PropertyCommandsHandler implements IPropertyCommands {
    private final IPropertyRegistrationRepository propertyRegistrationRepository;
    private final IPropertyRepository propertyRepository;
    private final IUserProfileRepository userProfileRepository;

    public PropertyCommandsHandler(IPropertyRegistrationRepository propertyRegistrationRepository, IPropertyRepository propertyRepository, IUserProfileRepository userProfileRepository) {
        this.propertyRegistrationRepository = propertyRegistrationRepository;
        this.propertyRepository = propertyRepository;
        this.userProfileRepository = userProfileRepository;
    }

    //TODO add area
    @Override
    public ApiResponse<Object> registerProperty(String accountId, RegisterPropertyRequestDto request) {
        var userProfile = userProfileRepository.findByAccountId(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("User Profile", "accountId", accountId));

        // Crear una nueva propiedad con el nombre y la dirección del DTO
        var newProperty = new Property();
        newProperty.setName(request.name());
        newProperty.setAddress(request.address());
        newProperty.setImageUrl(request.image_url());
        var savedProperty = propertyRepository.save(newProperty);

        // Crear el registro de la propiedad asociada al usuario
        var propertyRegistration = new PropertyRegistration();
        propertyRegistration.setProperty(savedProperty);
        propertyRegistration.setUserProfile(userProfile);
        propertyRegistrationRepository.save(propertyRegistration);

        return new ApiResponse<>("Property registered successfully", true, null);
    }

    @Override
    public ApiResponse<Object> unregisterProperty(String accountId, Long id) {
        var propertyRegistration = propertyRegistrationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Property Registration", "id", id));

        //check if the property registration belongs to the user
        if (!propertyRegistration.getUserProfile().getAccountId().equals(accountId)) {
            throw new CustomException(HttpStatus.FORBIDDEN, "Unauthorized", "You are not authorized to unregister this property");
        }

        propertyRegistrationRepository.delete(propertyRegistration);

        return new ApiResponse<>("Property unregistered successfully", true, null);
    }
}
