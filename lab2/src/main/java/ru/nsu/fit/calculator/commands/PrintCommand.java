package ru.nsu.fit.calculator.commands;




import org.apache.log4j.Logger;
import ru.nsu.fit.calculator.Context;

import java.util.List;

public class PrintCommand implements Command {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Override
    public void execute(Context context, List<String> args) {
        System.out.println(context.peek());
        logger.info(args.get(0) +  " command finishes work");
    }
}
