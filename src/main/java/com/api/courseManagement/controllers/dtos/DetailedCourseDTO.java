package com.api.courseManagement.controllers.dtos;

import com.api.courseManagement.models.Course;
import com.api.courseManagement.models.Registration;
import com.api.courseManagement.models.Teacher;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record DetailedCourseDTO(
        String name,
        String description,
        LocalDate startDate,
        LocalDate endDate,

        @JsonIgnoreProperties({"courseList", "id"})
        List<Teacher> teacherList,

        @JsonIgnoreProperties({"courseName"})
        List<DetailedRegistrationDTO> registrationList,
        List<DetailedSubjectDTO> subjectList

) {
    public DetailedCourseDTO(Course course) {
        this(course.getName(), course.getDescription(), course.getStartDate(), course.getEndDate(), course.getTeacherList(), course.getRegistrationList().stream().map(DetailedRegistrationDTO::new).toList(), course.getSubjectList().stream().map(DetailedSubjectDTO::new).toList());
    }
}
