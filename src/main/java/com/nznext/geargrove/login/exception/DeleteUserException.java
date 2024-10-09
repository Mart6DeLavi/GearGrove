package com.nznext.geargrove.login.exception;

public class DeleteUserException extends RuntimeException {
    public DeleteUserException(String message) {
        super(message);
    }
    public DeleteUserException(String message, Throwable cause) { super(message, cause); }
    public DeleteUserException(Throwable cause) { super(cause); }
}
