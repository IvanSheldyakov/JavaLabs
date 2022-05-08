package ru.nsu.fit.calculator.exceptions;

public class CantCreateCommandException extends RuntimeException {
    public CantCreateCommandException(String message) {
        super(message);
    }
    public CantCreateCommandException(String message, Throwable exception) {
        super(message,exception);
    }
}
