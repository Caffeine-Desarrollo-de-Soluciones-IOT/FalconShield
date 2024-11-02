package com.verysafe.falconshield.events.infrastructure.repositories;

import com.verysafe.falconshield.events.domain.model.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEventRepository extends JpaRepository<Event, Long> {
}
