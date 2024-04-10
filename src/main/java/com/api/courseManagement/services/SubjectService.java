package com.api.courseManagement.services;

import com.api.courseManagement.controllers.dtos.DetailedSubjectDTO;
import com.api.courseManagement.controllers.dtos.UpdateSubjectDTO;
import com.api.courseManagement.models.Subject;
import com.api.courseManagement.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public void delete(Long id) {
        subjectRepository.deleteById(id);
    }

    public DetailedSubjectDTO update(Long id, UpdateSubjectDTO updateSubjectDTO) {
        Subject subject = subjectRepository.getReferenceById(id);

        subject.updateInformation(updateSubjectDTO);

        subjectRepository.save(subject);

        return new DetailedSubjectDTO(subject);
    }
}
