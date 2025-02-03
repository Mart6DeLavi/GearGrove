package com.nznext.geargrove.products.exception;

/**
 * Custom exception for handling cases when a product is sold out.
 *
 * <p>This exception is thrown when an attempt is made to purchase or request a product that is out of stock.</p>
 *
 * <p>It extends {@link RuntimeException} and provides a constructor for passing a custom error message.</p>
 *
 * <p>Annotations used:</p>
 * <ul>
 *   <li>{@code @NoArgsConstructor} - Generates a constructor with no arguments.</li>
 * </ul>
 */
public class SoldOutException extends RuntimeException {

    /**
     * Constructs a new {@link SoldOutException} with the specified detail message.
     *
     * <p>This constructor allows setting a custom error message to provide more information about the exception.</p>
     *
     * @param message the detail message (which is saved for later retrieval by the {@link Throwable#getMessage()} method).
     */
    public SoldOutException(String message) {
        super(message);
    }
}

