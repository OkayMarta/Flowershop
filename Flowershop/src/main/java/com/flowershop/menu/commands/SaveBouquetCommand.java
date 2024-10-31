package com.flowershop.menu.commands;

import com.flowershop.manager.BouquetManager;
import com.flowershop.utils.BouquetFileWriter;

import java.io.File;
import java.util.Scanner;

public class SaveBouquetCommand implements Command {

    private BouquetManager bouquetManager;
    private Scanner scanner;

    // Конструктор з параметром
    public SaveBouquetCommand(BouquetManager bouquetManager) {
        this.bouquetManager = bouquetManager;
        this.scanner = new Scanner(System.in);
    }

    // Перевизначений метод інтерфейсу Command
    @Override
    public void execute() {
        System.out.print("Введіть назву файлу (наприклад, file.txt): ");
        String filename = scanner.nextLine();

        File file = new File(filename); // Створення об'єкту файлу

        // Перевірка на існування файлу
        if (file.exists()) {
            System.out.print("Файл з такою назвою вже існує. Перезаписати? (так/ні): ");
            String answer = scanner.nextLine();
            if (!answer.equalsIgnoreCase("так")) {
                System.out.println("Збереження скасовано.");
                return;
            }
        }

        try {
            BouquetFileWriter.saveBouquet(bouquetManager.getBouquet(), filename);
            System.out.println("Букет збережено у файл " + filename);
        } catch (Exception e) {
            System.err.println("Помилка при збереженні букету: " + e.getMessage());
        }
    }
}