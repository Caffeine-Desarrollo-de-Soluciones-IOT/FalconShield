package com.verysafe.falconshield.notifications.application.handlers.queries;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.verysafe.falconshield.notifications.application.dto.response.NotificationResponseDto;
import com.verysafe.falconshield.notifications.application.dto.response.NotificationTypeResponseDto;
import com.verysafe.falconshield.notifications.application.dto.response.RegisteredNotificationResponseDto;
import com.verysafe.falconshield.notifications.domain.services.queries.INotificationQueries;
import com.verysafe.falconshield.notifications.infrastructure.repositories.INotificationRegistrationRepository;
import com.verysafe.falconshield.notifications.infrastructure.repositories.INotificationRepository;
import com.verysafe.falconshield.notifications.infrastructure.repositories.INotificationTypeRepository;
import com.verysafe.falconshield.shared.exception.ResourceNotFoundException;
import com.verysafe.falconshield.shared.model.dto.response.ApiResponse;

@Service
public class NotificationQueriesHandler implements INotificationQueries {
    private final ModelMapper modelMapper;
    private final INotificationRepository notificationRepository;
    private final INotificationTypeRepository notificationTypeRepository;
    private final INotificationRegistrationRepository notificationRegistrationRepository;

    public NotificationQueriesHandler(ModelMapper modelMapper, INotificationRepository notificationRepository, INotificationTypeRepository notificationTypeRepository, INotificationRegistrationRepository notificationRegistrationRepository){
        this.modelMapper = modelMapper;
        this.notificationRepository = notificationRepository;
        this.notificationTypeRepository = notificationTypeRepository;
        this.notificationRegistrationRepository = notificationRegistrationRepository;
    }

    @Override
    public ApiResponse<List<NotificationTypeResponseDto>> getNotificationType(){
        var type = notificationTypeRepository.findAll();
        var responseData = type.stream().map(item -> modelMapper.map(item, NotificationTypeResponseDto.class)).toList();
        return new ApiResponse<>("Ok", true, responseData);
    }

    @Override
    public ApiResponse<NotificationResponseDto> getNotificationById(Long id){
        var workspace = notificationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Notification", "id", id));
        var responseData = modelMapper.map(workspace, NotificationResponseDto.class);

        return new ApiResponse<>("Ok", true, responseData);
    }

    @Override
    public ApiResponse<List<RegisteredNotificationResponseDto>> getRegisteredNotification(String accountId){
        var notification = notificationRegistrationRepository.findAllByUserProfileAccountId(accountId);
        var responseData = notification.stream().map(item -> modelMapper.map(item, RegisteredNotificationResponseDto.class)).toList();
        return new ApiResponse<>("Ok", true, responseData);
    }

}
