package com.api.courseManagement.controllers.dtos;

import com.api.courseManagement.enums.Country;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

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

        @NotNull
        Country country,

        @NotNull
        Boolean bilingual,

        @PastOrPresent
        LocalDate startDateInTechnology,

        @PastOrPresent
        LocalDate graduationCompletionDate,

        @Past
        LocalDate dateOfBirth
) {
}
