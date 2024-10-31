package com.flowershop.menu.commands;

import com.flowershop.manager.BouquetManager;

public class PrintBouquetInfoCommand implements Command {

    private BouquetManager bouquetManager;

    // Конструктор з параметром
    public PrintBouquetInfoCommand(BouquetManager bouquetManager) {
        this.bouquetManager = bouquetManager;
    }

    // Перевизначений метод інтерфейсу Command
    @Override
    public void execute() {
        String bouquetInfo = bouquetManager.getBouquet().getBouquetInfo();
        System.out.println(bouquetInfo);
    }
}