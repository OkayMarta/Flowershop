package com.flowershop.menu.commands;

import com.flowershop.manager.BouquetManager;
import com.flowershop.model.Bouquet;
import com.flowershop.utils.BouquetFileWriter;

import java.io.File;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SaveBouquetCommand implements Command {

    private static final Logger logger = Logger.getLogger(SaveBouquetCommand.class.getName());
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
        Bouquet bouquet = bouquetManager.getBouquet();
        if (bouquet == null || bouquet.isEmpty()) {
            System.err.println("Букет порожній. Неможливо зберегти.");
            logger.warning("Спроба зберегти порожній букет.");
            return;
        }

        try {
            System.out.print("Введіть назву файлу (наприклад, file.txt): ");
            String filename = scanner.nextLine();

            File file = new File(filename);
            if (file.exists()) {
                System.out.print("Файл з такою назвою вже існує. Перезаписати? (так/ні): ");
                String answer = scanner.nextLine();
                if (!answer.equalsIgnoreCase("так")) {
                    System.out.println("Збереження скасовано.");
                    logger.info("Збереження букета скасовано користувачем.");
                    return;
                }
            }

            logger.info("Збереження букета у файл: " + filename);
            BouquetFileWriter.saveBouquet(bouquet, filename);
            System.out.println("Букет збережено у файл " + filename);
            logger.info("Букет успішно збережено у файл: " + filename);
        } catch (Exception e) {
            System.err.println("Помилка при збереженні букету: " + e.getMessage());
            logger.log(Level.SEVERE, "Помилка при збереженні букета у файл.", e);
        }
    }
}