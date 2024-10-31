package com.flowershop.menu.commands;

import com.flowershop.manager.BouquetManager;
import com.flowershop.model.Bouquet;
import com.flowershop.utils.BouquetFileReader;

import java.util.Scanner;

public class LoadBouquetCommand implements Command {

    private BouquetManager bouquetManager;
    private Scanner scanner;

    // Конструктор з параметром
    public LoadBouquetCommand(BouquetManager bouquetManager) {
        this.bouquetManager = bouquetManager;
        this.scanner = new Scanner(System.in);
    }

    // Перевизначений метод інтерфейсу Command
    @Override
    public void execute() {
        // Перевіряємо, чи є квіти або аксесуари в поточному букеті
        if (!bouquetManager.getBouquet().getFlowers().isEmpty() ||
                !bouquetManager.getBouquet().getAccessories().isEmpty()) {

            System.out.print("Увага! Завантаження нового букету призведе до втрати інформації про поточний букет. Продовжити? (так/ні): ");
            String answer = scanner.nextLine();
            if (!answer.equalsIgnoreCase("так")) {
                System.out.println("Завантаження скасовано.");
                return;
            }
        }

        System.out.print("Введіть назву файлу (наприклад, file.txt): ");
        String filename = scanner.nextLine();

        try {
            Bouquet loadedBouquet = BouquetFileReader.loadBouquet(filename);
            bouquetManager.setBouquet(loadedBouquet);
            System.out.println("Букет завантажено з файлу " + filename);
        } catch (Exception e) {
            System.err.println("Помилка при завантаженні букету: " + e.getMessage());
        }
    }
}