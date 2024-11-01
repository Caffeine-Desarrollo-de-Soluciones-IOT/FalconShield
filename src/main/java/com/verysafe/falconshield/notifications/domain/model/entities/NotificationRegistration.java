package com.verysafe.falconshield.notifications.domain.model.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.verysafe.falconshield.user.profile.domain.model.entities.UserProfile;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "notification_registrations")
public class NotificationRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "notification_id")
    private Notification notification;

    @ManyToOne
    @JoinColumn(name = "user_profile_id")
    private UserProfile userProfile;

    @CreationTimestamp
    private LocalDateTime registeredAt;
    
    public NotificationRegistration(){}
}
