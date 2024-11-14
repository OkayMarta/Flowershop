package com.flowershop.menu.commands;

import com.flowershop.manager.BouquetManager;

import java.util.logging.Logger;

public class PrintBouquetInfoCommand implements Command {

    private static final Logger logger = Logger.getLogger(PrintBouquetInfoCommand.class.getName());
    private BouquetManager bouquetManager;

    // Конструктор з параметром
    public PrintBouquetInfoCommand(BouquetManager bouquetManager) {
        this.bouquetManager = bouquetManager;
    }

    // Перевизначений метод інтерфейсу Command
    @Override
    public void execute() {
        if (bouquetManager.getBouquet().isEmpty()) {
            System.out.println("Букету не створено.");
            logger.info("Спроба отримати інформацію про букет, який ще не створено.");
            return;
        }

        String bouquetInfo = bouquetManager.getBouquet().getBouquetInfo();
        System.out.println(bouquetInfo);
        logger.info("Інформацію про букет успішно отримано та відображено.");
    }
}