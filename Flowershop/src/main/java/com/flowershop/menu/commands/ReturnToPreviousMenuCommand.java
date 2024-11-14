package com.flowershop.menu.commands;

import com.flowershop.menu.Menu;
import java.util.logging.Logger;

public class ReturnToPreviousMenuCommand implements Command {

    private static final Logger logger = Logger.getLogger(ReturnToPreviousMenuCommand.class.getName());
    private Menu menu;

    // Конструктор приймає посилання на меню, з якого потрібно повернутись на попередній рівень
    public ReturnToPreviousMenuCommand(Menu menu) {
        this.menu = menu;
    }

    // Перевизначений метод інтерфейсу Command
    @Override
    public void execute() {
        menu.returnToPreviousMenu();
        logger.info("Користувач повернувся до попереднього меню.");
    }
}