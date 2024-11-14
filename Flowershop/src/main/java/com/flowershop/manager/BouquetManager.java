package com.flowershop.manager;

import com.flowershop.model.Bouquet;
import com.flowershop.model.Accessory;
import com.flowershop.model.Flower;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BouquetManager {
    private static final Logger logger = Logger.getLogger(BouquetManager.class.getName());
    private Bouquet bouquet;

    // Конструктор без параметрів
    public BouquetManager() {
        this.bouquet = new Bouquet();
        logger.info("Ініціалізація нового букета");
    }

    // Конструктор з параметром
    public BouquetManager(Bouquet bouquet) {
        this.bouquet = bouquet;
        logger.info("BouquetManager ініціалізовано наявним букетом");
    }

    // Метод для додавання квітки до букету
    public void addFlower(Flower flower) {
        if (flower == null) {
            logger.warning("Спроба додати null квітку до букету");
            return;
        }
        bouquet.addFlower(flower);
        logger.info("Додано квітку в букет: " + flower.getName());
    }

    // Метод для додавання аксесуару до букету
    public void addAccessory(Accessory accessory) {
        if (accessory == null) {
            logger.warning("Спроба додати null аксесуар");
            return;
        }
        if (bouquet.hasAccessoryOfType(accessory.getClass())) {
            logger.info("Аксесуар типу " + accessory.getClass().getSimpleName() + " вже існує в букеті");
        }
        bouquet.addAccessory(accessory);
        logger.info("До букету додано аксесуар: " + accessory.getClass().getSimpleName());
    }

    // Отримати букет
    public Bouquet getBouquet() {
        return bouquet;
    }

    // Встановити букет
    public void setBouquet(Bouquet bouquet) {
        if (bouquet == null) {
            logger.warning("Спроба встановити null букет");
            return;
        }
        this.bouquet = bouquet;
        logger.info("Букет оновлено");
    }

    // Метод для видалення квітки з букету
    public void removeFlower(Flower flower) {
        if (flower == null || !bouquet.getFlowers().contains(flower)) {
            logger.warning("Спроба видалити квітку, якої немає в букеті");
            return;
        }
        bouquet.removeFlower(flower);
        logger.info("Вилучено квітку з букета: " + flower.getName());
    }

    // Метод для перевірки наявності аксесуара заданого типу
    public boolean hasAccessoryOfType(Class<? extends Accessory> accessoryClass) {
        return bouquet.getAccessories().stream().anyMatch(a -> a.getClass() == accessoryClass); // лямбда-вираз: перевіряємо чи є хоча б один елемент, який задовільняє умову
    }

    // Метод для видалення аксесуара заданого типу
    public void removeAccessoryOfType(Class<? extends Accessory> accessoryClass) {
        if (accessoryClass == null || !bouquet.hasAccessoryOfType(accessoryClass)) {
            logger.warning("Спроба видалити аксесуар типу " + accessoryClass + " що не існує");
            return;
        }
        bouquet.removeAccessoryOfType(accessoryClass);
        logger.info("Видилений аксесуар типу " + accessoryClass.getSimpleName() + " з букету");
    }
}