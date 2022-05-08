package ru.nsu.fit.calculator.test;

import org.junit.jupiter.api.Test;
import ru.nsu.fit.calculator.CommandFabric;
import ru.nsu.fit.calculator.Context;
import ru.nsu.fit.calculator.commands.Command;
import ru.nsu.fit.calculator.exceptions.CantLoadPropertiesException;
import ru.nsu.fit.calculator.exceptions.PropertyNotFoundException;

import javax.imageio.IIOException;
import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;;

public class CommandFabricTest {

    @Test
    public void checkFabricWork() {
        String expected = "property " + UtilityForTests.fictiveCommand + " not found";
        Exception exception = assertThrows(PropertyNotFoundException.class,
                () -> CommandFabric.getInstance().create(UtilityForTests.fictiveCommand));
        assertEquals(expected,exception.getMessage());
    }
}

