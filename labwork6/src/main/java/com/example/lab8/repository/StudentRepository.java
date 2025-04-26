package com.example.lab8.repository;

import com.example.lab8.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Page<Student> findAll(Specification<Student> spec, Pageable pageable);
}
