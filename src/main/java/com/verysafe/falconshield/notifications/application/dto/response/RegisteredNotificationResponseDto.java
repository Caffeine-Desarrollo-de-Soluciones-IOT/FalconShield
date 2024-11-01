package com.verysafe.falconshield.notifications.application.dto.response;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisteredNotificationResponseDto {
    private Long id;
    private LocalDateTime registeredAt;
    private NotificationResponseDto notification;

    public RegisteredNotificationResponseDto() {}
}
