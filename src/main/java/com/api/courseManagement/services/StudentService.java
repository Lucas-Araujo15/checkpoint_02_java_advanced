package com.api.courseManagement.services;

import com.api.courseManagement.controllers.dtos.*;
import com.api.courseManagement.models.Student;
import com.api.courseManagement.models.Subject;
import com.api.courseManagement.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student create(RegisterStudentDTO registerStudentDTO) {
        return studentRepository.save(new Student(registerStudentDTO));
    }

    public DetailedStudentDTO get(Long id) {
        return new DetailedStudentDTO(studentRepository.getReferenceById(id));
    }

    public void delete (Long id) {
        studentRepository.deleteById(id);
    }

    public DetailedStudentDTO update(Long id, UpdateStudentDTO updateStudentDTO) {
        Student student = studentRepository.getReferenceById(id);

        student.updateInformation(updateStudentDTO);

        studentRepository.save(student);

        return new DetailedStudentDTO(student);
    }

    public Page<DetailedStudentDTO> list(Pageable pagination) {
        return studentRepository.findAll(pagination).map(DetailedStudentDTO::new);
    }
}
