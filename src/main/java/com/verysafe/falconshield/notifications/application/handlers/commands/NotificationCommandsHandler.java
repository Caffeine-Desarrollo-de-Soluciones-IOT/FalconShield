package com.verysafe.falconshield.notifications.application.handlers.commands;

import org.springframework.stereotype.Service;

import com.verysafe.falconshield.notifications.application.dto.request.RegisterNotificationRequestDto;
import com.verysafe.falconshield.notifications.domain.model.entities.Notification;
import com.verysafe.falconshield.notifications.domain.model.entities.NotificationRegistration;
import com.verysafe.falconshield.notifications.domain.services.commands.INotificationCommands;
import com.verysafe.falconshield.notifications.infrastructure.repositories.INotificationRegistrationRepository;
import com.verysafe.falconshield.notifications.infrastructure.repositories.INotificationRepository;
import com.verysafe.falconshield.shared.exception.ResourceNotFoundException;
import com.verysafe.falconshield.shared.model.dto.response.ApiResponse;
import com.verysafe.falconshield.user.profile.infrastructure.repositories.IUserProfileRepository;

@Service
public class NotificationCommandsHandler implements INotificationCommands{
    private final INotificationRegistrationRepository notificationRegistrationRepository;
    private final INotificationRepository notificationRepository;
    private final IUserProfileRepository userProfileRepository;

    public NotificationCommandsHandler(INotificationRegistrationRepository notificationRegistrationRepository, INotificationRepository notificationRepository, IUserProfileRepository userProfileRepository){
        this.notificationRegistrationRepository = notificationRegistrationRepository;
        this.notificationRepository = notificationRepository;
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public ApiResponse<Object> registerNotification(String accountId, RegisterNotificationRequestDto request){
        var userProfile = userProfileRepository.findByAccountId(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("User Profile", "accountId", accountId));

        var newNotification = new Notification();
        newNotification.setContent(request.content());
        var savedNotification = notificationRepository.save(newNotification);

        var notificationRegistration = new NotificationRegistration();
        notificationRegistration.setNotification(savedNotification);
        notificationRegistration.setUserProfile(userProfile);
        notificationRegistrationRepository.save(notificationRegistration);

        return new ApiResponse<>("Notification registered successfully", true, null);
    }
}
