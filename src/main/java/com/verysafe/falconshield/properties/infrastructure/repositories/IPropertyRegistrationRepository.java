package com.verysafe.falconshield.properties.infrastructure.repositories;

import com.verysafe.falconshield.properties.domain.model.entities.PropertyRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPropertyRegistrationRepository extends JpaRepository<PropertyRegistration, Long> {
    List<PropertyRegistration> findAllByUserProfileAccountId(String accountId);
}
