package com.flowershop.menu.commands;

import com.flowershop.manager.BouquetManager;
import com.flowershop.model.flowers.Hydrangea;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AddHydrangeaCommand implements Command {

    private BouquetManager bouquetManager;
    private Scanner scanner;

    // Конструктор з параметром
    public AddHydrangeaCommand(BouquetManager bouquetManager) {
        this.bouquetManager = bouquetManager;
        this.scanner = new Scanner(System.in);
    }

    // Перевизначений метод інтерфейсу Command
    @Override
    public void execute() {
        System.out.println("Введіть характеристики гортензії:");

        String color = getInput("Колір: ");
        int freshnessLevel = getIntInput("Рівень свіжості (1-5): ", 1, 5);
        double stemLength = getDoubleInput("Довжина стебла (см): ");
        double flowerSize = getDoubleInput("Розмір суцвіття (см): ");
        double price = getDoubleInput("Ціна (грн): ");
        String originCountry = getInput("Країна походження: ");
        String inflorescenceType = getInput("Тип суцвіття: ");
        String inflorescenceColor = getInput("Колір суцвіття: ");

        // Створення об'єкта гортензії та додавання його до букету
        Hydrangea hydrangea = new Hydrangea(color, freshnessLevel, stemLength, flowerSize, price, originCountry, inflorescenceType, inflorescenceColor);
        bouquetManager.addFlower(hydrangea);

        System.out.println("Гортензія додана до букету.");
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