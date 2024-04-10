package com.api.courseManagement.controllers.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterSubjectDTO(
        @NotBlank
        @Size(max = 100)
        String name,

        @Size(max = 500)
        String description
) {
}
