package ru.nsu.fit.calculator.commands;

import org.apache.log4j.Logger;
import ru.nsu.fit.calculator.Context;
import ru.nsu.fit.calculator.exceptions.IncorrectAmountOfArgsException;

import java.util.List;

public class DefineCommand implements Command {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Override
    public void execute(Context context, List<String> args) {
        validate(args);
        String name = args.get(1);
        Double value = Double.parseDouble(args.get(2));
        context.setConstant(name,value);
        logger.info(args.get(0) +  " command finishes work");
    }

    private void validate(List<String> args) {
        if (args.size() != 3) {
            throw new IncorrectAmountOfArgsException("define command needs 2 args");
        }
    }
}
