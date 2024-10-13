package com.verysafe.falconshield.properties.application.handlers.queries;

import com.verysafe.falconshield.properties.application.dto.response.PropertyResponseDto;
import com.verysafe.falconshield.properties.application.dto.response.RegisteredPropertyResponseDto;
import com.verysafe.falconshield.properties.domain.services.queries.IPropertyQueries;
import com.verysafe.falconshield.properties.infrastructure.repositories.IPropertyRegistrationRepository;
import com.verysafe.falconshield.properties.infrastructure.repositories.IPropertyRepository;
import com.verysafe.falconshield.shared.exception.ResourceNotFoundException;
import com.verysafe.falconshield.shared.model.dto.response.ApiResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyQueriesHandler implements IPropertyQueries {
    private final ModelMapper modelMapper;
    private final IPropertyRepository propertyRepository;
    private final IPropertyRegistrationRepository propertyRegistrationRepository;

    public PropertyQueriesHandler(ModelMapper modelMapper, IPropertyRepository propertyRepository, IPropertyRegistrationRepository propertyRegistrationRepository) {
        this.modelMapper = modelMapper;
        this.propertyRepository = propertyRepository;
        this.propertyRegistrationRepository = propertyRegistrationRepository;
    }

    @Override
    public ApiResponse<PropertyResponseDto> getPropertyById(Long id) {
        var workspace = propertyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Property", "id", id));
        var responseData = modelMapper.map(workspace, PropertyResponseDto.class);

        return new ApiResponse<>("Ok", true, responseData);
    }

    @Override
    public ApiResponse<List<RegisteredPropertyResponseDto>> getRegisteredProperties(String accountId) {
        var properties = propertyRegistrationRepository.findAllByUserProfileAccountId(accountId);
        var responseData = properties.stream()
                .map(item -> modelMapper.map(item, RegisteredPropertyResponseDto.class))
                .toList();

        return new ApiResponse<>("Ok", true, responseData);
    }
}