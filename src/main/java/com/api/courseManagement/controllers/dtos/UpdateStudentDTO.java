package com.api.courseManagement.controllers.dtos;

public record UpdateStudentDTO(
        String name,

        String email,

        String cpf,

        String country,

        Boolean bilingual
) {

}
