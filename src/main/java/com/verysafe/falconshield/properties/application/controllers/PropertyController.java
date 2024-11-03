package com.verysafe.falconshield.properties.application.controllers;

import com.verysafe.falconshield.properties.application.dto.request.RegisterPropertyRequestDto;
import com.verysafe.falconshield.properties.application.dto.response.PropertyResponseDto;
import com.verysafe.falconshield.properties.domain.services.commands.IPropertyCommands;
import com.verysafe.falconshield.properties.domain.services.queries.IPropertyQueries;
import com.verysafe.falconshield.shared.model.dto.response.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Property")
@RestController
@RequestMapping("/api/properties")
public class PropertyController {
    private final IPropertyQueries propertyQueries;
    private final IPropertyCommands propertyCommands;

    public PropertyController(IPropertyQueries propertyQueries, IPropertyCommands propertyCommands) {
        this.propertyQueries = propertyQueries;
        this.propertyCommands = propertyCommands;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PropertyResponseDto>> getPropertyById(@PathVariable Long id) {
        var res = propertyQueries.getPropertyById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/registered")
    public ResponseEntity<ApiResponse<List<PropertyResponseDto>>> getProperties(@AuthenticationPrincipal Jwt principal) {
        var res = propertyQueries.getProperties(principal.getSubject());
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Object>> registerProperty(
            @AuthenticationPrincipal Jwt principal,
            @RequestBody @Valid RegisterPropertyRequestDto request
    ) {
        var res = propertyCommands.registerProperty(principal.getSubject(), request);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("/unregister/{id}")
    public ResponseEntity<ApiResponse<Object>> unregisterProperty(
            @AuthenticationPrincipal Jwt principal,
            @PathVariable Long id
    ) {
        var res = propertyCommands.unregisterProperty(principal.getSubject(), id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
