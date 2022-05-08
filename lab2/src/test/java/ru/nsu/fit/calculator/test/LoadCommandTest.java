package ru.nsu.fit.calculator.test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.calculator.Context;
import ru.nsu.fit.calculator.commands.*;
import ru.nsu.fit.calculator.exceptions.IncorrectAmountOfArgsException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class LoadCommandTest {
    private static Command push;
    private static Command save;
    private static Command load;
    private static Command pop;

    @BeforeAll
    public static void setUp() {
        push = new PushCommand();
        save = new SaveCommand();
        load = new LoadCommand();
        pop = new PopCommand();
    }

    @Test
    public void checkCommandWork() {
        Context context = new Context();
        List<String> args = new ArrayList<>();
        args.add("LOAD");

        args.add(UtilityForTests.correctValue1);
        pushValueNTimes(context,args,2);
        UtilityForTests.removeAndAddArg(args,1,UtilityForTests.correctValue2);
        push.execute(context, args);
        UtilityForTests.removeAndAddArg(args,1,UtilityForTests.correctFileName);
        save.execute(context, args);

        Double[] expected = context.getElements(3);

        popValueNTimes(context,args,3);

        load.execute(context, args);

        assertArrayEquals(expected, context.getElements(3));
    }

    private void pushValueNTimes(Context context, List<String> args, int N) {
        for (int i = 0; i < N; i++) {
            push.execute(context,args);
        }
    }

    private void popValueNTimes(Context context, List<String> args, int N) {
        for (int i = 0; i < N; i++) {
            pop.execute(context,args);
        }
    }


    @Test
    public void checkArgsExceptions() {
        Context context = new Context();
        List<String> args = new ArrayList<>();
        args.add("LOAD");


        String expected = "load command needs 1 arg";
        Exception exception = assertThrows(IncorrectAmountOfArgsException.class,
                () -> load.execute(context, args));
        assertEquals(expected, exception.getMessage());

        expected = "problem with file " + UtilityForTests.incorrectFileName;
        args.add(UtilityForTests.incorrectFileName);
        exception = assertThrows(IllegalArgumentException.class,
                () -> load.execute(context, args));

        assertEquals(expected, exception.getMessage());
    }
}
