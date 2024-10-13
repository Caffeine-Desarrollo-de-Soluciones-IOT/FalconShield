package com.verysafe.falconshield.properties.domain.model.entities;

import com.verysafe.falconshield.user.profile.domain.model.entities.UserProfile;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "property_registrations")
public class PropertyRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    //TODO: Area id

    @ManyToOne
    @JoinColumn(name = "user_profile_id")
    private UserProfile userProfile;

    @CreationTimestamp
    private LocalDateTime registeredAt;

    public PropertyRegistration() {}
}
