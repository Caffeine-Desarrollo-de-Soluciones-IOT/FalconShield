package com.verysafe.falconshield.areas.infrastructure.repositories;
import com.verysafe.falconshield.areas.domain.model.entities.AreaRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IAreaRegistrationRepository extends JpaRepository<AreaRegistration, Long>{
    List<AreaRegistration> findAllByUserProfileAccountId(String accountId);
}