package com.korea.JpaMission1.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<SiteUser, Integer> {
    SiteUser findByEmail(String email);

    Optional<SiteUser> findByUsername(String username);
}
