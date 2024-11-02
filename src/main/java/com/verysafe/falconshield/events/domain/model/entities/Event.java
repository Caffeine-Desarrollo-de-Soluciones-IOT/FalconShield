package com.verysafe.falconshield.events.domain.model.entities;

import com.verysafe.falconshield.events.domain.model.enums.EEventType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    private EEventType type;

    @Column(nullable = false)
    @CreationTimestamp
    private Instant timestamp;

    //TODO: add propertyId

    public Event() {}
}
