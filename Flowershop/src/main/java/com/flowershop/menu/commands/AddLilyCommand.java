package com.flowershop.menu.commands;

import com.flowershop.manager.BouquetManager;
import com.flowershop.model.flowers.Lily;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddLilyCommand implements Command {

    private static final Logger logger = Logger.getLogger(AddLilyCommand.class.getName());
    private BouquetManager bouquetManager;
    private Scanner scanner;

    public AddLilyCommand(BouquetManager bouquetManager) {
        this.bouquetManager = bouquetManager;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        try {
            System.out.println("Введіть характеристики лілії:");

            String color = getInput("Колір: ");
            int freshnessLevel = getIntInput("Рівень свіжості (1-5): ", 1, 5);
            double stemLength = getDoubleInput("Довжина стебла (см): ");
            double flowerSize = getDoubleInput("Розмір квітки (см): ");
            double price = getDoubleInput("Ціна (грн): ");
            String originCountry = getInput("Країна походження: ");
            int numberOfFlowers = getIntInput("Кількість квіток на стеблі: ", 1, Integer.MAX_VALUE);
            String fragrance = getInput("Аромат лілії: ");

            Lily lily = new Lily(color, freshnessLevel, stemLength, flowerSize, price, originCountry, numberOfFlowers, fragrance);
            bouquetManager.addFlower(lily);

            System.out.println("Лілія додана до букету.");
            logger.info("Лілія успішно додана до букету: " + lily.getName() + ", Колір: " + lily.getColor() + ", Ціна: " + lily.getPrice());
        } catch (Exception e) {
            System.out.println("Помилка при додаванні лілії.");
            logger.log(Level.SEVERE, "Помилка при додаванні лілії: " + e.getMessage(), e);
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
