package ru.nsu.fit.calculator.commands;





import org.apache.log4j.Logger;
import ru.nsu.fit.calculator.Context;
import ru.nsu.fit.calculator.exceptions.IncorrectAmountOfArgsException;

import java.util.List;

public class AverageCommand implements Command{

    private final Logger logger = Logger.getLogger(this.getClass());

    @Override
    public void execute(Context context, List<String> args) {
        validate(args);
        int size = Integer.parseInt(args.get(1));
        Double[] values = context.getElements(size);

        double avg  = calculateAvg(values);
        context.push(avg);
        logger.info(args.get(0) +  " command finishes work with result: " + context.peek());
    }

    private double calculateAvg(Double[] values) {
        double sum = 0;
        for (double value : values) {
            sum += value;
        }
        return sum / values.length;
    }

    private void validate(List<String> args) {
        if (args.size() != 2) {
            throw new IncorrectAmountOfArgsException("AVG command needs 1 arg");
        }
    }
}
