package com.example.lab8.spec;

import com.example.lab8.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.Map;

public class EnrollmentSpecification {

    public static Specification<Enrollment> build(Map<String, String> params) {
        return (root, query, cb) -> {
            var predicates = cb.conjunction();
            if (params.containsKey("studentId"))
                predicates = cb.and(predicates, cb.equal(root.get("student").get("id"), Long.parseLong(params.get("studentId"))));
            if (params.containsKey("courseId"))
                predicates = cb.and(predicates, cb.equal(root.get("course").get("id"), Long.parseLong(params.get("courseId"))));
            if (params.containsKey("enrollmentDate"))
                predicates = cb.and(predicates, cb.equal(root.get("enrollmentDate"), LocalDate.parse(params.get("enrollmentDate"))));
            return predicates;
        };
    }
}