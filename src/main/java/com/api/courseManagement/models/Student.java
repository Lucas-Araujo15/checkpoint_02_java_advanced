package com.api.courseManagement.models;

import com.api.courseManagement.controllers.dtos.RegisterStudentDTO;
import com.api.courseManagement.controllers.dtos.UpdateStudentDTO;
import com.api.courseManagement.enums.Country;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "student")
public class Student {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String email;

    private String cpf;

    private Country country;

    private Boolean bilingual;

    @Column(name = "start_date_in_technology")
    private LocalDate startDateInTechnology;

    @Column(name = "graduation_completion_date")
    private LocalDate graduationCompletionDate;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    private List<Registration> registrationList;

    public Student(RegisterStudentDTO registerStudentDTO) {
        this.name = registerStudentDTO.name();
        this.cpf = registerStudentDTO.cpf();
        this.email = registerStudentDTO.email();
        this.country = registerStudentDTO.country();
        this.bilingual = registerStudentDTO.bilingual();
        this.startDateInTechnology = registerStudentDTO.startDateInTechnology();
        this.graduationCompletionDate = registerStudentDTO.graduationCompletionDate();
        this.dateOfBirth = registerStudentDTO.dateOfBirth();
    }

    public void updateInformation(UpdateStudentDTO updateStudentDTO) {
        if (updateStudentDTO.name() != null) {
            this.name = updateStudentDTO.name();
        }

        if (updateStudentDTO.email() != null) {
            this.email = updateStudentDTO.email();
        }
    }
}
