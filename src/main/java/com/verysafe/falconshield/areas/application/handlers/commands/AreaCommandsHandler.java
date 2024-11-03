package com.verysafe.falconshield.areas.application.handlers.commands;
import com.verysafe.falconshield.areas.application.dto.request.RegisterAreaRequestDto;
import com.verysafe.falconshield.areas.domain.model.entities.Area;
import com.verysafe.falconshield.areas.domain.model.entities.AreaRegistration;
import com.verysafe.falconshield.areas.domain.services.commands.IAreaCommands;
import com.verysafe.falconshield.areas.infrastructure.repositories.IAreaRegistrationRepository;
import com.verysafe.falconshield.areas.infrastructure.repositories.IAreaRepository;
import com.verysafe.falconshield.shared.exception.CustomException;
import com.verysafe.falconshield.shared.exception.ResourceNotFoundException;
import com.verysafe.falconshield.shared.model.dto.response.ApiResponse;
import com.verysafe.falconshield.properties.infrastructure.repositories.IPropertyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AreaCommandsHandler implements IAreaCommands {
    private final IAreaRegistrationRepository areaRegistrationRepository;
    private final IAreaRepository areaRepository;
    private final IPropertyRepository propertyRepository;

    public AreaCommandsHandler(IAreaRegistrationRepository areaRegistrationRepository, IAreaRepository areaRepository, IPropertyRepository propertyRepository) {
        this.areaRegistrationRepository = areaRegistrationRepository;
        this.areaRepository = areaRepository;
        this.propertyRepository = propertyRepository;
    }


    @Override
    public ApiResponse<Object> registerArea(String propertyIdString, RegisterAreaRequestDto request) {
        // Convertir el ID de propiedad de String a Long
        Long propertyId = Long.parseLong(propertyIdString);
    
        // Buscar la propiedad asociada usando su ID
        var property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException("Property", "propertyId", propertyId));
    
        // Crear una nueva instancia de área con los datos del DTO
        var newArea = new Area();
        newArea.setName(request.name());
        newArea.setIcon(request.icon());
        newArea.setColor(request.color());
        var savedArea = areaRepository.save(newArea);
    
        // Crear el registro de área y asociarlo con la propiedad y el área creadas
        var areaRegistration = new AreaRegistration();
        areaRegistration.setArea(savedArea);
        areaRegistration.setProperty(property);
        areaRegistrationRepository.save(areaRegistration);
    
        // Devolver la respuesta
        return new ApiResponse<>("Area registered successfully", true, null);
    }

    @Override
    public ApiResponse<Object> unregisterArea(String propertyId, Long id) {
        var areaRegistration = areaRegistrationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Area Registration", "id", id));

        //check if the area registration belongs to the property
        if (!areaRegistration.getProperty().equals(propertyId)) {
            throw new CustomException(HttpStatus.FORBIDDEN, "Unauthorized", "You are not authorized to unregister this area");
        }

        areaRegistrationRepository.delete(areaRegistration);

        return new ApiResponse<>("Area unregistered successfully", true, null);
    }
}