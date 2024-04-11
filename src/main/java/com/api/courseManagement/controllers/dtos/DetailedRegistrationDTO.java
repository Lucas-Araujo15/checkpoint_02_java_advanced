package com.api.courseManagement.controllers.dtos;

import com.api.courseManagement.models.Registration;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

public record DetailedRegistrationDTO(
        String studentName,

        String courseName,

        LocalDateTime registrationDate
) {
    public DetailedRegistrationDTO(Registration registration) {
        this(registration.getStudent().getName(), registration.getCourse().getName(), registration.getRegistrationDate());
    }
}
