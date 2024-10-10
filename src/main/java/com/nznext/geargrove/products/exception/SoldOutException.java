package com.nznext.geargrove.products.exception;

public class SoldOutException extends RuntimeException {
    public SoldOutException(String message) {
        super(message);
    }
    public SoldOutException(String message, Throwable cause) { super(message, cause); }
    public SoldOutException(Throwable cause) { super(cause); }
}
