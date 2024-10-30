package com.verysafe.falconshield.areas.domain.model.entities;
import com.verysafe.falconshield.properties.domain.model.entities.Property;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name="area_registrations")
public class AreaRegistration{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="area_id")
    private Area area;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    //@OneToMany
    //@JoinColumn(name = "users_devices_id")
    //private UserDevice users_devices_id;

    @CreationTimestamp
    private LocalDateTime registeredAt;

    public AreaRegistration(){}

}