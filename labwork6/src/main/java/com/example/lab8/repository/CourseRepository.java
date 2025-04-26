package com.example.lab8.repository;

import com.example.lab8.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Page<Course> findAll(Specification<Course> spec, Pageable pageable);
}
