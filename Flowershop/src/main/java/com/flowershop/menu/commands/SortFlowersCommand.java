package com.flowershop.menu.commands;

import com.flowershop.manager.BouquetManager;
import com.flowershop.model.Bouquet;
import java.util.logging.Logger;

public class SortFlowersCommand implements Command {

    private static final Logger logger = Logger.getLogger(SortFlowersCommand.class.getName());
    private BouquetManager bouquetManager;

    // Конструктор з параметром
    public SortFlowersCommand(BouquetManager bouquetManager) {
        this.bouquetManager = bouquetManager;
    }

    // Перевизначений метод інтерфейсу Command
    @Override
    public void execute() {
        Bouquet bouquet = bouquetManager.getBouquet();
        if (bouquet.getFlowers().isEmpty()) {
            System.out.println("Сортування неможливе, бо букет не створено.");
            logger.info("Спроба сортування квітів у порожньому букеті.");
            return;
        }

        bouquet.sortFlowersByFreshness();
        System.out.println("Квіти в букеті відсортовані за свіжістю.");
        logger.info("Квіти у букеті успішно відсортовані за свіжістю.");
    }
}