package com.verysafe.falconshield.devices.domain.model.entities;

import com.verysafe.falconshield.devices.domain.model.enums.EDeviceType;
import com.verysafe.falconshield.devices.domain.model.values.DeviceSpecs;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "devices")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

    @Enumerated(EnumType.STRING)
    private EDeviceType type;

    @Embedded
    private DeviceSpecs specs;

    //MUCHOS "devices" van a estar en UN "device_category"
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private DeviceCategory category;

    @OneToMany(mappedBy = "device")
    private Set<DeviceRegistration> registrations = new HashSet<>();

    public Device() {}
}
