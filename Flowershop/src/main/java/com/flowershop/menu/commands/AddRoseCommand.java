package com.flowershop.menu.commands;

import com.flowershop.manager.BouquetManager;
import com.flowershop.model.flowers.Rose;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddRoseCommand implements Command {

    private static final Logger logger = Logger.getLogger(AddRoseCommand.class.getName());
    private BouquetManager bouquetManager;
    private Scanner scanner;

    public AddRoseCommand(BouquetManager bouquetManager) {
        this.bouquetManager = bouquetManager;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        try {
            System.out.println("Введіть характеристики троянди:");
            String color = getInput("Колір: ");
            int freshnessLevel = getIntInput("Рівень свіжості (1-5): ", 1, 5);
            double stemLength = getDoubleInput("Довжина стебла (см): ");
            double flowerSize = getDoubleInput("Розмір квітки (см): ");
            double price = getDoubleInput("Ціна (грн): ");
            String originCountry = getInput("Країна походження: ");
            boolean hasThorns = getBooleanInput("Наявність шипів (true/false): ");
            String roseType = getInput("Сорт троянди: ");

            Rose rose = new Rose(color, freshnessLevel, stemLength, flowerSize, price, originCountry, hasThorns, roseType);
            bouquetManager.addFlower(rose);
            System.out.println("Троянда додана до букету.");
            logger.info("Троянда успішно додана в букет: " + rose.getName());

        } catch (Exception e) {
            System.out.println("Помилка при додаванні троянди.");
            logger.log(Level.SEVERE, "Помилка при додаванні троянди: " + e.getMessage(), e);
        }
    }

    private String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private int getIntInput(String prompt, int min, int max) {
        while (true) {
            try {
                System.out.print(prompt);
                int input = Integer.parseInt(scanner.nextLine());
                if (input >= min && input <= max) {
                    return input;
                } else {
                    System.out.println("Введіть число від " + min + " до " + max + ".");
                }
            } catch (NumberFormatException e) {
                logger.warning("Помилка введення: неправильний формат числа для параметра " + prompt);
                System.out.println("Невірний формат вводу. Введіть ціле число.");
            }
        }
    }

    private double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                logger.warning("Помилка введення: неправильний формат числа для параметра " + prompt);
                System.out.println("Невірний формат вводу. Введіть число.");
            }
        }
    }

    private boolean getBooleanInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            if ("true".equalsIgnoreCase(input)) {
                return true;
            } else if ("false".equalsIgnoreCase(input)) {
                return false;
            } else {
                logger.warning("Помилка введення: неправильний формат значення бульова для параметра " + prompt);
                System.out.println("Невірний формат вводу. Введіть 'true' або 'false'.");
            }
        }
    }
}
