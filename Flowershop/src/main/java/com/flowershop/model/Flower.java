package com.flowershop.model;

public abstract class Flower {

    protected String name;
    protected String color;
    protected int freshnessLevel; // 1-5, 5 - найсвіжіша
    protected double stemLength; // Довжина стебла в см
    protected double flowerSize; // Розмір квітки в см
    protected double price; // Ціна в грн
    protected String originCountry;

    // Конструктор з параметрами
    public Flower(String name, String color, int freshnessLevel, double stemLength, double flowerSize, double price, String originCountry) {
        this.name = name;
        this.color = color;
        this.freshnessLevel = freshnessLevel;
        this.stemLength = stemLength;
        this.flowerSize = flowerSize;
        this.price = price;
        this.originCountry = originCountry;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getFreshnessLevel() {
        return freshnessLevel;
    }

    public double getStemLength() {
        return stemLength;
    }

    public double getFlowerSize() {
        return flowerSize;
    }

    public double getPrice() {
        return price;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Ціна квітки має бути додатнім числом.");
        }
        this.price = price;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setFreshnessLevel(int freshnessLevel) {
        if (freshnessLevel < 1 || freshnessLevel > 5) {
            throw new IllegalArgumentException("Рівень свіжості має бути в діапазоні від 1 до 5.");
        }
        this.freshnessLevel = freshnessLevel;
    }

    public void setStemLength(double stemLength) {
        if (stemLength <= 0) {
            throw new IllegalArgumentException("Довжина стебла має бути додатнім числом.");
        }
        this.stemLength = stemLength;
    }

    public void setFlowerSize(double flowerSize) {
        if (flowerSize <= 0) {
            throw new IllegalArgumentException("Розмір квітки має бути додатнім числом.");
        }
        this.flowerSize = flowerSize;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    // Абстрактний метод, який реалізований у дочірніх класах
    public abstract String getUniqueCharacteristic();
}