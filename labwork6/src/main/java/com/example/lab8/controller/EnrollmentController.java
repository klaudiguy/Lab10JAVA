package com.example.lab8.controller;

import com.example.lab8.entity.Enrollment;
import com.example.lab8.service.EnrollmentService;
import com.example.lab8.spec.EnrollmentSpecification;
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
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {
    @Autowired
    private EnrollmentService service;

    @GetMapping
    public Page<Enrollment> getAll(@RequestParam Map<String, String> params, Pageable pageable,
                                @RequestParam(value = "sortBy", required = false) String sortBy,
                                @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir) {

        String sortField = sortBy != null ? sortBy : "id";
        Sort.Direction direction = Sort.Direction.fromString(sortDir.toUpperCase());
        Sort sort = Sort.by(Sort.Order.by(sortField).with(direction));
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        return service.getAll(EnrollmentSpecification.build(params), pageable);
    }

    @PostMapping
    public Enrollment create(@RequestBody Enrollment e) {
        return service.save(e);
    }
    @GetMapping("/{id}") public ResponseEntity<Enrollment> getById(@PathVariable Long id) {
        return service.getById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Enrollment> update(@PathVariable Long id, @RequestBody Enrollment e) {
        Optional<Enrollment> existingEnrollment = service.getById(id);
        if (existingEnrollment.isEmpty()) {
            return ResponseEntity.notFound().build(); // Return 404 if not found
        }
        e.setId(id); // Make sure the ID is set correctly before saving
        Enrollment updatedEnrollment = service.save(e); // Save the updated enrollment
        return ResponseEntity.ok(updatedEnrollment); // Return the updated enrollment
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
