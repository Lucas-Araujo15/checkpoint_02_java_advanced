package com.api.courseManagement.repositories;

import com.api.courseManagement.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
