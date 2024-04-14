package com.api.courseManagement.services;

import com.api.courseManagement.controllers.dtos.RegisterRegistrationDTO;
import com.api.courseManagement.exceptions.CourseFullException;
import com.api.courseManagement.models.Course;
import com.api.courseManagement.models.Registration;
import com.api.courseManagement.models.Student;
import com.api.courseManagement.repositories.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {
    private final RegistrationRepository registrationRepository;

    private final StudentService studentService;

    private final CourseService courseService;

    @Autowired
    public RegistrationService(RegistrationRepository registrationRepository, StudentService studentService, CourseService courseService) {
        this.registrationRepository = registrationRepository;
        this.courseService = courseService;
        this.studentService = studentService;
    }

    public void delete(Long id) {
        Registration registration = registrationRepository.getReferenceById(id);

        courseService.removeRegistration(registration.getCourse());

        registrationRepository.delete(registration);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Registration create(RegisterRegistrationDTO registerRegistrationDTO) throws CourseFullException {
        Registration registration = new Registration();

        Course course = courseService.find(registerRegistrationDTO.courseId()).orElseThrow();

        Student student = studentService.find(registerRegistrationDTO.studentId()).orElseThrow();

        registration.setCourse(course);
        registration.setStudent(student);
        registration.setRegistrationDate(registerRegistrationDTO.registrationDate());

        courseService.addRegistration(course);

        registrationRepository.save(registration);

        return registration;
    }
}
