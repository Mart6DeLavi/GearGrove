package com.nznext.geargrove.products.exception;

/**
 * Custom exception for cases when a product already exists in the system.
 *
 * <p>This exception is thrown when an attempt is made to add or create a product that already exists in the system.
 * It extends {@link RuntimeException} and provides a constructor for passing a custom error message.</p>
 *
 * <p>Annotations used:</p>
 * <ul>
 *   <li>{@code @NoArgsConstructor} - Generates a constructor with no arguments.</li>
 * </ul>
 */
public class ProductAlreadyExistException extends RuntimeException {

    /**
     * Constructs a new {@link ProductAlreadyExistException} with the specified detail message.
     *
     * <p>This constructor allows setting a custom error message to provide more information about the exception.</p>
     *
     * @param message the detail message (which is saved for later retrieval by the {@link Throwable#getMessage()} method).
     */
    public ProductAlreadyExistException(String message) {
        super(message);
    }
}
