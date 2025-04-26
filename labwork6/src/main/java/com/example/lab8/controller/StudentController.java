package com.example.lab8.controller;

import com.example.lab8.entity.Student;
import com.example.lab8.service.StudentService;
import com.example.lab8.spec.StudentSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Sort;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;


@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {
    @Autowired
    private StudentService service;

    @GetMapping
    public Page<Student> getAll(@RequestParam Map<String, String> params, Pageable pageable,
                                @RequestParam(value = "sortBy", required = false) String sortBy,
                                @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir) {

        String sortField = sortBy != null ? sortBy : "id";
        Sort.Direction direction = Sort.Direction.fromString(sortDir.toUpperCase());
        Sort sort = Sort.by(Sort.Order.by(sortField).with(direction));
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        return service.getAll(StudentSpecification.build(params), pageable);
    }

    @PostMapping
    public Student create(@RequestBody Student s) {
        return service.save(s);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getById(@PathVariable Long id) {
        return service.getById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}")
    public ResponseEntity<Student> update(@PathVariable Long id, @RequestBody Student s) {
        if (!service.getById(id).isPresent()) return ResponseEntity.notFound().build();
        s.setId(id);
        return ResponseEntity.ok(service.save(s));
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}