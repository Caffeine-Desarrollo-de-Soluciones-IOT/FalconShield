package com.verysafe.falconshield.devices.infrastructure.repositories;

import com.verysafe.falconshield.devices.domain.model.entities.DeviceCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDeviceCategoryRepository extends JpaRepository<DeviceCategory, Long> {
}
