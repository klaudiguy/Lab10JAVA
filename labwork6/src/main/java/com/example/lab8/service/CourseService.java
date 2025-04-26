package com.example.lab8.service;

import com.example.lab8.entity.Course;
import com.example.lab8.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {
    @Autowired
    private CourseRepository repository;

    public Page<Course> getAll(Specification<Course> spec, Pageable pageable) {
        return repository.findAll(spec, pageable);
    }

    public Course updateCourse(Long id, Course updatedCourse) {
        Optional<Course> existingCourse = repository.findById(id);

        if (existingCourse.isPresent()) {
            Course course = existingCourse.get();
            course.setTitle(updatedCourse.getTitle());
            course.setCreditHours(updatedCourse.getCreditHours());
            course.setInstructorName(updatedCourse.getInstructorName());
            return repository.save(course);
        } else {
            throw new RuntimeException("Course not found");
        }
    }

    public Optional<Course> getById(Long id) { return repository.findById(id); }
    public Course save(Course c) { return repository.save(c); }
    public void delete(Long id) { repository.deleteById(id); }
}
