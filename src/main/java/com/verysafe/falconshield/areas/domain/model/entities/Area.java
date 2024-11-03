package com.verysafe.falconshield.areas.domain.model.entities;
import com.verysafe.falconshield.devices.domain.model.entities.DeviceRegistration;
import com.verysafe.falconshield.properties.domain.model.entities.Property;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="areas")
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String icon;

    @Column(nullable = true)
    private String color;

    @CreationTimestamp
    private LocalDateTime registeredAt;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    @OneToMany(mappedBy = "area")
    private Set<DeviceRegistration> deviceRegistrations = new HashSet<>();
}