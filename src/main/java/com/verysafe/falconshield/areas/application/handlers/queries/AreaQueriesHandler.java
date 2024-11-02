package com.verysafe.falconshield.areas.application.handlers.queries;

import com.verysafe.falconshield.areas.application.dto.response.AreaResponseDto;
import com.verysafe.falconshield.areas.application.dto.response.RegisteredAreaResponseDto;
import com.verysafe.falconshield.areas.domain.services.queries.IAreaQueries;
import com.verysafe.falconshield.areas.infrastructure.repositories.IAreaRegistrationRepository;
import com.verysafe.falconshield.areas.infrastructure.repositories.IAreaRepository;
import com.verysafe.falconshield.shared.exception.ResourceNotFoundException;
import com.verysafe.falconshield.shared.model.dto.response.ApiResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service 
public class AreaQueriesHandler implements IAreaQueries{
    private final ModelMapper modelMapper;
    private final IAreaRepository areaRepository;
    private final IAreaRegistrationRepository areaRegistrationRepository;

    public AreaQueriesHandler(ModelMapper modelMapper, IAreaRepository areaRepository, IAreaRegistrationRepository areaRegistrationRepository){
        this.modelMapper = modelMapper;
        this.areaRepository = areaRepository;
        this.areaRegistrationRepository = areaRegistrationRepository;
    }

    @Override
    public ApiResponse<AreaResponseDto> getAreaById(Long id){
        var workspace = areaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Area","id", id));
        var responseData = modelMapper.map(workspace, AreaResponseDto.class);
        return new ApiResponse<>("Ok", true, responseData);
    }

    @Override
    public ApiResponse<List<RegisteredAreaResponseDto>> getRegisteredAreas(String propertyIdString) {
        Long propertyId = Long.parseLong(propertyIdString);
        var areas = areaRegistrationRepository.findAllByPropertyId(propertyId);
        var responseData = areas.stream()
            .map(item -> modelMapper.map(item, RegisteredAreaResponseDto.class))
            .toList();
        return new ApiResponse<>("Ok", true, responseData);
    }
    
}