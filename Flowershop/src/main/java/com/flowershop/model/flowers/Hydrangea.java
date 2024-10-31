package com.flowershop.model.flowers;

import com.flowershop.model.Flower;

public class Hydrangea extends Flower {

    private String inflorescenceType; // Тип суцвіття
    private String inflorescenceColor; // Колір суцвіття

    // Конструктор з параметрами
    public Hydrangea(String color, int freshnessLevel, double stemLength,
                     double flowerSize, double price, String originCountry,
                     String inflorescenceType, String inflorescenceColor) {

        super("Гортензія", color, freshnessLevel, stemLength, flowerSize, price, originCountry); // Виклик конструктора батьківського класу
        this.inflorescenceType = inflorescenceType;
        this.inflorescenceColor = inflorescenceColor;
    }

    public String getInflorescenceType() {
        return inflorescenceType;
    }

    public String getInflorescenceColor() {
        return inflorescenceColor;
    }

    public void setInflorescenceType(String inflorescenceType) {
        this.inflorescenceType = inflorescenceType;
    }

    public void setInflorescenceColor(String inflorescenceColor) {
        this.inflorescenceColor = inflorescenceColor;
    }

    // Перевизначений метод для отримання унікальної характеристики
    @Override
    public String getUniqueCharacteristic() {
        return "Тип суцвіття: " + inflorescenceType + ", колір суцвіття: " + inflorescenceColor;
    }
}