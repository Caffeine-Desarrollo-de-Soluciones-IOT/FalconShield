package com.verysafe.falconshield.notifications.domain.model.entities;
import java.util.HashSet;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notifications_type_id", nullable = false)
    private NotificationType type;

    @ManyToOne
    
    @OneToMany(mappedBy =  "notifications")
    private Set<NotificationRegistration> registrations = new HashSet<>();

    public Notification() {}
}
