package ru.nsu.fit.calculator.test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.calculator.Context;
import ru.nsu.fit.calculator.commands.AddCommand;
import ru.nsu.fit.calculator.commands.AverageCommand;
import ru.nsu.fit.calculator.commands.Command;
import ru.nsu.fit.calculator.commands.PushCommand;
import ru.nsu.fit.calculator.exceptions.IncorrectAmountOfArgsException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AverageCommandTest {
    private static Command push;
    private static Command avs;

    @BeforeAll
    public static void setUp() {
        push = new PushCommand();
        avs = new AverageCommand();
    }

    @Test
    public void checkCommandWork() {
        Context context = new Context();
        List<String> args = new ArrayList<>();
        args.add("AVS");

        args.add(UtilityForTests.correctValue1);
        pushValueNTimes(context,args,3);
        UtilityForTests.removeAndAddArg(args,1,"4");

        String expected = "size is too big";
        Exception exception = assertThrows(ru.nsu.fit.calculator.exceptions.IncorrectSizeException.class,
                () -> avs.execute(context,args));

        assertEquals(expected,exception.getMessage());

        UtilityForTests.removeAndAddArg(args,1,"3");
        avs.execute(context,args);

        Double expectedNumber = Double.parseDouble(UtilityForTests.correctValue1);
        assertEquals(expectedNumber,context.peek());
    }

    private void pushValueNTimes(Context context, List<String> args, int N) {
        for (int i = 0; i < N; i++) {
            push.execute(context,args);
        }
    }

    @Test
    public void checkArgsExceptions() {
        Context context = new Context();
        List<String> args = new ArrayList<>();
        args.add("AVS");


        String expected = "AVG command needs 1 arg";
        Exception exception = assertThrows(IncorrectAmountOfArgsException.class,
                () -> avs.execute(context,args));
        assertEquals(expected,exception.getMessage());

        expected = "For input string: \"34.5\"";
        args.add("34.5");
        exception = assertThrows(NumberFormatException.class,
                () -> avs.execute(context,args));

        assertEquals(expected,exception.getMessage());
    }
}
