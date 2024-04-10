package com.api.courseManagement.repositories;

import com.api.courseManagement.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
