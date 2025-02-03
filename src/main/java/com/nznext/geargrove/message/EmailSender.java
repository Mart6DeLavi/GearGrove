package com.nznext.geargrove.message;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Service for sending emails in the GearGrove application.
 *
 * <p>This service provides functionality to send simple text-based emails to recipients. It constructs the email
 * body with the provided content and adds additional details such as the time of login and system information.</p>
 *
 * <p>Annotations used:</p>
 * <ul>
 *   <li>{@code @Service} - Marks this class as a Spring service, making it eligible for dependency injection.</li>
 *   <li>{@code @AllArgsConstructor} - Generates a constructor with required arguments (final fields) for dependency injection.</li>
 * </ul>
 */
@Service
@AllArgsConstructor
public class EmailSender {

    /** The {@link JavaMailSender} instance used for sending emails. */
    private final JavaMailSender mailSender;

    /**
     * Sends an email with the specified subject and body to the provided recipient.
     *
     * <p>This method creates a simple email message, formats the current date and time, retrieves system information
     * such as the OS name and version, and appends these details to the email body before sending it to the recipient.</p>
     *
     * @param toEmail the recipient's email address.
     * @param subject the subject of the email.
     * @param body the body content of the email.
     */
    public void sendEmail(String toEmail, String subject, String body) {
        // Create a new simple mail message
        SimpleMailMessage message = new SimpleMailMessage();

        // Format the current date and time
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedLoginTime = now.format(formatter);

        // Retrieve system information (OS details)
        String osName = System.getProperty("os.name");
        String osVersion = System.getProperty("os.version");
        String osArch = System.getProperty("os.arch");

        // Construct the email body by adding system information
        String emailBody = body +
                "\n\nВремя авторизации: " + formattedLoginTime +
                "\nСистема пользователя: " + osName + " " + osVersion + " (" + osArch + ")";

        // Set up the email message
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(emailBody);

        // Send the email
        mailSender.send(message);
        System.out.println("Mail sent successfully");
    }
}
