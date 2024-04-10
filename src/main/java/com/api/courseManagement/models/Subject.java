package com.api.courseManagement.models;

import com.api.courseManagement.controllers.dtos.RegisterSubjectDTO;
import com.api.courseManagement.controllers.dtos.UpdateSubjectDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "subject")
public class Subject {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    public Subject(RegisterSubjectDTO registerSubjectDTO) {
        this.name = registerSubjectDTO.name();
        this.description = registerSubjectDTO.description();
    }

    public void updateInformation(UpdateSubjectDTO updateSubjectDTO) {
        if (updateSubjectDTO.name() != null) {
            this.name = updateSubjectDTO.name();
        }

        if (updateSubjectDTO.description() != null) {
            this.description = updateSubjectDTO.description();
        }
    }
}
