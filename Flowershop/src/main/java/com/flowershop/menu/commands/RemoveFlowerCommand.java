package com.flowershop.menu.commands;

import com.flowershop.manager.BouquetManager;
import com.flowershop.model.Flower;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class RemoveFlowerCommand implements Command {

    private static final Logger logger = Logger.getLogger(RemoveFlowerCommand.class.getName());
    private BouquetManager bouquetManager;
    private Scanner scanner;

    // Конструктор з параметром
    public RemoveFlowerCommand(BouquetManager bouquetManager) {
        this.bouquetManager = bouquetManager;
        this.scanner = new Scanner(System.in);
    }

    // Перевизначений метод інтерфейсу Command
    @Override
    public void execute() {
        List<Flower> flowers = bouquetManager.getBouquet().getFlowers();

        if (flowers.isEmpty()) {
            System.out.println("У букеті немає квітів.");
            logger.info("Спроба видалення квітки з порожнього букета.");
            return;
        }

        System.out.println("Квіти в букеті:");
        for (int i = 0; i < flowers.size(); i++) {
            System.out.println((i + 1) + ". " + flowers.get(i).getName() + ", " + flowers.get(i).getColor());
        }

        System.out.print("Введіть номер квітки, яку потрібно видалити: ");
        int flowerIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Очищення буфера

        if (flowerIndex >= 0 && flowerIndex < flowers.size()) {
            Flower removedFlower = flowers.get(flowerIndex);
            bouquetManager.getBouquet().removeFlower(removedFlower);
            System.out.println("Квітка видалена з букету.");
            logger.info("Квітка видалена з букету: " + removedFlower.getName() + ", колір: " + removedFlower.getColor());
        } else {
            System.out.println("Невірний номер квітки.");
            logger.warning("Користувач ввів неправильний номер квітки: " + (flowerIndex + 1));
        }
    }
}