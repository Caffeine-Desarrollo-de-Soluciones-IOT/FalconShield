package com.verysafe.falconshield.properties.infrastructure.repositories;

import com.verysafe.falconshield.properties.domain.model.entities.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface IPropertyRepository extends JpaRepository<Property, Long> {
}
