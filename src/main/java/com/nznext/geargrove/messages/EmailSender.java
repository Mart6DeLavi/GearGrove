package com.nznext.geargrove.messages;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@AllArgsConstructor
public class EmailSender {

    private final JavaMailSender mailSender;

    public void sendEmail(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedLoginTime = now.format(formatter);

        String osName = System.getProperty("os.name");
        String osVersion = System.getProperty("os.version");
        String osArch = System.getProperty("os.arch");

        String emailBody = body +
                "\n\nВремя авторизации: " + formattedLoginTime +
                "\nСистема пользователя: " + osName + " " + osVersion + " (" + osArch + ")";

        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(emailBody);

        mailSender.send(message);
        System.out.println("Mail sent successfully");
    }
}
