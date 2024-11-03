package com.verysafe.falconshield.areas.application.handlers.commands;
import com.verysafe.falconshield.areas.application.dto.request.RegisterAreaRequestDto;
import com.verysafe.falconshield.areas.domain.model.entities.Area;
import com.verysafe.falconshield.areas.domain.services.commands.IAreaCommands;
import com.verysafe.falconshield.areas.infrastructure.repositories.IAreaRepository;
import com.verysafe.falconshield.shared.exception.ResourceNotFoundException;
import com.verysafe.falconshield.shared.model.dto.response.ApiResponse;
import com.verysafe.falconshield.properties.infrastructure.repositories.IPropertyRepository;
import org.springframework.stereotype.Service;

@Service
public class AreaCommandsHandler implements IAreaCommands {
    private final IAreaRepository areaRepository;
    private final IPropertyRepository propertyRepository;

    public AreaCommandsHandler(IAreaRepository areaRepository, IPropertyRepository propertyRepository) {
        this.areaRepository = areaRepository;
        this.propertyRepository = propertyRepository;
    }


    @Override
    public ApiResponse<Object> registerArea(long propertyId, RegisterAreaRequestDto request) {
        // Buscar la propiedad asociada usando su ID
        var property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException("Property", "propertyId", propertyId));
    
        // Crear una nueva instancia de área con los datos del DTO
        var newArea = new Area();
        newArea.setName(request.name());
        newArea.setIcon(request.icon());
        newArea.setColor(request.color());
        newArea.setProperty(property);
        areaRepository.save(newArea);
    
        // Devolver la respuesta
        return new ApiResponse<>("Area registered successfully", true, null);
    }

    @Override
    public ApiResponse<Object> unregisterArea(long areaId) {
        var areaRegistration = areaRepository.findById(areaId)
                .orElseThrow(() -> new ResourceNotFoundException("Area Registration", "id", areaId));
        areaRepository.delete(areaRegistration);

        return new ApiResponse<>("Area unregistered successfully", true, null);
    }
}
