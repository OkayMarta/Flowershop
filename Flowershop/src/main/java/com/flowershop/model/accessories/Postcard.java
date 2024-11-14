package com.flowershop.model.accessories;

import com.flowershop.model.Accessory;

public class Postcard extends Accessory {

    private String greetingText; // Текст привітання на листівці

    // Конструктор з параметрами
    public Postcard(String material, String color, double price, String greetingText) {
        super(material, color, price); // Виклик конструктора батьківського класу
        this.greetingText = greetingText;
    }

    public String getGreetingText() {
        return greetingText;
    }

    public void setGreetingText(String greetingText) {
        this.greetingText = greetingText;
    }

    // Перевизначений метод для отримання унікальної характеристики
    @Override
    public String getUniqueCharacteristic() {
        return greetingText.substring(greetingText.indexOf(":") + 1).trim();
    }
}