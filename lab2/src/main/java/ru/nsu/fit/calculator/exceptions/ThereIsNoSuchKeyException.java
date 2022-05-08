package ru.nsu.fit.calculator.exceptions;

public class ThereIsNoSuchKeyException extends RuntimeException{
    public ThereIsNoSuchKeyException(String errorMessage, Throwable err) {
        super(errorMessage,err);
    }

    public ThereIsNoSuchKeyException(String errorMessage) {
        super(errorMessage);
    }
}
