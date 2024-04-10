package com.api.courseManagement.repositories;

import com.api.courseManagement.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
