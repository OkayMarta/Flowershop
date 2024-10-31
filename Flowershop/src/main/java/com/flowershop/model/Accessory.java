package com.flowershop.model;

public abstract class Accessory {

    protected String material;
    protected String color;
    protected double price;

    // Конструктор з параметрами
    public Accessory(String material, String color, double price) {
        this.material = material;
        this.color = color;
        this.price = price;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Ціна не може бути від'ємною.");
        }
        this.price = price;
    }

    public abstract String getUniqueCharacteristic();
}