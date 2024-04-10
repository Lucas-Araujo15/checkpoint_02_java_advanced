package com.api.courseManagement.services;

import com.api.courseManagement.controllers.dtos.CourseDTO;
import com.api.courseManagement.controllers.dtos.DetailedCourseDTO;
import com.api.courseManagement.controllers.dtos.RegisterCourseDTO;
import com.api.courseManagement.controllers.dtos.UpdateCourseDTO;
import com.api.courseManagement.models.Course;
import com.api.courseManagement.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course create(RegisterCourseDTO courseDTO) {
        return courseRepository.save(new Course(courseDTO));
    }

    public DetailedCourseDTO get(Long id) {
        return new DetailedCourseDTO(courseRepository.getReferenceById(id));
    }

    public Optional<Course> find(Long id) {
        return courseRepository.findById(id);
    }

    public void delete(Long id) {
        courseRepository.deleteById(id);
    }

    public CourseDTO update(Long id, UpdateCourseDTO updateCourseDTO) {
        Course course = courseRepository.getReferenceById(id);

        course.updateInformation(updateCourseDTO);

        courseRepository.save(course);

        return new CourseDTO(course);
    }

    public Page<CourseDTO> list(Pageable pagination) {
        return courseRepository.findAll(pagination).map(CourseDTO::new);
    }
}
