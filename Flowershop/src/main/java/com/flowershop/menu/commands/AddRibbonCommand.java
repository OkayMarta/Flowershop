package com.flowershop.menu.commands;

import com.flowershop.manager.BouquetManager;
import com.flowershop.model.accessories.Ribbon;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddRibbonCommand implements Command {

    private static final Logger logger = Logger.getLogger(AddRibbonCommand.class.getName());
    private BouquetManager bouquetManager;
    private Scanner scanner;

    public AddRibbonCommand(BouquetManager bouquetManager) {
        this.bouquetManager = bouquetManager;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        if (bouquetManager.hasAccessoryOfType(Ribbon.class)) {
            System.out.print("Стрічка вже існує. Замінити її? (так/ні): ");
            String answer = scanner.nextLine();

            if (answer.equalsIgnoreCase("так")) {
                bouquetManager.removeAccessoryOfType(Ribbon.class);
                logger.info("Існуюча стрічка видалена.");
                getAndAddRibbon();
                System.out.println("Стрічка замінена.");
            } else {
                System.out.println("Заміна стрічки скасована.");
                logger.info("Заміну стрічки скасовано користувачем.");
            }
        } else {
            getAndAddRibbon();
            System.out.println("Стрічка додана до букету.");
            logger.info("Нова стрічка додана в букет.");
        }
    }

    private void getAndAddRibbon() {
        try {
            System.out.println("Введіть характеристики стрічки:");
            String material = getInput("Матеріал: ");
            String color = getInput("Колір: ");
            double price = getDoubleInput("Ціна (грн): ");
            double width = getDoubleInput("Ширина (см): ");
            double length = getDoubleInput("Довжина (см): ");

            Ribbon ribbon = new Ribbon(material, color, price, width, length);
            bouquetManager.addAccessory(ribbon);
            logger.info("Стрічка успішно створена та додана: " + ribbon.toString());
        } catch (Exception e) {
            System.out.println("Помилка при додаванні стрічки.");
            logger.log(Level.SEVERE, "Помилка при додаванні стрічки: " + e.getMessage(), e);
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
