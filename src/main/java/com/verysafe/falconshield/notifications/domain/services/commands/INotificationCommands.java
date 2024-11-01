package com.verysafe.falconshield.notifications.domain.services.commands;

import com.verysafe.falconshield.notifications.application.dto.request.RegisterNotificationRequestDto;
import com.verysafe.falconshield.shared.model.dto.response.ApiResponse;

public interface INotificationCommands {
    ApiResponse<Object> registerNotification(String accountId, RegisterNotificationRequestDto request);    
}
