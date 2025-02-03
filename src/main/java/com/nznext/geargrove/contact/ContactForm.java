package com.nznext.geargrove.contact;

/**
 * Represents a contact form submitted by a user in the GearGrove application.
 *
 * <p>This class contains the fields that make up the contact form, including the user's name,
 * email, subject, and message. It is used to capture the form data sent by the client in HTTP requests.</p>
 *
 * <p>Annotations used:</p>
 * <ul>
 *   <li>{@code @Getter} and {@code @Setter} - Automatically generate getter and setter methods
 *   for all fields in the form.</li>
 * </ul>
 */
public class ContactForm {

    /** The name of the person submitting the contact form. */
    private String name;

    /** The email address of the person submitting the contact form. */
    private String email;

    /** The subject of the contact form. */
    private String subject;

    /** The message provided by the person submitting the contact form. */
    private String message;


    /**
     * Gets the name of the person submitting the contact form.
     *
     * @return the name of the person submitting the contact form.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the person submitting the contact form.
     *
     * @param name the name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the email address of the person submitting the contact form.
     *
     * @return the email address of the person submitting the contact form.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the person submitting the contact form.
     *
     * @param email the email address to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the subject of the contact form.
     *
     * @return the subject of the contact form.
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets the subject of the contact form.
     *
     * @param subject the subject to set.
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Gets the message of the contact form.
     *
     * @return the message of the contact form.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message of the contact form.
     *
     * @param message the message to set.
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
