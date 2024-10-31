package com.flowershop.model.flowers;

import com.flowershop.model.Flower;

public class Lily extends Flower {

    private int numberOfFlowersOnStem; // Кількість квіток на стеблі
    private String fragrance; // Аромат

    // Конструктор з параметрами
    public Lily(String color, int freshnessLevel, double stemLength,
                double flowerSize, double price, String originCountry,
                int numberOfFlowersOnStem, String fragrance) {

        super("Лілія", color, freshnessLevel, stemLength, flowerSize, price, originCountry); // Виклик конструктора батьківського класу
        this.numberOfFlowersOnStem = numberOfFlowersOnStem;
        this.fragrance = fragrance;
    }

    public int getNumberOfFlowersOnStem() {
        return numberOfFlowersOnStem;
    }

    public String getFragrance() {
        return fragrance;
    }

    public void setNumberOfFlowersOnStem(int numberOfFlowersOnStem) {
        if (numberOfFlowersOnStem < 0) {
            throw new IllegalArgumentException("Кількість квіток на стеблі не може бути від'ємною.");
        }
        this.numberOfFlowersOnStem = numberOfFlowersOnStem;
    }

    public void setFragrance(String fragrance) {
        this.fragrance = fragrance;
    }

    // Перевизначений метод для отримання унікальної характеристики
    @Override
    public String getUniqueCharacteristic() {
        return "Кількість квіток на стеблі: " + numberOfFlowersOnStem + ", аромат: " + fragrance;
    }
}