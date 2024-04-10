package com.api.courseManagement.controllers.dtos;

import com.api.courseManagement.models.Course;
import com.api.courseManagement.models.Subject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

public record CourseDTO(
        String name,
        String description,
        @JsonIgnoreProperties({"id", "course"})
        List<Subject> subjectList
) {
    public CourseDTO(Course course) {
        this(course.getName(), course.getDescription(), course.getSubjectList());
    }
}
