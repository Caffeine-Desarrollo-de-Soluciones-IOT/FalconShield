package com.verysafe.falconshield.areas.infrastructure.repositories;
import com.verysafe.falconshield.areas.domain.model.entities.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAreaRepository extends JpaRepository<Area, Long>{
}
