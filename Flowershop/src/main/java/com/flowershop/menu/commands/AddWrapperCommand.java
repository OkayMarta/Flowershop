package com.flowershop.menu.commands;

import com.flowershop.manager.BouquetManager;
import com.flowershop.model.accessories.Wrapper;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddWrapperCommand implements Command {

    private static final Logger logger = Logger.getLogger(AddWrapperCommand.class.getName());
    private BouquetManager bouquetManager;
    private Scanner scanner;

    public AddWrapperCommand(BouquetManager bouquetManager) {
        this.bouquetManager = bouquetManager;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        if (bouquetManager.hasAccessoryOfType(Wrapper.class)) {
            System.out.print("Обгортка вже існує. Замінити її? (так/ні): ");
            String answer = scanner.nextLine();

            if (answer.equalsIgnoreCase("так")) {
                bouquetManager.removeAccessoryOfType(Wrapper.class);
                logger.info("Існуюча обгортка видалена.");
                getAndAddWrapper();
                System.out.println("Обгортка замінена.");
            } else {
                System.out.println("Заміна обгортки скасована.");
                logger.info("Заміну обгортки скасовано користувачем.");
            }
        } else {
            getAndAddWrapper();
            System.out.println("Обгортка додана до букету.");
            logger.info("Нова обгортка додана в букет.");
        }
    }

    private void getAndAddWrapper() {
        try {
            System.out.println("Введіть характеристики обгортки:");
            String material = getInput("Матеріал: ");
            String color = getInput("Колір: ");
            double price = getDoubleInput("Ціна (грн): ");
            String size = getInput("Розмір: ");

            Wrapper wrapper = new Wrapper(material, color, price, size);
            bouquetManager.addAccessory(wrapper);
            logger.info("Обгортка успішно створена та додана: " + wrapper.toString());
        } catch (Exception e) {
            System.out.println("Помилка при додаванні обгортки.");
            logger.log(Level.SEVERE, "Помилка при додаванні обгортки: " + e.getMessage(), e);
        }
    }

    private String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
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
