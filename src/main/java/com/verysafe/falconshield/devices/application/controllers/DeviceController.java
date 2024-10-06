package com.verysafe.falconshield.devices.application.controllers;

import com.verysafe.falconshield.devices.application.dto.response.DeviceCatalogResponseDto;
import com.verysafe.falconshield.devices.application.dto.response.DeviceResponseDto;
import com.verysafe.falconshield.devices.domain.services.queries.IDeviceQueries;
import com.verysafe.falconshield.shared.model.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {
    private final IDeviceQueries deviceQueries;

    public DeviceController(IDeviceQueries deviceQueries) {
        this.deviceQueries = deviceQueries;
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse<List<DeviceResponseDto>>> getDevices() {
        var res = deviceQueries.getDevices();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/catalog")
    public ResponseEntity<ApiResponse<List<DeviceCatalogResponseDto>>> getDeviceCatalog() {
        var res = deviceQueries.getDeviceCatalog();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DeviceResponseDto>> getProductById(@PathVariable String id) {
        var res = deviceQueries.getDeviceById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
