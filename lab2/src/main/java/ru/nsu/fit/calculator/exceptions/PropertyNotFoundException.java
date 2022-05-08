package ru.nsu.fit.calculator.exceptions;

public class PropertyNotFoundException extends RuntimeException {
    public PropertyNotFoundException(String message) {
        super(message);
    }

    public PropertyNotFoundException(String message, Throwable exception) {
        super(message,exception);
    }
}
