package com.flowershop.menu.commands;

import com.flowershop.manager.BouquetManager;
import com.flowershop.model.flowers.Orchid;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddOrchidCommand implements Command {

    private static final Logger logger = Logger.getLogger(AddOrchidCommand.class.getName());
    private BouquetManager bouquetManager;
    private Scanner scanner;

    public AddOrchidCommand(BouquetManager bouquetManager) {
        this.bouquetManager = bouquetManager;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        try {
            System.out.println("Введіть характеристики орхідеї:");

            String color = getInput("Колір: ");
            int freshnessLevel = getIntInput("Рівень свіжості (1-5): ", 1, 5);
            double stemLength = getDoubleInput("Довжина стебла (см): ");
            double flowerSize = getDoubleInput("Розмір квітки (см): ");
            double price = getDoubleInput("Ціна (грн): ");
            String originCountry = getInput("Країна походження: ");
            String orchidType = getInput("Тип орхідеї: ");
            String lipColor = getInput("Колір губи: ");

            Orchid orchid = new Orchid(color, freshnessLevel, stemLength, flowerSize, price, originCountry, orchidType, lipColor);
            bouquetManager.addFlower(orchid);

            System.out.println("Орхідея додана до букету.");
            logger.info("Орхідея успішно додана до букету: " + orchid.getName() + ", Колір: " + orchid.getColor() + ", Ціна: " + orchid.getPrice());
        } catch (Exception e) {
            System.out.println("Помилка при додаванні орхідеї.");
            logger.log(Level.SEVERE, "Помилка при додаванні орхідеї: " + e.getMessage(), e);
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
                String inputLine = scanner.nextLine();
                int input = Integer.parseInt(inputLine);
                if (input >= min && input <= max) {
                    return input;
                } else {
                    System.out.println("Введіть число від " + min + " до " + max + ".");
                    logger.warning("Невірний ввід числа для параметра '" + prompt + "'. Введено значення: " + input);
                }
            } catch (NumberFormatException e) {
                System.out.println("Невірний формат вводу. Введіть ціле число.");
                logger.warning("Невірний формат вводу для параметра '" + prompt + "'. Очікувано ціле число.");
            }
        }
    }

    private double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String inputLine = scanner.nextLine();
                double input = Double.parseDouble(inputLine);
                return input;
            } catch (NumberFormatException e) {
                System.out.println("Невірний формат вводу. Введіть число.");
                logger.warning("Невірний формат вводу для параметра '" + prompt + "'. Очікувано число з плаваючою комою.");
            }
        }
    }
}
