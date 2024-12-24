package com.restapi.repository;

import com.restapi.entities.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration,Long> {
}
