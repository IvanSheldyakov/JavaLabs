package ru.nsu.fit.calculator.commands;




import ru.nsu.fit.calculator.Context;

import java.util.List;

public interface Command {
    void execute(Context context, List<String> args);
}
