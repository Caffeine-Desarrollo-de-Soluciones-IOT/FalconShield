package com.verysafe.falconshield.user.profile.infrastructure.repositories;

import com.verysafe.falconshield.user.profile.domain.model.entities.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserProfileRepository extends JpaRepository<UserProfile, Long> {
    Optional<UserProfile> findByAccountId(String accountId);
}
