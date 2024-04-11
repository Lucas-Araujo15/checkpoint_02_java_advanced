package com.api.courseManagement.services;

import com.api.courseManagement.controllers.dtos.*;
import com.api.courseManagement.enums.Country;
import com.api.courseManagement.exceptions.*;
import com.api.courseManagement.models.Registration;
import com.api.courseManagement.models.Student;
import com.api.courseManagement.repositories.StudentRepository;
import com.api.courseManagement.utils.CPFUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    private final CourseService courseService;

    @Autowired
    public StudentService(StudentRepository studentRepository, CourseService courseService) {
        this.studentRepository = studentRepository;
        this.courseService = courseService;
    }

    public Student create(RegisterStudentDTO registerStudentDTO) throws UnderAgeException, InvalidCpfException, FiveYearsGraduationException, InsufficientExperienceException, NotBilingualException, InvalidLocationException {
        Period diffAge = Period.between(registerStudentDTO.dateOfBirth(), LocalDate.now());
        Period diffGraduation = Period.between(registerStudentDTO.graduationCompletionDate(), LocalDate.now());
        Period diffTechExp = Period.between(registerStudentDTO.startDateInTechnology(), LocalDate.now());

        if (diffAge.getYears() < 18) {
           throw new UnderAgeException("É necessário ter 18 anos ou mais.");
        }

        if (diffGraduation.getYears() < 5) {
            throw new FiveYearsGraduationException("É necessário ter concluído a graduação há pelo menos 5 anos.");
        }

        if (!CPFUtil.validateCPF(registerStudentDTO.cpf())) {
            throw new InvalidCpfException("CPF Inválido");
        }

        if (diffTechExp.getYears() < 10) {
            throw new InsufficientExperienceException("É necessário ter pelo menos 10 anos de experiência na área de tecnologia");
        }

        if (!registerStudentDTO.bilingual()) {
            throw new NotBilingualException("É necessário ter fluência nos idiomas Inglês e Português");
        }

        if (registerStudentDTO.country() != Country.BRAZIL) {
            throw new InvalidLocationException("É necessário estar no Brasil");
        }

        return studentRepository.save(new Student(registerStudentDTO));
    }

    public DetailedStudentDTO get(Long id) {
        return new DetailedStudentDTO(studentRepository.getReferenceById(id));
    }

    public void delete (Long id) {
        Student student = studentRepository.getReferenceById(id);

        List<Registration> studentRegistrations =  student.getRegistrationList();

        for (Registration registration : studentRegistrations) {
            courseService.removeRegistration(registration.getCourse());
        }

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

    public Optional<Student> find(Long id) {
        return studentRepository.findById(id);
    }
}
