package ru.nsu.fit.calculator.commands;



import org.apache.log4j.Logger;
import ru.nsu.fit.calculator.Context;
import ru.nsu.fit.calculator.Utility;
import ru.nsu.fit.calculator.exceptions.IncorrectAmountOfArgsException;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class SaveCommand implements Command {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Override
    public void execute(Context context, List<String> args) {
        validate(args);
        File file = new File(args.get(1));
        try (PrintWriter writer = new PrintWriter(file)){
            Utility.validateFile(file,"w");
            Double[] elements = context.getElements(context.getStackSize());
            for (int i = context.getStackSize()-1; i >= 0; i--) {
                writer.printf("%f ",elements[i]);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("problem with file " + args.get(1),e);
        }
        logger.info(args.get(0) +  " command finishes work");

    }
    private void validate(List<String> args) {
        if (args.size() != 2) {
            throw new IncorrectAmountOfArgsException("save command needs 1 arg");
        }
    }
}
