package com.flowershop.menu.commands;

import com.flowershop.menu.Menu;

public class ReturnToPreviousMenuCommand implements Command {

    private Menu menu;

    public ReturnToPreviousMenuCommand(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void execute() {
        menu.returnToPreviousMenu();
    }
}