package com.api.courseManagement.controllers.dtos;

import com.api.courseManagement.enums.Country;
import com.api.courseManagement.models.Registration;
import com.api.courseManagement.models.Student;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record DetailedStudentDTO(
        String name,

        Country country,

        Boolean bilingual,

        LocalDate startDateInTechnology,

        LocalDate graduationCompletionDate,

        @JsonIgnoreProperties({"studentName"})
        List<DetailedRegistrationDTO> registrationList
) {
    public DetailedStudentDTO(Student student) {
        this(student.getName(), student.getCountry(), student.getBilingual(), student.getStartDateInTechnology(), student.getGraduationCompletionDate(), student.getRegistrationList().stream().map(DetailedRegistrationDTO::new).toList());
    }
}
