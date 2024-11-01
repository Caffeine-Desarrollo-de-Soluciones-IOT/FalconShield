package com.verysafe.falconshield.notifications.application.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RegisterNotificationRequestDto(    
    @NotNull(message = "Notification ID is required")
    @Min(value = 1, message = "Notification ID must be greater than 0")
    Long notificationId,

    @NotEmpty(message = "Content is required")
    String content
) {}
