package com.flowershop.menu.commands;

import com.flowershop.manager.BouquetManager;
import com.flowershop.model.flowers.Lily;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AddLilyCommand implements Command {

    private BouquetManager bouquetManager;
    private Scanner scanner;

    // Конструктор з параметром
    public AddLilyCommand(BouquetManager bouquetManager) {
        this.bouquetManager = bouquetManager;
        this.scanner = new Scanner(System.in);
    }

    // Перевизначений метод інтерфейсу Command
    @Override
    public void execute() {
        System.out.println("Введіть характеристики лілії:");

        String color = getInput("Колір: ");
        int freshnessLevel = getIntInput("Рівень свіжості (1-5): ", 1, 5);
        double stemLength = getDoubleInput("Довжина стебла (см): ");
        double flowerSize = getDoubleInput("Розмір квітки (см): ");
        double price = getDoubleInput("Ціна (грн): ");
        String originCountry = getInput("Країна походження: ");
        int numberOfFlowers = getIntInput("Кількість квіток на стеблі: ", 1, Integer.MAX_VALUE); // Мінімум 1 квітка
        String fragrance = getInput("Аромат лілії: ");

        // Створення об'єкта лілії та додавання його до букету
        Lily lily = new Lily(color, freshnessLevel, stemLength, flowerSize, price, originCountry, numberOfFlowers, fragrance);
        bouquetManager.addFlower(lily);

        System.out.println("Лілія додана до букету.");
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