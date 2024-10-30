package com.verysafe.falconshield.areas.application.controllers;
import com.verysafe.falconshield.areas.application.dto.request.RegisterAreaRequestDto;
import com.verysafe.falconshield.areas.application.dto.response.AreaResponseDto;
import com.verysafe.falconshield.areas.application.dto.response.RegisteredAreaResponseDto;
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
    public ResponseEntity<ApiResponse<AreaResponseDto>> getAreaById(@PathVariable Long id) {
        var res = areaQueries.getAreaById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/registered")
    public ResponseEntity<ApiResponse<List<RegisteredAreaResponseDto>>> getRegisteredProperties(@AuthenticationPrincipal Jwt principal) {
        var res = areaQueries.getRegisteredAreas(principal.getSubject());
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Object>> registerArea(
            @AuthenticationPrincipal Jwt principal,
            @RequestBody @Valid RegisterAreaRequestDto request
    ) {
        var res = areaCommands.registerArea(principal.getSubject(), request);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("/unregister/{id}")
    public ResponseEntity<ApiResponse<Object>> unregisterArea(
            @AuthenticationPrincipal Jwt principal,
            @PathVariable Long id
    ) {
        var res = areaCommands.unregisterArea(principal.getSubject(), id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
