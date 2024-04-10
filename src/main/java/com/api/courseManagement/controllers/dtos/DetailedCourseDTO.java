package com.api.courseManagement.controllers.dtos;

import com.api.courseManagement.models.Course;
import com.api.courseManagement.models.Registration;
import com.api.courseManagement.models.Teacher;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;
import java.util.List;

public record DetailedCourseDTO(
        String name,
        String description,
        LocalDateTime startDate,
        LocalDateTime endDate,
        List<Teacher> teacherList,

        @JsonIgnoreProperties({"id", "course"})
        List<Registration> registrationList,
        List<DetailedSubjectDTO> subjectList

) {
    public DetailedCourseDTO(Course course) {
        this(course.getName(), course.getDescription(), course.getStartDate(), course.getEndDate(), course.getTeacherList(), course.getRegistrationList(), course.getSubjectList().stream().map(DetailedSubjectDTO::new).toList());
    }
}
