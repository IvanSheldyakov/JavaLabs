package ru.nsu.fit.calculator.exceptions;

public class EmptyStackException extends RuntimeException {
    public EmptyStackException(String errorMessage, Throwable err) {
        super(errorMessage,err);
    }

    public EmptyStackException(String errorMessage) {
        super(errorMessage);
    }
}
