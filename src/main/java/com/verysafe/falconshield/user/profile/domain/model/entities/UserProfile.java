package com.verysafe.falconshield.user.profile.domain.model.entities;

import com.verysafe.falconshield.devices.domain.model.entities.DeviceRegistration;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "user_profiles")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Hace referencia al ID de la cuenta de usuario en Keycloak
    @Column(name = "account_id", nullable = false, unique = true)
    private String accountId;

    @Column()
    private String occupation;

    @Column(nullable = false, length = 8)
    private String dni;

    @OneToMany(mappedBy = "userProfile")
    private Set<DeviceRegistration> deviceRegistrations = new HashSet<>();

    public UserProfile() {}
}

