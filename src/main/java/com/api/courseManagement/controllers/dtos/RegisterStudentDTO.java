package com.api.courseManagement.controllers.dtos;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDateTime;

public record RegisterStudentDTO(
        @NotBlank
        @Size(max = 100)
        String name,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @CPF
        String cpf,

        @NotBlank
        String country,

        @NotNull
        Boolean bilingual,

        @PastOrPresent
        LocalDateTime startDateInTechnology,

        @PastOrPresent
        LocalDateTime graduationCompletionDate,

        @Past
        LocalDateTime dateOfBirth
) {
}
