package com.flowershop.menu.commands;

import com.flowershop.menu.Menu;
import java.util.logging.Logger;

public class ReturnToMainMenuCommand implements Command {

    private static final Logger logger = Logger.getLogger(ReturnToMainMenuCommand.class.getName());
    private Menu menu;

    // Конструктор, якому передається посилання на об'єкт меню
    public ReturnToMainMenuCommand(Menu menu) {
        this.menu = menu;
    }

    // Перевизначений метод інтерфейсу Command
    @Override
    public void execute() {
        menu.returnToMainMenu();
        logger.info("Користувач повернувся до головного меню.");
    }
}