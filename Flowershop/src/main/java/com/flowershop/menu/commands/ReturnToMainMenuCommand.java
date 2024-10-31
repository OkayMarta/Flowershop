package com.flowershop.menu.commands;

import com.flowershop.menu.Menu;

public class ReturnToMainMenuCommand implements Command {

    private Menu menu; // Посилання на об'єкт меню

    public ReturnToMainMenuCommand(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void execute() {
        menu.returnToMainMenu(); // Викликаємо метод меню для повернення до головного меню
    }
}