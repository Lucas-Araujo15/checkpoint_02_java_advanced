package com.api.courseManagement.repositories;

import com.api.courseManagement.models.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
}
