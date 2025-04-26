package com.example.lab8.controller;

import com.example.lab8.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private final EmailService emailService;

    @PostMapping("/send-simple-email")
    public void sendSimple(@RequestBody EmailRequest request) {
        emailService.sendSimple(request.getTo(), request.getSubject(), request.getBody());
    }

    @PostMapping("/send-html-email")
    public void sendHtml(@RequestBody EmailRequest request) throws MessagingException {
        emailService.sendHtml(request.getTo(), request.getSubject(), request.getBody());
    }

    @PostMapping(value = "/send-with-attachment", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> sendWithAttachment(
            @ModelAttribute EmailRequest data,
            @RequestPart("file") MultipartFile file
    ) throws IOException, MessagingException {
        byte[] bytes = file.getBytes();
        emailService.sendWithAttachment(
                data.getTo(), data.getSubject(), data.getBody(),
                bytes, file.getOriginalFilename()
        );
        return ResponseEntity.ok("Email sent with attachment");
    }

    @Getter
    @Setter
    public static class EmailRequest {
        private String[] to;
        private String subject;
        private String body;
    }
}
