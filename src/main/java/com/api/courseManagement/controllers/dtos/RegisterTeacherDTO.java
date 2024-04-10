package com.api.courseManagement.controllers.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record RegisterTeacherDTO(
        @NotBlank
        @Size(max = 100)
        String name,

        @NotBlank
        String specialty,

        @NotNull
        List<Long> coursesId
) {
}
