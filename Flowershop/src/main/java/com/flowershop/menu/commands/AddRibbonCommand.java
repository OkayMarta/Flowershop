package com.flowershop.menu.commands;

import com.flowershop.manager.BouquetManager;
import com.flowershop.model.accessories.Ribbon;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AddRibbonCommand implements Command {

    private BouquetManager bouquetManager;
    private Scanner scanner;

    // Конструктор з параметром
    public AddRibbonCommand(BouquetManager bouquetManager) {
        this.bouquetManager = bouquetManager;
        this.scanner = new Scanner(System.in);
    }

    // Перевизначений метод інтерфейсу Command
    @Override
    public void execute() {
        // Перевірка на наявність стрічки
        if (bouquetManager.hasAccessoryOfType(Ribbon.class)) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Стрічка вже існує. Замінити її? (так/ні): ");
            String answer = scanner.nextLine();

            if (answer.equalsIgnoreCase("так")) {
                // Видалення існуючої стрічки
                bouquetManager.removeAccessoryOfType(Ribbon.class);

                // Введення даних та додавання нової стрічки
                getAndAddRibbon();
                System.out.println("Стрічка замінена.");
            } else {
                System.out.println("Заміна стрічки скасована.");
            }
        } else {
            // Введення даних та додавання стрічки
            getAndAddRibbon();
            System.out.println("Стрічка додана до букету.");
        }
    }

    // Метод для введення даних та додавання стрічки
    private void getAndAddRibbon() {
        System.out.println("Введіть характеристики стрічки:");
        String material = getInput("Матеріал: ");
        String color = getInput("Колір: ");
        double price = getDoubleInput("Ціна (грн): ");
        double width = getDoubleInput("Ширина (см): ");
        double length = getDoubleInput("Довжина (см): ");

        Ribbon ribbon = new Ribbon(material, color, price, width, length);
        bouquetManager.addAccessory(ribbon);
    }

    // Метод для отримання введення користувача у вигляді рядка
    private String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    // Метод для отримання введення користувача у вигляді дійсного числа
    private double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double input = scanner.nextDouble();
                scanner.nextLine(); // Очищення буфера
                return input;
            } catch (InputMismatchException e) {
                System.out.println("Невірний формат вводу. Введіть число.");
                scanner.nextLine(); // Очищення буфера
            }
        }
    }
}