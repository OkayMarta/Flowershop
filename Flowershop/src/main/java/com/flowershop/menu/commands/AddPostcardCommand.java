package com.flowershop.menu.commands;

import com.flowershop.manager.BouquetManager;
import com.flowershop.model.accessories.Postcard;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AddPostcardCommand implements Command {

    private BouquetManager bouquetManager;
    private Scanner scanner;

    // Конструктор з параметром
    public AddPostcardCommand(BouquetManager bouquetManager) {
        this.bouquetManager = bouquetManager;
        this.scanner = new Scanner(System.in);
    }

    // Перевизначений метод інтерфейсу Command
    @Override
    public void execute() {
        // Перевірка на наявність листівки
        if (bouquetManager.hasAccessoryOfType(Postcard.class)) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Листівка вже існує. Замінити її? (так/ні): ");
            String answer = scanner.nextLine();

            if (answer.equalsIgnoreCase("так")) {
                // Видалення існуючої листівки
                bouquetManager.removeAccessoryOfType(Postcard.class);

                // Введення даних та додавання нової листівки
                getAndAddPostcard();
                System.out.println("Листівка замінена.");
            } else {
                System.out.println("Заміна листівки скасована.");
            }
        } else {
            // Введення даних та додавання листівки
            getAndAddPostcard();
            System.out.println("Листівка додана до букету.");
        }
    }

    // Метод для введення даних та додавання листівки
    private void getAndAddPostcard() {
        System.out.println("Введіть характеристики листівки:");
        String material = getInput("Матеріал: ");
        String color = getInput("Колір: ");
        double price = getDoubleInput("Ціна (грн): ");
        String greetingText = getInput("Текст привітання: ");

        Postcard postcard = new Postcard(material, color, price, greetingText);
        bouquetManager.addAccessory(postcard);
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