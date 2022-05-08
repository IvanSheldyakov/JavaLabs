package ru.nsu.fit.calculator.test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.calculator.Context;
import ru.nsu.fit.calculator.commands.ABSCommand;
import ru.nsu.fit.calculator.commands.Command;
import ru.nsu.fit.calculator.commands.PushCommand;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ABSCommandTest {
    private static Command push;
    private static Command abs;

    @BeforeAll
    public static void setUp() {
        push = new PushCommand();
        abs = new ABSCommand();
    }

    @Test
    public void checkCommandWork() {
        Context context = new Context();
        List<String> args = new ArrayList<>();
        args.add("ABS");

        String expected = "stack is empty";
        Exception exception = assertThrows(ru.nsu.fit.calculator.exceptions.EmptyStackException.class,
                () -> abs.execute(context,args));

        assertEquals(expected,exception.getMessage());

        args.add(UtilityForTests.correctNegativeValue);
        push.execute(context,args);
        abs.execute(context,args);

        Double expectedNumber = Math.abs(Double.parseDouble(UtilityForTests.correctNegativeValue));
        assertEquals(expectedNumber,context.peek());
    }
}
