package com.example.lab8.spec;

import com.example.lab8.entity.*;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.Map;


public class CourseSpecification {
    public static Specification<Course> build(Map<String, String> params) {
        return (root, query, cb) -> {
            var predicates = cb.conjunction();
            if (params.containsKey("title"))
                predicates = cb.and(predicates, cb.equal(root.get("title"), params.get("title")));
            if (params.containsKey("creditHours"))
                predicates = cb.and(predicates, cb.equal(root.get("creditHours"), Integer.parseInt(params.get("creditHours"))));
            if (params.containsKey("instructorName"))
                predicates = cb.and(predicates, cb.equal(root.get("instructorName"), params.get("instructorName")));
            return predicates;
        };
    }
}