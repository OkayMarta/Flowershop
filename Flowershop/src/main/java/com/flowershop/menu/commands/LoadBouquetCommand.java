package com.flowershop.menu.commands;

import com.flowershop.manager.BouquetManager;
import com.flowershop.model.Bouquet;
import com.flowershop.utils.BouquetFileReader;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoadBouquetCommand implements Command {

    private static final Logger logger = Logger.getLogger(LoadBouquetCommand.class.getName());
    private BouquetManager bouquetManager;
    private Scanner scanner;

    // Конструктор з параметрами
    public LoadBouquetCommand(BouquetManager bouquetManager) {
        this.bouquetManager = bouquetManager;
        this.scanner = new Scanner(System.in);
    }

    // Метод для завантаження букету з файлу
    public void loadFromFile(String filename) {
        try {
            logger.info("Завантаження букета з файлу: " + filename);
            Bouquet loadedBouquet = BouquetFileReader.loadBouquet(filename); // Виклик методу зчитування букету з файлу
            bouquetManager.setBouquet(loadedBouquet);
            System.out.println("Букет завантажено з файлу " + filename);
            logger.info("Букет успішно завантажено з файлу: " + filename);
        } catch (Exception e) {
            System.err.println("Помилка при завантаженні букету: " + e.getMessage());
            logger.log(Level.SEVERE, "Помилка при завантаженні букету з файлу: " + filename, e);
        }
    }

    // Перевизначений метод інтерфейсу Command
    @Override
    public void execute() {
        System.out.print("Введіть назву файлу (наприклад, file.txt): ");
        String filename = scanner.nextLine();

        // Перевірка наявності поточного букету
        if (!bouquetManager.getBouquet().getFlowers().isEmpty() ||
                !bouquetManager.getBouquet().getAccessories().isEmpty()) {

            System.out.print("Увага! Завантаження нового букету призведе до втрати інформації про поточний букет. Продовжити? (так/ні): ");
            String answer = scanner.nextLine();
            if (!answer.equalsIgnoreCase("так")) {
                System.out.println("Завантаження скасовано.");
                logger.info("Завантаження букета скасовано користувачем.");
                return;
            }
        }

        loadFromFile(filename); // Виклик методу завантаження
    }
}