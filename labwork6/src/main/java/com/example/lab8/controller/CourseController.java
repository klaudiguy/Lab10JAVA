package com.example.lab8.controller;

import com.example.lab8.entity.Course;
import com.example.lab8.service.CourseService;
import com.example.lab8.spec.CourseSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    @Autowired
    private final CourseService courseService;  // Using constructor injection

    @GetMapping
    public Page<Course> getAll(@RequestParam Map<String, String> params, Pageable pageable,
                               @RequestParam(value = "sortBy", required = false) String sortBy,
                               @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir) {
        String sortField = sortBy != null ? sortBy : "id";
        Sort.Direction direction = Sort.Direction.fromString(sortDir.toUpperCase());
        Sort sort = Sort.by(Sort.Order.by(sortField).with(direction));
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        return courseService.getAll(CourseSpecification.build(params), pageable);
    }

    @PostMapping
    public Course create(@RequestBody Course course) {
        return courseService.save(course);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getById(@PathVariable Long id) {
        return courseService.getById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course course) {
        Course updatedCourse = courseService.updateCourse(id, course);
        return ResponseEntity.ok(updatedCourse);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        courseService.delete(id);
    }
}



