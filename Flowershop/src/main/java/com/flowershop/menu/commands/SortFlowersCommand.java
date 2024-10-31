package com.flowershop.menu.commands;

import com.flowershop.manager.BouquetManager;

public class SortFlowersCommand implements Command {

    private BouquetManager bouquetManager;

    // Конструктор з параметром
    public SortFlowersCommand(BouquetManager bouquetManager) {
        this.bouquetManager = bouquetManager;
    }

    // Перевизначений метод інтерфейсу Command
    @Override
    public void execute() {
        if (bouquetManager.getBouquet().getFlowers().isEmpty()) {
            System.out.println("Сортування неможливе, бо букет не створено.");
            return;
        }

        bouquetManager.getBouquet().sortFlowersByFreshness(); // Сортуємо квіти в букеті
        System.out.println("Квіти в букеті відсортовані за свіжістю.");
    }
}