package com.verysafe.falconshield.notifications.application.dto.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationTypeResponseDto {
    private Long id;
    private String type;
    private List<NotificationResponseDto> notifications;

    public NotificationTypeResponseDto() {}
}
