package com.flowershop.menu.commands;

import com.flowershop.manager.BouquetManager;
import com.flowershop.model.accessories.Postcard;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddPostcardCommand implements Command {

    private static final Logger logger = Logger.getLogger(AddPostcardCommand.class.getName());
    private BouquetManager bouquetManager;
    private Scanner scanner;

    public AddPostcardCommand(BouquetManager bouquetManager) {
        this.bouquetManager = bouquetManager;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        if (bouquetManager.hasAccessoryOfType(Postcard.class)) {
            System.out.print("Листівка вже існує. Замінити її? (так/ні): ");
            String answer = scanner.nextLine();

            if (answer.equalsIgnoreCase("так")) {
                bouquetManager.removeAccessoryOfType(Postcard.class);
                logger.info("Існуюча листівка видалена.");
                getAndAddPostcard();
                System.out.println("Листівка замінена.");
            } else {
                System.out.println("Заміна листівки скасована.");
                logger.info("Заміну листівки скасовано користувачем.");
            }
        } else {
            getAndAddPostcard();
            System.out.println("Листівка додана до букету.");
            logger.info("Нова листівка додана в букет.");
        }
    }

    private void getAndAddPostcard() {
        try {
            System.out.println("Введіть характеристики листівки:");
            String material = getInput("Матеріал: ");
            String color = getInput("Колір: ");
            double price = getDoubleInput("Ціна (грн): ");
            String greetingText = getInput("Текст привітання: ");

            Postcard postcard = new Postcard(material, color, price, greetingText);
            bouquetManager.addAccessory(postcard);
            logger.info("Листівка успішно створена і додана: " + postcard.toString());
        } catch (Exception e) {
            System.out.println("Помилка при додаванні листівки.");
            logger.log(Level.SEVERE, "Помилка при додаванні листівки: " + e.getMessage(), e);
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
