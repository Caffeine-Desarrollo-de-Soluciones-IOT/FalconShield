package com.verysafe.falconshield.areas.domain.model.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="areas")
public class Area{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String icon;

    @Column(nullable = true)
    private String color;

    @OneToMany
    private Set<AreaRegistration> registrations = new HashSet<>();
}