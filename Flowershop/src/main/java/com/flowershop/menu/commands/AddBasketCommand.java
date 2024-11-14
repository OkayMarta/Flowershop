package com.flowershop.menu.commands;

import com.flowershop.manager.BouquetManager;
import com.flowershop.model.accessories.Basket;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddBasketCommand implements Command {

    private static final Logger logger = Logger.getLogger(AddBasketCommand.class.getName());
    private BouquetManager bouquetManager;
    private Scanner scanner;

    public AddBasketCommand(BouquetManager bouquetManager) {
        this.bouquetManager = bouquetManager;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        if (bouquetManager.hasAccessoryOfType(Basket.class)) {
            System.out.print("Кошик вже існує. Замінити його? (так/ні): ");
            String answer = scanner.nextLine();

            if (answer.equalsIgnoreCase("так")) {
                bouquetManager.removeAccessoryOfType(Basket.class);
                logger.info("Існуючий кошик видалено.");
                getAndAddBasket();
                System.out.println("Кошик замінений.");
            } else {
                System.out.println("Заміна кошика скасована.");
                logger.info("Заміну кошика скасовано користувачем.");
            }

        } else {
            getAndAddBasket();
            System.out.println("Кошик доданий до букету.");
            logger.info("Новий кошик доданий у букет.");
        }
    }

    public void getAndAddBasket() {
        System.out.println("Введіть характеристики кошика:");
        String material = getInput("Матеріал: ");
        String color = getInput("Колір: ");
        double price = getDoubleInput("Ціна (грн): ", scanner);
        String size = getInput("Розмір: ");

        Basket basket = new Basket(material, color, price, size);
        bouquetManager.addAccessory(basket);
        logger.info("Кошик успішно створений і доданий: " + basket.toString());
    }

    public String getInput(String prompt) {
        System.out.print(prompt);
        return this.scanner.nextLine();
    }

    public double getDoubleInput(String prompt, Scanner scanner) {
        while (true) {
            try {
                System.out.print(prompt);
                String inputLine = scanner.nextLine();
                double input = Double.parseDouble(inputLine);
                return input;
            } catch (NumberFormatException e) {
                System.out.println("Невірний формат вводу. Введіть число.");
                logger.warning("Помилка введення: неправильний формат числа для параметра " + prompt);
            }
        }
    }
}
