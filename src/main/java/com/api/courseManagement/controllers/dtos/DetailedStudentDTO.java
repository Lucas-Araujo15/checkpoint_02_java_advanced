package com.api.courseManagement.controllers.dtos;

import com.api.courseManagement.models.Registration;
import com.api.courseManagement.models.Student;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;
import java.util.List;

public record DetailedStudentDTO(
        String name,

        String country,

        Boolean bilingual,

        LocalDateTime startDateInTechnology,

        LocalDateTime graduationCompletionDate,

        @JsonIgnoreProperties({"id", "student"})
        List<Registration> registrationList
) {
    public DetailedStudentDTO(Student student) {
        this(student.getName(), student.getCountry(), student.getBilingual(), student.getStartDateInTechnology(), student.getGraduationCompletionDate(), student.getRegistrationList());
    }
}
