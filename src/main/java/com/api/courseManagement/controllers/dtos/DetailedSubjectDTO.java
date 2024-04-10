package com.api.courseManagement.controllers.dtos;

import com.api.courseManagement.models.Subject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public record DetailedSubjectDTO(
        String name,

        String description
) {
    public DetailedSubjectDTO(Subject subject) {
        this(subject.getName(), subject.getDescription());
    }
}
