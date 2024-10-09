package com.logitrack.geargrove.login.exception;

public class NoSuchRoleException extends RuntimeException {
    public NoSuchRoleException(String message) {
        super(message);
    }
    public NoSuchRoleException(String message, Throwable cause) { super(message, cause); }
    public NoSuchRoleException(Throwable cause) { super(cause); }
}
