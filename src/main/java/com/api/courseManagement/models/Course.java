package com.api.courseManagement.models;

import com.api.courseManagement.controllers.dtos.RegisterCourseDTO;
import com.api.courseManagement.controllers.dtos.UpdateCourseDTO;
import com.api.courseManagement.exceptions.CourseFullException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "course")
public class Course {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "registration_limit")
    private int registrationLimit;

    @Column(name = "number_of_registrations")
    private int numberOfRegistrations;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "course_teacher",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id"))
    private List<Teacher> teacherList;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id")
    private List<Registration> registrationList;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private List<Subject> subjectList;

    public Course(RegisterCourseDTO courseDTO) {
        this.name = courseDTO.name();
        this.description = courseDTO.description();
        this.startDate = courseDTO.startDate();
        this.endDate = courseDTO.endDate();
        this.subjectList = courseDTO.subjectList().stream().map(Subject::new).toList();
        this.registrationLimit = courseDTO.registrationLimit();
    }

    public void updateInformation(UpdateCourseDTO updateCourseDTO) {
        if (updateCourseDTO.name() != null) {
            this.name = updateCourseDTO.name();
        }

        if (updateCourseDTO.description() != null) {
            this.description = updateCourseDTO.description();
        }
    }

    public void addRegistration() throws CourseFullException {
        if (this.registrationLimit == this.numberOfRegistrations) {
            throw new CourseFullException("O limite de inscrições já foi atingido!");
        }

        this.numberOfRegistrations += 1;
    }

    public void removeRegistration() {
        this.numberOfRegistrations -= 1;
    }
}
