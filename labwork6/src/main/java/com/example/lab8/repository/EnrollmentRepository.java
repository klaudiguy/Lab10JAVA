package com.example.lab8.repository;

import com.example.lab8.entity.Enrollment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    Page<Enrollment> findAll(Specification<Enrollment> spec, Pageable pageable);
}
