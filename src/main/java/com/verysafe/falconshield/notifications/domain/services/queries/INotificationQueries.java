package com.verysafe.falconshield.notifications.domain.services.queries;

import java.util.List;

import com.verysafe.falconshield.notifications.application.dto.response.NotificationResponseDto;
import com.verysafe.falconshield.notifications.application.dto.response.NotificationTypeResponseDto;
import com.verysafe.falconshield.notifications.application.dto.response.RegisteredNotificationResponseDto;
import com.verysafe.falconshield.shared.model.dto.response.ApiResponse;

public interface INotificationQueries {
    ApiResponse<List<NotificationTypeResponseDto>> getNotificationType();
    ApiResponse<NotificationResponseDto> getNotificationById(Long id);
    ApiResponse<List<RegisteredNotificationResponseDto>> getRegisteredNotification(String accountId);
}
