package com.example.lab8.controller;

import com.example.lab8.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emails")
public class MassEmailController {
    @Autowired private EmailService emailService;

    @Operation(summary = "Send mass email to multiple students")
    @ApiResponse(responseCode = "200", description = "Emails sent successfully")
    @PostMapping("/send-to-students")
    public void sendToStudents(@RequestBody MassEmailRequest request) {
        emailService.sendSimple(request.getEmails(), request.getSubject(), request.getBody());
    }

    @Setter
    @Getter
    static class MassEmailRequest {
        private String[] emails;
        private String subject;
        private String body;

    }
}

