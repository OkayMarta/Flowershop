package com.flowershop.menu.commands;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ExitCommand implements Command {

    private static final Logger logger = Logger.getLogger(ExitCommand.class.getName());

    @Override
    public void execute() {
        System.out.println("Вихід з програми...");
        logger.log(Level.INFO, "Програма завершена користувачем.");
        System.exit(0); // Завершуємо роботу програми
    }
}
