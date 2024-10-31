package com.flowershop.model.flowers;

import com.flowershop.model.Flower;

public class Orchid extends Flower {

    private String orchidType; // Тип орхідеї
    private String lipColor; // Колір губи

    // Конструктор з параметрами
    public Orchid(String color, int freshnessLevel, double stemLength,
                  double flowerSize, double price, String originCountry,
                  String orchidType, String lipColor) {

        super("Орхідея", color, freshnessLevel, stemLength, flowerSize, price, originCountry); // Виклик конструктора батьківського класу
        this.orchidType = orchidType;
        this.lipColor = lipColor;
    }

    public String getOrchidType() {
        return orchidType;
    }

    public String getLipColor() {
        return lipColor;
    }

    public void setOrchidType(String orchidType) {
        this.orchidType = orchidType;
    }

    public void setLipColor(String lipColor) {
        this.lipColor = lipColor;
    }

    // Перевизначений метод для отримання унікальної характеристики
    @Override
    public String getUniqueCharacteristic() {
        return "Тип орхідеї: " + orchidType + ", колір губи: " + lipColor;
    }
}