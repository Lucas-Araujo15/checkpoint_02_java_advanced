package com.api.courseManagement.controllers;

import com.api.courseManagement.controllers.dtos.RegisterCourseDTO;
import com.api.courseManagement.controllers.dtos.RegisterRegistrationDTO;
import com.api.courseManagement.exceptions.CourseFullException;
import com.api.courseManagement.models.Course;
import com.api.courseManagement.models.Registration;
import com.api.courseManagement.services.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {
    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        registrationService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid RegisterRegistrationDTO registerRegistrationDTO, UriComponentsBuilder uriBuilder) throws CourseFullException {
        Registration registration = registrationService.create(registerRegistrationDTO);

        URI uri = uriBuilder.path("/registration/{id}").buildAndExpand(registration.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
