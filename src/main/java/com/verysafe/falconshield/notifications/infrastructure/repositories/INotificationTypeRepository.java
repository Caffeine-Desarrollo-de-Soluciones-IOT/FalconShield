package com.verysafe.falconshield.notifications.infrastructure.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.verysafe.falconshield.notifications.domain.model.entities.NotificationType;

@Repository
public interface INotificationTypeRepository extends JpaRepository<NotificationType, Long>{

}