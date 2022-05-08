package ru.nsu.fit.calculator.exceptions;

public class IncorrectSizeException extends RuntimeException {
    public IncorrectSizeException(String errorMessage, Throwable err) {
        super(errorMessage,err);
    }

    public IncorrectSizeException(String errorMessage) {
        super(errorMessage);
    }
}
