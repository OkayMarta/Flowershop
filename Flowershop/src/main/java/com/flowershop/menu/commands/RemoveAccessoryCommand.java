package com.flowershop.menu.commands;

import com.flowershop.manager.BouquetManager;
import com.flowershop.model.Accessory;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class RemoveAccessoryCommand implements Command {

    private static final Logger logger = Logger.getLogger(RemoveAccessoryCommand.class.getName());
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
            logger.info("Спроба видалення аксесуару з порожнього букета.");
            return;
        }

        System.out.println("Аксесуари в букеті:");
        for (int i = 0; i < accessories.size(); i++) {
            String className = accessories.get(i).getClass().getSimpleName();

            switch (className) {
                case "Wrapper" -> className = "Обгортка";
                case "Basket" -> className = "Кошик";
                case "Ribbon" -> className = "Стрічка";
                case "Postcard" -> className = "Листівка";
            }
            System.out.println((i + 1) + ". " + className + ", " + accessories.get(i).getColor());
        }

        System.out.print("Введіть номер аксесуару, який потрібно видалити: ");
        int accessoryIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Очищення буфера

        if (accessoryIndex >= 0 && accessoryIndex < accessories.size()) {
            Accessory removedAccessory = accessories.get(accessoryIndex);
            bouquetManager.getBouquet().removeAccessory(removedAccessory);
            System.out.println("Аксесуар видалений з букету.");
            logger.info("Аксесуар видалено з букету: " + removedAccessory.getClass().getSimpleName() +
                    ", колір: " + removedAccessory.getColor());
        } else {
            System.out.println("Невірний номер аксесуару.");
            logger.warning("Користувач ввів невірний номер аксесуару: " + (accessoryIndex + 1));
        }
    }
}