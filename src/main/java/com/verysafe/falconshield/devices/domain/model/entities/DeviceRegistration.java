package com.verysafe.falconshield.devices.domain.model.entities;

import com.verysafe.falconshield.user.profile.domain.model.entities.UserProfile;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "device_registrations")
public class DeviceRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;

    //TODO: Area id

    @ManyToOne
    @JoinColumn(name = "user_profile_id")
    private UserProfile userProfile;

    @Column(nullable = false, length = 10)
    private String registrationCode;

    @CreationTimestamp
    private LocalDateTime registeredAt;

    public DeviceRegistration() {}
}