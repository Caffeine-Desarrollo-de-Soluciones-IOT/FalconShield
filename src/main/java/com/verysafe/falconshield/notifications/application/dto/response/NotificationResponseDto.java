package com.verysafe.falconshield.notifications.application.dto.response;

import com.verysafe.falconshield.notifications.domain.model.enums.ENotificationType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationResponseDto {
    private Long id;
    private String content;
    private ENotificationType type;

    public NotificationResponseDto() {}
}
