package com.example.lab8.service;

import com.example.lab8.entity.Enrollment;
import com.example.lab8.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EnrollmentService {
    @Autowired private EnrollmentRepository repository;
    @Autowired private EmailService emailService;

    public Page<Enrollment> getAll(Specification<Enrollment> spec, Pageable pageable) {
        return repository.findAll(spec, pageable);
    }

    public Optional<Enrollment> getById(Long id) { return repository.findById(id); }

    public Enrollment save(Enrollment e) {
        Enrollment saved = repository.save(e);
        try {
            String subject = "Enrollment Confirmation";
            String body = String.format(
                    "Dear %s,\n\nYou have been successfully enrolled in the course \"%s\" starting from %s.\n\nBest regards,",
                    e.getStudent().getName(),
                    e.getCourse().getTitle(),
                    e.getEnrollmentDate()
            );
            emailService.sendSimple(new String[]{e.getStudent().getEmail()}, subject, body);
        } catch (Exception ex) {
            System.err.println("Failed to send enrollment email: " + ex.getMessage());
        }
        return saved;
    }

    public void delete(Long id) { repository.deleteById(id); }
}