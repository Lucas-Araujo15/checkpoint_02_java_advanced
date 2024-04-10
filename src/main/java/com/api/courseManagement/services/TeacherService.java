package com.api.courseManagement.services;

import com.api.courseManagement.controllers.dtos.*;
import com.api.courseManagement.models.Course;
import com.api.courseManagement.models.Teacher;
import com.api.courseManagement.repositories.TeacherRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;

    private final CourseService courseService;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository, CourseService courseService) {
        this.teacherRepository = teacherRepository;
        this.courseService = courseService;
    }

    public Teacher create(RegisterTeacherDTO registerTeacherDTO) {
        Teacher teacher = new Teacher(registerTeacherDTO);

        List<Course> courses = new ArrayList<>();

        for (Long courseId : registerTeacherDTO.coursesId()) {
            Course course = courseService.find(courseId).orElseThrow();

            courses.add(course);
        }

        teacher.setCourseList(courses);

        return teacherRepository.save(teacher);
    }

    public DetailedTeacherDTO get(Long id) {
        return new DetailedTeacherDTO(teacherRepository.getReferenceById(id));
    }

    public void delete(Long id) {
        teacherRepository.deleteById(id);
    }

    public DetailedTeacherDTO update(Long id, UpdateTeacherDTO updateTeacherDTO) {
        Teacher teacher = teacherRepository.getReferenceById(id);

        teacher.updateInformation(updateTeacherDTO);

        teacherRepository.save(teacher);

        return new DetailedTeacherDTO(teacher);
    }

    public Page<DetailedTeacherDTO> list(Pageable pagination) {
        return teacherRepository.findAll(pagination).map(DetailedTeacherDTO::new);
    }
}
