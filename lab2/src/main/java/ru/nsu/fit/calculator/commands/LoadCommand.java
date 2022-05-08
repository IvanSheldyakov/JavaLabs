package ru.nsu.fit.calculator.commands;




import org.apache.log4j.Logger;
import ru.nsu.fit.calculator.Context;
import ru.nsu.fit.calculator.Utility;
import ru.nsu.fit.calculator.exceptions.IncorrectAmountOfArgsException;

import java.io.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoadCommand implements Command{

    private final Logger logger = Logger.getLogger(this.getClass());

    @Override
    public void execute(Context context, List<String> args) {
        validate(args);

        try (BufferedReader reader = new BufferedReader(new FileReader(args.get(1)))) {
            Pattern pattern = Pattern.compile(Utility.anyWordBetweenWhiteSpaces);
            String line;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                String elem;
                while (matcher.find()) {
                    elem = line.substring(matcher.start(),matcher.end());
                    context.push(Double.parseDouble(elem));
                }
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("problem with file " + args.get(1) ,e);
        }
        logger.info(args.get(0) +  " command finishes work with such context: " + context);
    }

    private void validate(List<String> args) {
        if (args.size() != 2) {
            throw new IncorrectAmountOfArgsException("load command needs 1 arg");
        }

        try {
            File file = new File(args.get(1));
            Utility.validateFile(file,"r");
        } catch (IOException e) {
            throw new IllegalArgumentException("problem with file " + args.get(1),e);
        }
    }
}
