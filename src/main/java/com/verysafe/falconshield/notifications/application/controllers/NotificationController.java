package com.verysafe.falconshield.notifications.application.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.verysafe.falconshield.notifications.application.dto.request.RegisterNotificationRequestDto;
import com.verysafe.falconshield.notifications.application.dto.response.NotificationResponseDto;
import com.verysafe.falconshield.notifications.application.dto.response.NotificationTypeResponseDto;
import com.verysafe.falconshield.notifications.application.dto.response.RegisteredNotificationResponseDto;
import com.verysafe.falconshield.notifications.domain.services.commands.INotificationCommands;
import com.verysafe.falconshield.notifications.domain.services.queries.INotificationQueries;
import com.verysafe.falconshield.shared.model.dto.response.ApiResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Tag(name = "Notification")
@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    private final INotificationQueries notificationQueries;
    private final INotificationCommands notificationCommands;

    public NotificationController(INotificationQueries notificationQueries, INotificationCommands notificationCommands){
        this.notificationCommands = notificationCommands;
        this.notificationQueries = notificationQueries;
    }

    @GetMapping("/type")
    public ResponseEntity<ApiResponse<List<NotificationTypeResponseDto>>> getTypeNotification() {
        var res = notificationQueries.getNotificationType();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<NotificationResponseDto>> getDeviceById(@PathVariable Long id) {
        var res = notificationQueries.getNotificationById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/registered")
    public ResponseEntity<ApiResponse<List<RegisteredNotificationResponseDto>>> getRegisteredNotification(@AuthenticationPrincipal Jwt principal){
        var res = notificationQueries.getRegisteredNotification(principal.getSubject());
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Object>> registerNotification (
        @AuthenticationPrincipal Jwt principal,
        @RequestBody @Valid RegisterNotificationRequestDto request
    ) {
        var res = notificationCommands.registerNotification(principal.getSubject(), request);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    
    
}
