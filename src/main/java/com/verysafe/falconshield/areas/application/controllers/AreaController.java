package com.verysafe.falconshield.areas.application.controllers;
import com.verysafe.falconshield.areas.application.dto.request.RegisterAreaRequestDto;
import com.verysafe.falconshield.areas.application.dto.response.AreaResponseDto;
import com.verysafe.falconshield.areas.domain.services.commands.IAreaCommands;
import com.verysafe.falconshield.areas.domain.services.queries.IAreaQueries;
import com.verysafe.falconshield.shared.model.dto.response.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Area")
@RestController
@RequestMapping("/api/areas")
public class AreaController {
    private final IAreaQueries areaQueries;
    private final IAreaCommands areaCommands;

    public AreaController(IAreaQueries areaQueries, IAreaCommands areaCommands) {
        this.areaQueries = areaQueries;
        this.areaCommands = areaCommands;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AreaResponseDto>> getAreaById(@PathVariable long id) {
        var res = areaQueries.getAreaById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<AreaResponseDto>>> getAllAreasByAccountId(@AuthenticationPrincipal Jwt jwt) {
        var res = areaQueries.getAllAreas(jwt.getSubject());
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/all/{propertyId}")
    public ResponseEntity<ApiResponse<List<AreaResponseDto>>> getRegisteredAreas(@PathVariable long propertyId) {
        var res = areaQueries.getAreasByPropertyId(propertyId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/register/{propertyId}")
    public ResponseEntity<ApiResponse<Object>> registerArea(
            @PathVariable long propertyId,
            @RequestBody @Valid RegisterAreaRequestDto request
    ) {
        var res = areaCommands.registerArea(propertyId, request);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/update/{areaId}")
    public ResponseEntity<ApiResponse<Object>> updateArea(
            @PathVariable long areaId,
            @RequestBody RegisterAreaRequestDto request
    ) {
        var res = areaCommands.updateArea(areaId, request);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("/unregister/{areaId}")
    public ResponseEntity<ApiResponse<Object>> unregisterArea(@PathVariable long areaId) {
        var res = areaCommands.unregisterArea(areaId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
