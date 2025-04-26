package com.example.lab8.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "enrollment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "enrollment_date")
    private LocalDate enrollmentDate;

    @Column(name = "enrollment_at")
    private LocalDateTime enrolledAt = LocalDateTime.now();
}