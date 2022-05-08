package ru.nsu.fit.calculator.exceptions;

import java.io.IOException;


import java.io.IOException;

public class CantWriteFileException extends IOException {
    public CantWriteFileException(String errorMessage, Throwable err) {
        super(errorMessage,err);
    }

    public CantWriteFileException(String errorMessage) {
        super(errorMessage);
    }
}