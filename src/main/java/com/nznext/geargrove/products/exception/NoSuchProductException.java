package com.nznext.geargrove.products.exception;

public class NoSuchProductException extends RuntimeException {
    public NoSuchProductException(String message) {
        super(message);
    }
    public NoSuchProductException(String message, Throwable cause) { super(message, cause); }
    public NoSuchProductException(Throwable cause) { super(cause); }
}