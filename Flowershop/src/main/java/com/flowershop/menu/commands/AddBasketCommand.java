package com.flowershop.menu.commands;

import com.flowershop.manager.BouquetManager;
import com.flowershop.model.accessories.Basket;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AddBasketCommand implements Command {

    private BouquetManager bouquetManager;
    private Scanner scanner;

    // Конструктор з параметром
    public AddBasketCommand(BouquetManager bouquetManager) {
        this.bouquetManager = bouquetManager;
        this.scanner = new Scanner(System.in);
    }

    // Перевизначений метод інтерфейсу Command
    @Override
    public void execute() {

        if (bouquetManager.hasAccessoryOfType(Basket.class)) {
            System.out.print("Кошик вже існує. Замінити його? (так/ні): ");
            String answer = scanner.nextLine();

            if (answer.equalsIgnoreCase("так")) {

                bouquetManager.removeAccessoryOfType(Basket.class);

                getAndAddBasket();
                System.out.println("Кошик замінений.");
            } else {
                System.out.println("Заміна кошика скасована.");
            }
        } else {

            getAndAddBasket();
            System.out.println("Кошик доданий до букету.");
        }
    }

    // Метод для створення кошика та додавання його до букету
    protected void getAndAddBasket() {
        System.out.println("Введіть характеристики кошика:");
        String material = getInput("Матеріал: ");
        String color = getInput("Колір: ");
        double price = getDoubleInput("Ціна (грн): ");
        String size = getInput("Розмір: ");

        Basket basket = new Basket(material, color, price, size);
        bouquetManager.addAccessory(basket);
    }

    // Метод для отримання введення від користувача
    protected String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    // Метод для отримання введення користувача у вигляді дійсного числа
    protected double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double input = scanner.nextDouble();
                scanner.nextLine(); // Очистка буфера
                return input;
            } catch (InputMismatchException e) {
                System.out.println("Невірний формат вводу. Введіть число.");
                scanner.nextLine(); // Очистка буфера
            }
        }
    }
}