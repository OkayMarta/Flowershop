package com.flowershop.menu.commands;

import com.flowershop.manager.BouquetManager;
import com.flowershop.model.Accessory;

import java.util.List;
import java.util.Scanner;

public class RemoveAccessoryCommand implements Command {

    private BouquetManager bouquetManager;
    private Scanner scanner;

    // Конструктор з параметром
    public RemoveAccessoryCommand(BouquetManager bouquetManager) {
        this.bouquetManager = bouquetManager;
        this.scanner = new Scanner(System.in);
    }

    // Перевизначений метод інтерфейсу Command
    @Override
    public void execute() {
        List<Accessory> accessories = bouquetManager.getBouquet().getAccessories();

        if (accessories.isEmpty()) {
            System.out.println("У букеті немає аксесуарів.");
            return;
        }

        System.out.println("Аксесуари в букеті:");
        for (int i = 0; i < accessories.size(); i++) {
            String className = accessories.get(i).getClass().getSimpleName();

            switch (className) {
                case "Wrapper":
                    className = "Обгортка";
                    break;
                case "Basket":
                    className = "Кошик";
                    break;
                case "Ribbon":
                    className = "Стрічка";
                    break;
                case "Postcard":
                    className = "Листівка";
                    break;
            }

            System.out.println((i + 1) + ". " + className +
                    ", " + accessories.get(i).getColor());
        }

        System.out.print("Введіть номер аксесуару, який потрібно видалити: ");
        int accessoryIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Очищення буфера

        if (accessoryIndex >= 0 && accessoryIndex < accessories.size()) {
            bouquetManager.getBouquet().removeAccessory(accessories.get(accessoryIndex));
            System.out.println("Аксесуар видалений з букету.");
        } else {
            System.out.println("Невірний номер аксесуару.");
        }
    }
}