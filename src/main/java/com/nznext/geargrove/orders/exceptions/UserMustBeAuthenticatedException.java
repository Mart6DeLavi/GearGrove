package com.nznext.geargrove.orders.exceptions;

public class UserMustBeAuthenticatedException extends RuntimeException {
    public UserMustBeAuthenticatedException(String message) {
        super(message);
    }
    public UserMustBeAuthenticatedException(String message, Throwable cause) { super(message, cause); }
    public UserMustBeAuthenticatedException(Throwable cause) { super(cause); }
}
