package com.verysafe.falconshield.devices.infrastructure.repositories;

import com.verysafe.falconshield.devices.domain.model.entities.DeviceRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDeviceRegistrationRepository extends JpaRepository<DeviceRegistration, Long> {
    List<DeviceRegistration> findAllByUserProfileAccountId(String accountId);
    List<DeviceRegistration> findAllByUserProfileAccountIdAndAreaId(String accountId, long areaId);
}
