package com.verysafe.falconshield.areas.infrastructure.repositories;
import com.verysafe.falconshield.areas.domain.model.entities.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAreaRepository extends JpaRepository<Area, Long>{
    List<Area> findAllByPropertyId(long propertyId);
}
