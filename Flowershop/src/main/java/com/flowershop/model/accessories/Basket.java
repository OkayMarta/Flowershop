package com.flowershop.model.accessories;

import com.flowershop.model.Accessory;

public class Basket extends Accessory {

    private String size;

    // Конструктор з параметрами
    public Basket(String material, String color, double price, String size) {
        super(material, color, price); // Виклик конструктора батьківського класу
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    // Перевизначений метод для отримання унікальної характеристики
    @Override
    public String getUniqueCharacteristic() {
        return size.substring(size.indexOf(":") + 1).trim(); // Повертаємо розмір кошика без пробілів
    }
}