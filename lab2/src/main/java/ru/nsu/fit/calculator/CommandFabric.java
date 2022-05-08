package ru.nsu.fit.calculator;

import org.apache.log4j.Logger;
import ru.nsu.fit.calculator.commands.Command;
import ru.nsu.fit.calculator.exceptions.CantCreateCommandException;
import ru.nsu.fit.calculator.exceptions.CantLoadPropertiesException;
import ru.nsu.fit.calculator.exceptions.PropertyNotFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CommandFabric {

    private static final Logger logger = Logger.getLogger(CommandFabric.class);

    private static volatile CommandFabric instance;

    private final InputStream in = CommandFabric.class.getResourceAsStream("app.properties");
    private final Properties properties = new Properties();


    private CommandFabric() {
        try {
            properties.load(in);
        } catch(Exception e) {
            throw new CantLoadPropertiesException("cant load properties",e);
        }
        logger.info("properties was loaded");
    }

    public static CommandFabric getInstance() {
        if (instance == null) {
            synchronized (CommandFabric .class) {
                if (instance == null) {
                    instance = new CommandFabric();
                    logger.info("fabric was initialized");
                }
            }
        }
        return instance;
    }

    public Command create(String commandName) {
        logger.info("fabric tries to create " + commandName);

        String className = properties.getProperty(commandName);
        if (className == null) {
            throw new PropertyNotFoundException("property " + commandName + " not found");
        }

        try {
            Class classOfCommand = Class.forName(className);
            Object command = classOfCommand.getDeclaredConstructor().newInstance();
            logger.info("fabric created " + commandName);
            return (Command) command;
        } catch (Exception e) {
            throw new CantCreateCommandException("cant create " + commandName,e);
        }
    }
}

