package ru.nsu.fit.calculator.exceptions;

public class IncorrectAmountOfArgsException extends RuntimeException {
    public IncorrectAmountOfArgsException(String errorMessage, Throwable err) {
        super(errorMessage,err);
    }

    public IncorrectAmountOfArgsException(String errorMessage) {
        super(errorMessage);
    }
}
