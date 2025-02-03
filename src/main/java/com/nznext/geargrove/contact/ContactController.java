package com.nznext.geargrove.contact;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling contact form submissions in the GearGrove application.
 *
 * <p>This controller provides an endpoint to handle POST requests sent to {@code /api/contact}.
 * It receives a contact form, processes it, and returns a response indicating the success of the submission.</p>
 *
 * <p>Annotations used:</p>
 * <ul>
 *   <li>{@code @RestController} - Marks this class as a controller where each method returns
 *   a domain object rather than a view (RESTful web services).</li>
 *   <li>{@code @RequestMapping} - Maps HTTP requests to the {@code /api/contact} path to this controller.</li>
 * </ul>
 */
@RestController
@RequestMapping("/api/contact")
public class ContactController {

    /**
     * Handles the contact form submission.
     *
     * <p>This method processes the POST request received at {@code /api/contact} with the data sent
     * in the request body. It returns a success message if the form submission is processed correctly.</p>
     *
     * @param contactForm the contact form data received from the client in the request body.
     * @return a {@link ResponseEntity} containing a success message and HTTP status {@code OK} (200).
     */
    @PostMapping
    public ResponseEntity<String> handleContactForm(@RequestBody ContactForm contactForm) {
        // Log the received contact form data (for demo purposes)
        System.out.println("Received contact form: " + contactForm);

        // Return a success response
        return new ResponseEntity<>("Form submitted successfully", HttpStatus.OK);
    }
}
