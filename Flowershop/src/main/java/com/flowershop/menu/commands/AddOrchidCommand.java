package com.flowershop.menu.commands;

import com.flowershop.manager.BouquetManager;
import com.flowershop.model.flowers.Orchid;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AddOrchidCommand implements Command {

    private BouquetManager bouquetManager;
    private Scanner scanner;

    // Конструктор з параметром
    public AddOrchidCommand(BouquetManager bouquetManager) {
        this.bouquetManager = bouquetManager;
        this.scanner = new Scanner(System.in);
    }

    // Перевизначений метод інтерфейсу Command
    @Override
    public void execute() {
        System.out.println("Введіть характеристики орхідеї:");

        String color = getInput("Колір: ");
        int freshnessLevel = getIntInput("Рівень свіжості (1-5): ", 1, 5);
        double stemLength = getDoubleInput("Довжина стебла (см): ");
        double flowerSize = getDoubleInput("Розмір квітки (см): ");
        double price = getDoubleInput("Ціна (грн): ");
        String originCountry = getInput("Країна походження: ");
        String orchidType = getInput("Тип орхідеї: ");
        String lipColor = getInput("Колір губи: ");

        // Створення об'єкта орхідеї та додавання його до букету
        Orchid orchid = new Orchid(color, freshnessLevel, stemLength, flowerSize, price, originCountry, orchidType, lipColor);
        bouquetManager.addFlower(orchid);

        System.out.println("Орхідея додана до букету.");
    }

    // Метод для отримання введення користувача у вигляді рядка
    private String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    // Метод для отримання введення користувача у вигляді цілого числа
    private int getIntInput(String prompt, int min, int max) {
        while (true) {
            try {
                System.out.print(prompt);
                int input = scanner.nextInt();
                scanner.nextLine(); // Очищення буфера
                if (input >= min && input <= max) {
                    return input;
                } else {
                    System.out.println("Введіть число від " + min + " до " + max + ".");
                }
            } catch (InputMismatchException e) {
                System.out.println("Невірний формат вводу. Введіть ціле число.");
                scanner.nextLine(); // Очищення буфера
            }
        }
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