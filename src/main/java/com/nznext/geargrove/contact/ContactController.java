package com.nznext.geargrove.contact;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    @PostMapping
    public ResponseEntity<String> handleContactForm(@RequestBody ContactForm contactForm) {
        // Обработка полученных данных, например, сохранение в БД или отправка на email
        System.out.println("Received contact form: " + contactForm);
        return new ResponseEntity<>("Form submitted successfully", HttpStatus.OK);
    }
}
