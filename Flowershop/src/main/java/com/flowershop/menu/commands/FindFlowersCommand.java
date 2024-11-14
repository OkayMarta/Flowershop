package com.flowershop.menu.commands;

import com.flowershop.manager.BouquetManager;
import com.flowershop.model.Flower;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FindFlowersCommand implements Command {

    private static final Logger logger = Logger.getLogger(FindFlowersCommand.class.getName());
    private BouquetManager bouquetManager;
    private Scanner scanner;

    // Конструктор з параметром
    public FindFlowersCommand(BouquetManager bouquetManager) {
        this.bouquetManager = bouquetManager;
    }

    // Сеттер для scanner
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    // Перевизначений метод інтерфейсу Command
    @Override
    public void execute() {
        if (bouquetManager.getBouquet().getFlowers().isEmpty()) {
            System.out.println("Пошук неможливий, бо букет не створено.");
            logger.warning("Спроба пошуку квітів без наявного букета.");
            return;
        }

        double minLength = 0;
        double maxLength = 0;

        while (true) {
            try {
                System.out.print("Введіть мінімальну довжину стебла (см): ");
                minLength = scanner.nextDouble();
                System.out.print("Введіть максимальну довжину стебла (см): ");
                maxLength = scanner.nextDouble();
                scanner.nextLine(); // Очищення буфера

                logger.info("Користувач ввів діапазон довжини стебла: від " + minLength + " до " + maxLength);
                break;
            } catch (InputMismatchException e) {
                System.out.println("Невірний формат вводу. Будь ласка, введіть число.");
                logger.log(Level.WARNING, "Помилка введення: некоректний формат числа", e);
                scanner.nextLine(); // Очищення буфера
            }
        }

        List<Flower> foundFlowers = bouquetManager.getBouquet().findFlowersByStemLength(minLength, maxLength);

        if (foundFlowers.isEmpty()) {
            System.out.println("Не знайдено квітів з довжиною стебла в заданому діапазоні.");
            logger.info("Не знайдено квітів з довжиною стебла в діапазоні: від " + minLength + " до " + maxLength);
            return;
        }

        System.out.println("Знайдені квіти:");
        for (Flower flower : foundFlowers) {
            System.out.println("- " + flower.getName() + ", " + flower.getColor() +
                    ", довжина стебла: " + flower.getStemLength() + " см");
        }
        logger.info("Знайдено квітів за заданим діапазоном: " + foundFlowers.size());
    }
}
