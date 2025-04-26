package com.example.lab8.spec;

import com.example.lab8.entity.*;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.Map;

public class StudentSpecification {
    public static Specification<Student> build(Map<String, String> params) {
        return (root, query, cb) -> {
            var predicates = cb.conjunction();
            if (params.containsKey("name"))
                predicates = cb.and(predicates, cb.equal(root.get("name"), params.get("name")));
            if (params.containsKey("name_like"))
                predicates = cb.and(predicates, cb.like(root.get("name"), params.get("name_like") + "%"));
            if (params.containsKey("email"))
                predicates = cb.and(predicates, cb.equal(root.get("email"), params.get("email")));
            if (params.containsKey("groupName"))
                predicates = cb.and(predicates, cb.equal(root.get("groupName"), params.get("groupName")));
            return predicates;
        };
    }
}