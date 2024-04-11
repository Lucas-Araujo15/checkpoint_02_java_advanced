package com.api.courseManagement.controllers.dtos;

import com.api.courseManagement.enums.Country;

public record UpdateStudentDTO(
        String name,

        String email
) {

}
