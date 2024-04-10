package com.api.courseManagement.controllers;

import com.api.courseManagement.controllers.dtos.*;
import com.api.courseManagement.models.Student;
import com.api.courseManagement.models.Teacher;
import com.api.courseManagement.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid RegisterStudentDTO registerStudentDTO, UriComponentsBuilder uriBuilder)  {
        Student student = studentService.create(registerStudentDTO);

        URI uri = uriBuilder.path("/student/{id}").buildAndExpand(student.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailedStudentDTO> get(@PathVariable("id") Long id) {
        DetailedStudentDTO detailedStudentDTO = studentService.get(id);
        return ResponseEntity.ok(detailedStudentDTO);
    }

    @GetMapping
    public ResponseEntity<Page<DetailedStudentDTO>> list(@PageableDefault(size = 10, page = 0) Pageable pagination) {
        Page<DetailedStudentDTO> page = studentService.list(pagination);
        return ResponseEntity.ok(page);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetailedStudentDTO> update(@PathVariable("id") Long id, @RequestBody UpdateStudentDTO updateStudentDTO) {
        DetailedStudentDTO detailedStudentDTO = studentService.update(id, updateStudentDTO);
        return ResponseEntity.ok(detailedStudentDTO);
    }
}
