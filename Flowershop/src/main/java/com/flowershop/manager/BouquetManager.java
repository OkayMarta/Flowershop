package com.flowershop.manager;

import com.flowershop.model.Bouquet;
import com.flowershop.model.Accessory;
import com.flowershop.model.Flower;

    public class BouquetManager {
    private Bouquet bouquet;

    // Конструктор без параметрів
    public BouquetManager() {
        this.bouquet = new Bouquet();
    }

    public void addFlower(Flower flower) {
        bouquet.addFlower(flower);
    }

    public void addAccessory(Accessory accessory) {
        bouquet.addAccessory(accessory);
    }

    public Bouquet getBouquet() {
        return bouquet;
    }

    public void setBouquet(Bouquet bouquet) {
        this.bouquet = bouquet;
    }

    public void removeFlower(Flower flower) {
        bouquet.removeFlower(flower);
    }

    // Метод для перевірки наявності аксесуара заданого типу
    public boolean hasAccessoryOfType(Class<? extends Accessory> accessoryClass) {
        return bouquet.getAccessories().stream().anyMatch(a -> a.getClass() == accessoryClass);
    }

    // Метод для видалення аксесуара заданого типу
    public void removeAccessoryOfType(Class<? extends Accessory> accessoryClass) {
        bouquet.getAccessories().removeIf(a -> a.getClass() == accessoryClass);
    }
}