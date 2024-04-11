package com.api.courseManagement.controllers;

import com.api.courseManagement.controllers.dtos.DetailedSubjectDTO;
import com.api.courseManagement.controllers.dtos.UpdateSubjectDTO;
import com.api.courseManagement.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {
    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        subjectService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetailedSubjectDTO> update(@PathVariable("id") Long id, @RequestBody UpdateSubjectDTO updateSubjectDTO) {
        DetailedSubjectDTO subjectDTO = subjectService.update(id, updateSubjectDTO);
        return ResponseEntity.ok(subjectDTO);
    }
}
