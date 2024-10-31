package com.flowershop.utils;

import com.flowershop.model.Bouquet;
import com.flowershop.model.accessories.*;
import com.flowershop.model.flowers.*;
import com.flowershop.model.Flower;
import com.flowershop.model.Accessory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BouquetFileReader {

    // Метод для завантаження букету з файлу
    public static Bouquet loadBouquet(String filename) throws IOException {
        Bouquet bouquet = new Bouquet(); // Створюємо новий букет

        // Використовуємо try-with-resources для автоматичного закриття ресурсів
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean readingFlowers = false; // Прапорець для визначення, чи йде зчитування квітів
            boolean readingAccessories = false; // Прапорець для визначення, чи йде зчитування аксесуарів

            while ((line = reader.readLine()) != null) {
                if (line.equals("Квіти:")) {
                    readingFlowers = true;
                    readingAccessories = false;
                    continue;
                } else if (line.equals("Аксесуари:")) {
                    readingAccessories = true;
                    readingFlowers = false;
                    continue;
                } else if (line.isEmpty()) {
                    continue;
                }

                String[] parts = line.split(",");
                if (readingFlowers) {
                    bouquet.addFlower(createFlowerFromParts(parts));
                } else if (readingAccessories) {
                    bouquet.addAccessory(createAccessoryFromParts(parts));
                }
            }
        }

        return bouquet;
    }

    // Метод для створення квітки з рядка
    private static Flower createFlowerFromParts(String[] parts) {
        String name = parts[0];

        switch (name) {
            case "Троянда":
                if (parts.length < 9) {
                    throw new IllegalArgumentException("Недостатньо даних для Троянди.");
                }
                String color = parts[1];
                int freshnessLevel = Integer.parseInt(parts[2]);
                double stemLength = Double.parseDouble(parts[3]);
                double flowerSize = Double.parseDouble(parts[4]);
                double price = Double.parseDouble(parts[5]);
                String originCountry = parts[6];
                boolean hasThorns = Boolean.parseBoolean(parts[7]);
                String roseType = parts[8];
                return new Rose(color, freshnessLevel, stemLength, flowerSize, price, originCountry, hasThorns, roseType);

            case "Лілія":
                if (parts.length < 9) {
                    throw new IllegalArgumentException("Недостатньо даних для Лілії.");
                }
                color = parts[1];
                freshnessLevel = Integer.parseInt(parts[2]);
                stemLength = Double.parseDouble(parts[3]);
                flowerSize = Double.parseDouble(parts[4]);
                price = Double.parseDouble(parts[5]);
                originCountry = parts[6];
                int numberOfFlowersOnStem = Integer.parseInt(parts[7]);
                String fragrance = parts[8];
                return new Lily(color, freshnessLevel, stemLength, flowerSize, price, originCountry, numberOfFlowersOnStem, fragrance);

            case "Орхідея":
                if (parts.length < 9) {
                    throw new IllegalArgumentException("Недостатньо даних для Орхідеї.");
                }
                color = parts[1];
                freshnessLevel = Integer.parseInt(parts[2]);
                stemLength = Double.parseDouble(parts[3]);
                flowerSize = Double.parseDouble(parts[4]);
                price = Double.parseDouble(parts[5]);
                originCountry = parts[6];
                String orchidType = parts[7];
                String lipColor = parts[8];
                return new Orchid(color, freshnessLevel, stemLength, flowerSize, price, originCountry, orchidType, lipColor);

            case "Гортензія":
                if (parts.length != 9) {
                    throw new IllegalArgumentException("Недостатньо даних для Гортензії.");
                }
                color = parts[1];
                freshnessLevel = Integer.parseInt(parts[2]);
                stemLength = Double.parseDouble(parts[3]);
                flowerSize = Double.parseDouble(parts[4]);
                price = Double.parseDouble(parts[5]);
                originCountry = parts[6];
                String inflorescenceType = parts[7];
                String inflorescenceColor = parts[8];
                return new Hydrangea(color, freshnessLevel, stemLength, flowerSize, price, originCountry, inflorescenceType, inflorescenceColor);

            default:
                throw new IllegalArgumentException("Невідомий тип квітки: " + name);
        }
    }

    // Метод для створення аксесуару з рядка
    private static Accessory createAccessoryFromParts(String[] parts) {
        String className = parts[0];
        String material = parts[1];
        String color = parts[2];
        double price = Double.parseDouble(parts[3]);

        switch (className) {
            case "Обгортка":
                return new Wrapper(material, color, price, parts[4]);
            case "Кошик":
                return new Basket(material, color, price, parts[4]);
            case "Стрічка":
                double width = extractDoubleValue(parts[4], "ширина:");
                double length = extractDoubleValue(parts[5], "довжина:");
                return new Ribbon(material, color, price, width, length);
            case "Листівка":
                return new Postcard(material, color, price, parts[4]);
            default:
                throw new IllegalArgumentException("Невідомий тип аксесуару: " + className);
        }
    }

    // Метод для видобування значення double з рядка виду "назва: значення"
    private static double extractDoubleValue(String text, String label) {
        String value = text.substring(text.indexOf(label) + label.length()).trim();
        value = value.replaceAll("[^\\d.]", ""); // Видаляємо всі символи, крім цифр та крапки
        return Double.parseDouble(value);
    }
}