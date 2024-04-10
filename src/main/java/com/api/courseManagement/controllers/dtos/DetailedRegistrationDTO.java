package com.api.courseManagement.controllers.dtos;

import com.api.courseManagement.models.Registration;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

public record DetailedRegistrationDTO(
        @JsonIgnoreProperties({"inscriptionList"})
        DetailedStudentDTO student,

        LocalDateTime registrationDate
) {
    public DetailedRegistrationDTO(Registration registration) {
        this(new DetailedStudentDTO(registration.getStudent()), registration.getRegistrationDate());
    }
}
