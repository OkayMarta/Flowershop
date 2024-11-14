package com.flowershop.model.accessories;

import com.flowershop.model.Accessory;

public class Ribbon extends Accessory {

    private double width; // Ширина стрічки в см
    private double length; // Довжина стрічки в см

    // Конструктор з параметрами
    public Ribbon(String material, String color, double price, double width, double length) {
        super(material, color, price); // Виклик конструктора батьківського класу
        this.width = width;
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        if (width < 0) {
            throw new IllegalArgumentException("Ширина стрічки не може бути від'ємною.");
        }
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        if (length < 0) {
            throw new IllegalArgumentException("Довжина стрічки не може бути від'ємною.");
        }
        this.length = length;
    }

    // Перевизначений метод для отримання унікальної характеристики
    @Override
    public String getUniqueCharacteristic() {
        return "ширина: " + width + " см, довжина: " + length + " см";
    }
}