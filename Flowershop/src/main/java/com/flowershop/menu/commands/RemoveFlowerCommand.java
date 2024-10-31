package com.flowershop.menu.commands;

import com.flowershop.manager.BouquetManager;
import com.flowershop.model.Flower;

import java.util.List;
import java.util.Scanner;

public class RemoveFlowerCommand implements Command {

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
            bouquetManager.getBouquet().removeFlower(flowers.get(flowerIndex));
            System.out.println("Квітка видалена з букету.");
        } else {
            System.out.println("Невірний номер квітки.");
        }
    }
}