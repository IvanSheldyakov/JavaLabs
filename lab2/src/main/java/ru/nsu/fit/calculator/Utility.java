package ru.nsu.fit.calculator;





import ru.nsu.fit.calculator.exceptions.CantReadFileException;
import ru.nsu.fit.calculator.exceptions.CantWriteFileException;
import ru.nsu.fit.calculator.exceptions.IsNotFileException;

import java.io.File;

import java.io.IOException;

public class Utility {

    public static final String anyWordBetweenWhiteSpaces = "\\S+";

    public static void validateFile(File file, String option) throws IOException {

        if (!file.isFile()) {
            throw new IsNotFileException("is not file: " + file.getName());
        } else if ("r".equals(option) && !file.canRead()) {
            throw new CantReadFileException("cant read file " + file.getName());
        } else if ("w".equals(option) && !file.canWrite()) {
            throw new CantWriteFileException("cant write to file " + file.getName());
        }
    }
}
