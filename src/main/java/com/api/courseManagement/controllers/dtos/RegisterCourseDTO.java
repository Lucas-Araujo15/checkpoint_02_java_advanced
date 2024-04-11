package com.api.courseManagement.controllers.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

public record RegisterCourseDTO(
        @NotBlank
        @Size(max = 150)
        String name,

        @NotBlank
        @Size(max = 1000)
        String description,

        @FutureOrPresent
        LocalDate startDate,

        @FutureOrPresent
        LocalDate endDate,

        @NotNull
        @Valid
        List<RegisterSubjectDTO> subjectList,

        @NotNull
        int registrationLimit
) {
}
