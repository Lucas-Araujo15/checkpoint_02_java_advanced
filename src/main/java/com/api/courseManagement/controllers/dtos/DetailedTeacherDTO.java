package com.api.courseManagement.controllers.dtos;

import com.api.courseManagement.models.Teacher;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

public record DetailedTeacherDTO(
        String name,

        String specialty,

        @JsonIgnoreProperties({"subjectList"})
        List<CourseDTO> courseList
) {
    public DetailedTeacherDTO(Teacher teacher) {
        this(teacher.getName(), teacher.getSpecialty(), teacher.getCourseList().stream().map(CourseDTO::new).toList());
    }
}
