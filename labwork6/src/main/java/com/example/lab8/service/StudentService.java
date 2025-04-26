package com.example.lab8.service;

import com.example.lab8.entity.Student;
import com.example.lab8.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {
    @Autowired
    private StudentRepository repository;

    public Page<Student> getAll(Specification<Student> spec, Pageable pageable) {
        return repository.findAll(spec, pageable);
    }

    public Optional<Student> getById(Long id) { return repository.findById(id); }
    public Student save(Student s) { return repository.save(s); }
    public void delete(Long id) { repository.deleteById(id); }
}

