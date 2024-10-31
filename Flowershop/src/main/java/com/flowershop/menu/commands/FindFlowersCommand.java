package com.flowershop.menu.commands;

import com.flowershop.manager.BouquetManager;
import com.flowershop.model.Flower;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class FindFlowersCommand implements Command {

    private BouquetManager bouquetManager;
    private Scanner scanner;

    // Конструктор з параметром
    public FindFlowersCommand(BouquetManager bouquetManager) {
        this.bouquetManager = bouquetManager;
        this.scanner = new Scanner(System.in);
    }

    // Перевизначений метод інтерфейсу Command
    @Override
    public void execute() {
        if (bouquetManager.getBouquet().getFlowers().isEmpty()) {
            System.out.println("Пошук неможливий, бо букет не створено.");
            return;
        }

        double minLength = 0;
        double maxLength = 0;

        // Цикл, який продовжується доки користувач не введе числові значення
        while (true) {
            try {
                System.out.print("Введіть мінімальну довжину стебла (см): ");
                minLength = scanner.nextDouble();

                System.out.print("Введіть максимальну довжину стебла (см): ");
                maxLength = scanner.nextDouble();
                scanner.nextLine(); // Очищення буфера

                // Якщо ввод дійсний, виходимо з циклу
                break;
            } catch (InputMismatchException e) {
                System.out.println("Невірний формат вводу. Будь ласка, введіть число.");
                scanner.nextLine(); // Очищення буфера
            }
        }

        List<Flower> foundFlowers = bouquetManager.getBouquet().findFlowersByStemLength(minLength, maxLength);

        if (foundFlowers.isEmpty()) {
            System.out.println("Не знайдено квітів з довжиною стебла в заданому діапазоні.");
            return;
        }

        System.out.println("Знайдені квіти:");
        for (Flower flower : foundFlowers) {
            System.out.println("- " + flower.getName() + ", " + flower.getColor() +
                    ", довжина стебла: " + flower.getStemLength() + " см");
        }
    }
}