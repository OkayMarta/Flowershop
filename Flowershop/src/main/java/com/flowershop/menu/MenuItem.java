package com.flowershop.menu;

import com.flowershop.menu.commands.Command;
import java.util.List;

// Клас, що представляє пункт меню
public class MenuItem {

    private String title;
    private Command command;
    private List<MenuItem> subMenu;

    // Конструктор для пункту меню з командою
    public MenuItem(String title, Command command) {
        this.title = title;
        this.command = command;
        this.subMenu = null; // За замовчуванням підменю відсутнє
    }

    // Конструктор для пункту меню з підменю
    public MenuItem(String title, List<MenuItem> subMenu) {
        this.title = title;
        this.command = null; // Якщо є підменю, то команда не потрібна
        this.subMenu = subMenu;
    }

    public String getTitle() {
        return title;
    }

    // Виконання команди, якщо вона є
    public void executeCommand() {
        if (command != null) {
            command.execute();
        }
    }

    public Command getCommand() {
        return command;
    }

    // Отримання підменю, якщо воно є
    public List<MenuItem> getSubMenu() {
        return subMenu;
    }
}