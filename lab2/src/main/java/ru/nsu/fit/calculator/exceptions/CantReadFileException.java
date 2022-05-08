package ru.nsu.fit.calculator.exceptions;

import java.io.IOException;

public class CantReadFileException extends IOException {
    public CantReadFileException(String errorMessage, Throwable err) {
        super(errorMessage,err);
    }

    public CantReadFileException(String errorMessage) {
        super(errorMessage);
    }
}
