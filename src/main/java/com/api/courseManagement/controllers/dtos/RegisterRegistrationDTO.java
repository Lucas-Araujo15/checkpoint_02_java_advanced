package com.api.courseManagement.controllers.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDateTime;

public record RegisterRegistrationDTO(
        @PastOrPresent
        LocalDateTime registrationDate,

        @NotNull
        Long courseId,

        @NotNull
        Long studentId
) {
}
