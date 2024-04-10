package com.api.courseManagement.controllers.dtos;

import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;

public record RegisterCourseDTO(
        @NotBlank
        @Size(max = 150)
        String name,

        @NotBlank
        @Size(max = 1000)
        String description,

        @FutureOrPresent
        LocalDateTime startDate,

        @FutureOrPresent
        LocalDateTime endDate,

        @NotNull
        @Valid
        List<RegisterSubjectDTO> subjectList
) {
}
