package ru.nsu.fit.calculator;



import org.apache.log4j.Logger;

import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProgramCalculatorParser {

    private final Logger logger = Logger.getLogger(this.getClass());

    public static final char commentSymbol = '#';

    public List<List<String>> parse(String[] args)
            throws IOException{

        logger.info("parser starts working");
        List<List<String>> commands = new ArrayList<>();
        Pattern pattern = Pattern.compile(Utility.anyWordBetweenWhiteSpaces);

        try (Scanner scanner = initScanner(args)) {
             while (scanner.hasNextLine()) {
                 String line = scanner.nextLine();
                 if (!validateLine(line)) {continue;}
                 Matcher matcher = pattern.matcher(line);
                 List<String> command = new ArrayList<>();
                 while (matcher.find()) {
                        command.add(line.substring(matcher.start(),matcher.end()));
                 }
                 commands.add(command);
             }
        }
        logger.info("parser finishes work");
        return commands;
    }

    private Scanner initScanner(String[] args)
            throws IOException {
        Scanner scanner;
        if (args.length == 0) {
            scanner = new Scanner(System.in);
        } else {
            File file = new File(args[0]);
            Utility.validateFile(file,"r");
            scanner = new Scanner(file);
        }
        return scanner;
    }

    private boolean validateLine(String line) {
        if (line.isEmpty() || line.charAt(0) == commentSymbol) {
            return false;
        }
        return true;
    }
}
