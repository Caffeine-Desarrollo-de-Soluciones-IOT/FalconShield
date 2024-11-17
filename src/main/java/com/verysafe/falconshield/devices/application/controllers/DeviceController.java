package com.verysafe.falconshield.devices.application.controllers;

import com.verysafe.falconshield.devices.application.dto.request.RegisterDeviceRequestDto;
import com.verysafe.falconshield.devices.application.dto.response.DeviceCatalogResponseDto;
import com.verysafe.falconshield.devices.application.dto.response.DeviceResponseDto;
import com.verysafe.falconshield.devices.application.dto.response.RegisteredDeviceResponseDto;
import com.verysafe.falconshield.devices.domain.services.commands.IDeviceCommands;
import com.verysafe.falconshield.devices.domain.services.queries.IDeviceQueries;
import com.verysafe.falconshield.shared.model.dto.response.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Device")
@RestController
@RequestMapping("/api/devices")
public class DeviceController {
    private final IDeviceQueries deviceQueries;
    private final IDeviceCommands deviceCommands;

    public DeviceController(IDeviceQueries deviceQueries, IDeviceCommands deviceCommands) {
        this.deviceQueries = deviceQueries;
        this.deviceCommands = deviceCommands;
    }

    @GetMapping("/catalog")
    public ResponseEntity<ApiResponse<List<DeviceCatalogResponseDto>>> getDeviceCatalog() {
        var res = deviceQueries.getDeviceCatalog();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DeviceResponseDto>> getDeviceById(@PathVariable Long id) {
        var res = deviceQueries.getDeviceById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/registered")
    public ResponseEntity<ApiResponse<List<RegisteredDeviceResponseDto>>> getRegisteredDevices(@AuthenticationPrincipal Jwt principal) {
        var res = deviceQueries.getRegisteredDevices(principal.getSubject());
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/registered/{areaId}")
    public ResponseEntity<ApiResponse<List<RegisteredDeviceResponseDto>>> getRegisteredDevicesForArea(
            @AuthenticationPrincipal Jwt principal,
            @PathVariable long areaId
    ) {
        var res = deviceQueries.getRegisteredDevicesByAreaId(principal.getSubject(), areaId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Object>> registerDevice(
            @AuthenticationPrincipal Jwt principal,
            @RequestBody @Valid RegisterDeviceRequestDto request
    ) {
        var res = deviceCommands.registerDevice(principal.getSubject(), request);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("/unregister/{id}")
    public ResponseEntity<ApiResponse<Object>> unregisterDevice(
            @AuthenticationPrincipal Jwt principal,
            @PathVariable Long id
    ) {
        var res = deviceCommands.unregisterDevice(principal.getSubject(), id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
