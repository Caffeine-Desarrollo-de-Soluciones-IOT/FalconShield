package com.verysafe.falconshield.notifications.infrastructure.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.verysafe.falconshield.notifications.domain.model.entities.NotificationRegistration;
import java.util.List;

@Repository
public interface INotificationRegistrationRepository extends JpaRepository<NotificationRegistration, Long>{
    List<NotificationRegistration> findAllByUserProfileAccountId(String accountId);
}