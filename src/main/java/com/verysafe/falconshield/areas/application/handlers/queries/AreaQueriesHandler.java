package com.verysafe.falconshield.areas.application.handlers.queries;

import com.verysafe.falconshield.areas.application.dto.response.AreaResponseDto;
import com.verysafe.falconshield.areas.domain.services.queries.IAreaQueries;
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

    public AreaQueriesHandler(ModelMapper modelMapper, IAreaRepository areaRepository){
        this.modelMapper = modelMapper;
        this.areaRepository = areaRepository;
    }

    @Override
    public ApiResponse<AreaResponseDto> getAreaById(long id){
        var area = areaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Area","id", id));
        var responseData = modelMapper.map(area, AreaResponseDto.class);
        return new ApiResponse<>("Ok", true, responseData);
    }

    @Override
    public ApiResponse<List<AreaResponseDto>> getAreasByPropertyId(long propertyId) {
        var areas = areaRepository.findAllByPropertyId(propertyId);
        var responseData = areas.stream()
            .map(item -> modelMapper.map(item, AreaResponseDto.class))
            .toList();
        return new ApiResponse<>("Ok", true, responseData);
    }
}