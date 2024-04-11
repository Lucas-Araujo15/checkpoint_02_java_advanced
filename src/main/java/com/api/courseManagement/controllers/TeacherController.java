package com.api.courseManagement.controllers;

import com.api.courseManagement.controllers.dtos.*;
import com.api.courseManagement.models.Course;
import com.api.courseManagement.models.Teacher;
import com.api.courseManagement.services.TeacherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {
    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid RegisterTeacherDTO registerTeacherDTO, UriComponentsBuilder uriBuilder)  {
        Teacher teacher = teacherService.create(registerTeacherDTO);

        URI uri = uriBuilder.path("/teacher/{id}").buildAndExpand(teacher.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<Page<DetailedTeacherDTO>> list(@PageableDefault(size = 10, page = 0) Pageable pagination) {
        Page<DetailedTeacherDTO> page = teacherService.list(pagination);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailedTeacherDTO> find(@PathVariable("id") Long id) {
        DetailedTeacherDTO detailedTeacherDTO = teacherService.get(id);
        return ResponseEntity.ok(detailedTeacherDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        teacherService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetailedTeacherDTO> update(@PathVariable("id") Long id, @RequestBody UpdateTeacherDTO updateTeacherDTO) {
        DetailedTeacherDTO detailedTeacherDTO = teacherService.update(id, updateTeacherDTO);
        return ResponseEntity.ok(detailedTeacherDTO);
    }

    @GetMapping("/course/{id}")
    public ResponseEntity<Page<DetailedTeacherDTO>> findByCourse(@PageableDefault(size = 10, page = 0) Pageable pagination) {
        Page<DetailedTeacherDTO> page = teacherService.list(pagination);
        return ResponseEntity.ok(page);
    }
}
