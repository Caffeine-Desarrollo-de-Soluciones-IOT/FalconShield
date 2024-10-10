package com.verysafe.falconshield.devices.infrastructure.repositories;

import com.verysafe.falconshield.devices.domain.model.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDeviceRepository extends JpaRepository<Device, Long> {
}
