package ru.nsu.fit.calculator.commands;


import org.apache.log4j.Logger;
import ru.nsu.fit.calculator.Context;
import ru.nsu.fit.calculator.exceptions.IncorrectAmountOfArgsException;

import java.util.List;

public class PushCommand implements Command {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Override
    public void execute(Context context, List<String> args) {
        validate(args);
        double number;

        try {
            number = Double.parseDouble(args.get(1));
        } catch (NumberFormatException e) {
            number = context.getValueByKey(args.get(1));
        }
        context.push(number);
        logger.info(args.get(0) +  " command finishes work with context: " + context);
    }

    private void validate(List<String> args) {
        if (args.size() != 2) {
            throw new IncorrectAmountOfArgsException("push command needs 1 arg");
        }
    }
}
