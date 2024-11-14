package com.flowershop.utils;

import com.flowershop.model.Bouquet;
import com.flowershop.model.accessories.*;
import com.flowershop.model.flowers.*;
import com.flowershop.model.Flower;
import com.flowershop.model.Accessory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BouquetFileReader {

    private static final Logger logger = Logger.getLogger(BouquetFileReader.class.getName());

    // Метод для завантаження букету з файлу
    public static Bouquet loadBouquet(String filename) throws IOException {
        logger.info("Завантаження букету з файлу: " + filename);
        Bouquet bouquet = new Bouquet();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean readingFlowers = false;
            boolean readingAccessories = false;

            while ((line = reader.readLine()) != null) {
                if (line.equals("Квіти:")) {
                    readingFlowers = true;
                    readingAccessories = false;
                    logger.info("Початок зчитування квітів.");
                    continue;
                } else if (line.equals("Аксесуари:")) {
                    readingAccessories = true;
                    readingFlowers = false;
                    logger.info("Початок зчитування аксесуарів.");
                    continue;
                } else if (line.isEmpty()) {
                    continue;
                }

                String[] parts = line.split(",");
                try {
                    if (readingFlowers) {
                        bouquet.addFlower(createFlowerFromParts(parts));
                    } else if (readingAccessories) {
                        bouquet.addAccessory(createAccessoryFromParts(parts));
                    }
                } catch (IllegalArgumentException e) {
                    logger.log(Level.WARNING, "Помилка при створенні об'єкта з рядка: " + line, e);
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Помилка при зчитуванні файлу: " + filename, e);
            throw e;
        }

        logger.info("Букет успішно завантажено з файлу: " + filename);
        return bouquet;
    }

    // Метод для створення квітки з рядка
    private static Flower createFlowerFromParts(String[] parts) {
        String name = parts[0];
        logger.info("Створення квітки типу: " + name);

        switch (name) {
            case "Троянда":
                if (parts.length < 9) throw new IllegalArgumentException("Недостатньо даних для Троянди.");
                return new Rose(parts[1], Integer.parseInt(parts[2]), Double.parseDouble(parts[3]),
                        Double.parseDouble(parts[4]), Double.parseDouble(parts[5]), parts[6],
                        Boolean.parseBoolean(parts[7]), parts[8]);

            case "Лілія":
                if (parts.length < 9) throw new IllegalArgumentException("Недостатньо даних для Лілії.");
                return new Lily(parts[1], Integer.parseInt(parts[2]), Double.parseDouble(parts[3]),
                        Double.parseDouble(parts[4]), Double.parseDouble(parts[5]), parts[6],
                        Integer.parseInt(parts[7]), parts[8]);

            case "Орхідея":
                if (parts.length < 9) throw new IllegalArgumentException("Недостатньо даних для Орхідеї.");
                return new Orchid(parts[1], Integer.parseInt(parts[2]), Double.parseDouble(parts[3]),
                        Double.parseDouble(parts[4]), Double.parseDouble(parts[5]), parts[6],
                        parts[7], parts[8]);

            case "Гортензія":
                if (parts.length != 9) throw new IllegalArgumentException("Недостатньо даних для Гортензії.");
                return new Hydrangea(parts[1], Integer.parseInt(parts[2]), Double.parseDouble(parts[3]),
                        Double.parseDouble(parts[4]), Double.parseDouble(parts[5]), parts[6],
                        parts[7], parts[8]);

            default:
                throw new IllegalArgumentException("Невідомий тип квітки: " + name);
        }
    }

    // Метод для створення аксесуару з рядка
    private static Accessory createAccessoryFromParts(String[] parts) {
        String className = parts[0];
        logger.info("Створення аксесуара типу: " + className);
        String material = parts[1];
        String color = parts[2];
        double price = Double.parseDouble(parts[3]);

        switch (className) {
            case "Обгортка":
                return new Wrapper(material, color, price, parts[4]);
            case "Кошик":
                return new Basket(material, color, price, parts[4]);
            case "Стрічка":
                return new Ribbon(material, color, price, extractDoubleValue(parts[4], "ширина:"),
                        extractDoubleValue(parts[5], "довжина:"));
            case "Листівка":
                return new Postcard(material, color, price, parts[4]);
            default:
                throw new IllegalArgumentException("Невідомий тип аксесуару: " + className);
        }
    }

    // Метод для видобування значення double з рядка виду "назва: значення"
    private static double extractDoubleValue(String text, String label) {
        String value = text.substring(text.indexOf(label) + label.length()).trim();
        value = value.replaceAll("[^\\d.]", "");
        return Double.parseDouble(value);
    }
}