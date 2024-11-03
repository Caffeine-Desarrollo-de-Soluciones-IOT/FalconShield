package com.verysafe.falconshield.properties.domain.model.entities;

import com.verysafe.falconshield.areas.domain.model.entities.Area;
import com.verysafe.falconshield.events.domain.model.entities.Event;
import com.verysafe.falconshield.user.profile.domain.model.entities.UserProfile;
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

    @CreationTimestamp
    private LocalDateTime registeredAt;

    @ManyToOne
    @JoinColumn(name = "user_profile_id")
    private UserProfile userProfile;

    @OneToMany(mappedBy = "property")
    private Set<Area> areas = new HashSet<>();

    @OneToMany(mappedBy = "property")
    private Set<Event> events = new HashSet<>();
}
