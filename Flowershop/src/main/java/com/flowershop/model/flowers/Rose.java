package com.flowershop.model.flowers;

import com.flowershop.model.Flower;

public class Rose extends Flower {

    private boolean hasThorns; // Наявність шипів
    private String roseType; // Сорт троянди

    // Конструктор з параметрами
    public Rose(String color, int freshnessLevel, double stemLength,
                double flowerSize, double price, String originCountry,
                boolean hasThorns, String roseType) {

        super("Троянда", color, freshnessLevel, stemLength, flowerSize, price, originCountry); // Виклик конструктора батьківського класу
        this.hasThorns = hasThorns;
        this.roseType = roseType;
    }

    public boolean isHasThorns() {
        return hasThorns;
    }

    public String getRoseType() {
        return roseType;
    }

    public void setHasThorns(boolean hasThorns) {
        this.hasThorns = hasThorns;
    }

    public void setRoseType(String roseType) {
        this.roseType = roseType;
    }

    public void setFreshnessLevel(int freshnessLevel) {
        if (freshnessLevel < 1 || freshnessLevel > 5) {
            throw new IllegalArgumentException("Рівень свіжості повинен бути в діапазоні від 1 до 5.");
        }
        this.freshnessLevel = freshnessLevel;
    }

    // Перевизначений метод для отримання унікальної характеристики
    @Override
    public String getUniqueCharacteristic() {
        return "Наявність шипів: " + hasThorns + ", сорт: " + roseType;
    }
}