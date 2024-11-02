package com.verysafe.falconshield.properties.domain.model.entities;

import com.verysafe.falconshield.properties.domain.model.entities.PropertyRegistration;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "properties")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String imageUrl;

    @Column(nullable = false)
    private String address;

    @OneToMany(mappedBy = "property")
    private Set<PropertyRegistration> registrations = new HashSet<>();
}
