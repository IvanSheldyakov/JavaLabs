package ru.nsu.fit.calculator.exceptions;

public class CantLoadPropertiesException extends RuntimeException {
    public CantLoadPropertiesException(String message) {
        super(message);
    }
    public CantLoadPropertiesException(String message, Throwable exception) {
        super(message,exception);
    }
}
