package com.korea.JpaMission1.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <SiteUser, Integer> {

    public SiteUser findByEmail(String email);
}
