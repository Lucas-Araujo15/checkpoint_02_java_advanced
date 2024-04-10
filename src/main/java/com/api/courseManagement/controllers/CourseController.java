package com.api.courseManagement.controllers;

import com.api.courseManagement.controllers.dtos.CourseDTO;
import com.api.courseManagement.controllers.dtos.DetailedCourseDTO;
import com.api.courseManagement.controllers.dtos.RegisterCourseDTO;
import com.api.courseManagement.controllers.dtos.UpdateCourseDTO;
import com.api.courseManagement.models.Course;
import com.api.courseManagement.services.CourseService;
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
@RequestMapping("/course")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid RegisterCourseDTO registerCourseDTO, UriComponentsBuilder uriBuilder)  {
        Course course = courseService.create(registerCourseDTO);

        URI uri = uriBuilder.path("/course/{id}").buildAndExpand(course.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<Page<CourseDTO>> list(@PageableDefault(size = 10, page = 0) Pageable pagination) {
        Page<CourseDTO> page = courseService.list(pagination);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailedCourseDTO> get(@PathVariable("id") Long id) {
        DetailedCourseDTO detailedCourseDTO = courseService.get(id);
        return ResponseEntity.ok(detailedCourseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        courseService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> update(@PathVariable("id") Long id, @RequestBody UpdateCourseDTO updateCourseDTO) {
        CourseDTO courseDTO = courseService.update(id, updateCourseDTO);
        return ResponseEntity.ok(courseDTO);
    }
}
