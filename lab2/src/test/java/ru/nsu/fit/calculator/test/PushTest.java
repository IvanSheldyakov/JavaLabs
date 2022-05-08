package ru.nsu.fit.calculator.test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.calculator.Context;
import ru.nsu.fit.calculator.commands.Command;
import ru.nsu.fit.calculator.commands.PushCommand;
import ru.nsu.fit.calculator.exceptions.IncorrectAmountOfArgsException;
import ru.nsu.fit.calculator.exceptions.ThereIsNoSuchKeyException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class PushTest {
    private static Command push;


    @BeforeAll
    public static void setUp() {
        push = new PushCommand();
    }

    @Test
    public void checkChangeOfStackSize() {
        Context context = new Context();
        List<String> args = new ArrayList<>();
        args.add("PUSH");

        args.add(UtilityForTests.correctValue1);
        push.execute(context,args);

        UtilityForTests.removeAndAddArg(args,1,UtilityForTests.correctValue2);
        push.execute(context,args);

        int expected = 2;
        assertEquals(expected,context.getStackSize());
    }

    @Test
    public void checkChangeOfStack() {
        Context context = new Context();
        List<String> args = new ArrayList<>();
        args.add("PUSH");

        args.add(UtilityForTests.correctValue1);
        push.execute(context,args);
        assertEquals(Double.parseDouble(UtilityForTests.correctValue1),context.pop());

        UtilityForTests.removeAndAddArg(args,1,UtilityForTests.key1);
        context.setConstant(UtilityForTests.key1,Double.parseDouble(UtilityForTests.correctValue2));
        push.execute(context,args);
        assertEquals(Double.parseDouble(UtilityForTests.correctValue2),context.pop());
    }

    @Test
    public void checkIncorrectAmountOfArgsException() {
        Context context = new Context();
        List<String> args = new ArrayList<>();
        args.add("PUSH");

        String expected = "push command needs 1 arg";

        Exception exception = assertThrows(IncorrectAmountOfArgsException.class,() -> {
            push.execute(context,args);
        });
        assertEquals(expected,exception.getMessage());

        args.add(UtilityForTests.correctValue1);
        args.add(UtilityForTests.correctValue2);

        exception = assertThrows(IncorrectAmountOfArgsException.class,() -> {
            push.execute(context,args);
        });
        assertEquals(expected,exception.getMessage());

    }

    @Test
    public void checkThereIsNotSuchKeyException() {
        Context context = new Context();
        List<String> args = new ArrayList<>();
        args.add("PUSH");

        String expected = UtilityForTests.key2 + " doesn't exist";

        args.add(UtilityForTests.key2);
        Exception exception = assertThrows(ThereIsNoSuchKeyException.class,() -> {
            push.execute(context,args);
        });
        assertEquals(expected,exception.getMessage());

    }
}
