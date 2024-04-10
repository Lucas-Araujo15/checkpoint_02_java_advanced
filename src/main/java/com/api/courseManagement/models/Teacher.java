package com.api.courseManagement.models;

import com.api.courseManagement.controllers.dtos.RegisterTeacherDTO;
import com.api.courseManagement.controllers.dtos.UpdateTeacherDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "teacher")
public class Teacher {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String specialty;

    @ManyToMany
    @JoinTable(
            name = "course_teacher",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> courseList;

    public Teacher(RegisterTeacherDTO registerTeacherDTO) {
        this.name = registerTeacherDTO.name();
        this.specialty = registerTeacherDTO.specialty();
    }

    public void updateInformation(UpdateTeacherDTO updateTeacherDTO) {
        if (updateTeacherDTO.name() != null) {
            this.name = updateTeacherDTO.name();
        }

        if (updateTeacherDTO.specialty() != null) {
            this.specialty = updateTeacherDTO.specialty();
        }
    }
}
