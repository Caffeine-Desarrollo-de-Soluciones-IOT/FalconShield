package com.verysafe.falconshield.events.infrastructure.repositories;

import com.verysafe.falconshield.events.domain.model.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllByPropertyId(long propertyId);
    List<Event> findAllByPropertyUserProfileAccountId(String accountId);
}
