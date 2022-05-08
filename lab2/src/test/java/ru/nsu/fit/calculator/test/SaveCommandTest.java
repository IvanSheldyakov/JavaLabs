package ru.nsu.fit.calculator.test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.calculator.Context;
import ru.nsu.fit.calculator.commands.AverageCommand;
import ru.nsu.fit.calculator.commands.Command;
import ru.nsu.fit.calculator.commands.PushCommand;
import ru.nsu.fit.calculator.commands.SaveCommand;
import ru.nsu.fit.calculator.exceptions.IncorrectAmountOfArgsException;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class SaveCommandTest {
    private static Command push;
    private static Command save;

    @BeforeAll
    public static void setUp() {
        push = new PushCommand();
        save = new SaveCommand();
    }

    @Test
    public void checkCommandWork() {
        Context context = new Context();
        List<String> args = new ArrayList<>();
        args.add("SAVE");

        args.add(UtilityForTests.correctValue1);
        push.execute(context,args);
        push.execute(context,args);
        UtilityForTests.removeAndAddArg(args,1,UtilityForTests.correctValue2);
        push.execute(context,args);
        UtilityForTests.removeAndAddArg(args,1,UtilityForTests.correctFileName);
        save.execute(context,args);

        assertArrayEquals(read(),context.getElements(3));
    }

    private Double[] read() {
        Double[] elements = new Double[3];
        try(Scanner scanner = new Scanner(new File(UtilityForTests.correctFileName))) {
            for (int i = 2; i >= 0; i--) {
                elements[i] = scanner.nextDouble();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return elements;
    }

    @Test
    public void checkArgsExceptions() {
        Context context = new Context();
        List<String> args = new ArrayList<>();
        args.add("SAVE");


        String expected = "save command needs 1 arg";
        Exception exception = assertThrows(IncorrectAmountOfArgsException.class,
                () -> save.execute(context,args));
        assertEquals(expected,exception.getMessage());

        expected = "problem with file " + UtilityForTests.incorrectFileName;
        args.add(UtilityForTests.incorrectFileName);
        exception = assertThrows(IllegalArgumentException.class,
                () -> save.execute(context,args));

        assertEquals(expected,exception.getMessage());
    }
}
