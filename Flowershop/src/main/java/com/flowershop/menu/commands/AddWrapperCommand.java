package com.flowershop.menu.commands;

import com.flowershop.manager.BouquetManager;
import com.flowershop.model.accessories.Wrapper;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AddWrapperCommand implements Command {

    private BouquetManager bouquetManager;
    private Scanner scanner;

    // Конструктор з параметром
    public AddWrapperCommand(BouquetManager bouquetManager) {
        this.bouquetManager = bouquetManager;
        this.scanner = new Scanner(System.in);
    }

    // Перевизначений метод інтерфейсу Command
    @Override
    public void execute() {
        // Перевірка на наявність обгортки
        if (bouquetManager.hasAccessoryOfType(Wrapper.class)) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Обгортка вже існує. Замінити її? (так/ні): ");
            String answer = scanner.nextLine();

            if (answer.equalsIgnoreCase("так")) {
                // Видалення існуючої обгортки
                bouquetManager.removeAccessoryOfType(Wrapper.class);

                // Введення даних та додавання нової обгортки
                getAndAddWrapper();
                System.out.println("Обгортка замінена.");
            } else {
                System.out.println("Заміна обгортки скасована.");
            }
        } else {
            // Введення даних та додавання обгортки
            getAndAddWrapper();
            System.out.println("Обгортка додана до букету.");
        }
    }

    // Метод для введення даних та додавання обгортки
    private void getAndAddWrapper() {
        System.out.println("Введіть характеристики обгортки:");
        String material = getInput("Матеріал: ");
        String color = getInput("Колір: ");
        double price = getDoubleInput("Ціна (грн): ");
        String size = getInput("Розмір: ");

        Wrapper wrapper = new Wrapper(material, color, price, size);
        bouquetManager.addAccessory(wrapper);
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