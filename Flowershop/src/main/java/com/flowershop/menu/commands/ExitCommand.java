package com.flowershop.menu.commands;

public class ExitCommand implements Command {

    // Перевизначений метод інтерфейсу Command
    @Override
    public void execute() {
        System.out.println("Вихід з програми...");
        System.exit(0); // Завершуємо роботу програми
    }
}