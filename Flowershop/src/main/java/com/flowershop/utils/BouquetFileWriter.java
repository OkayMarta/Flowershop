package com.flowershop.utils;

import com.flowershop.model.Bouquet;
import com.flowershop.model.Accessory;
import com.flowershop.model.Flower;
import com.flowershop.model.flowers.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class BouquetFileWriter {

    // Метод для збереження букету у файл
    public static void saveBouquet(Bouquet bouquet, String filename) throws IOException {
        File file = new File(filename);

        // Перевіряємо, чи файл існує
        if (file.exists()) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Файл з такою назвою вже існує. Перезаписати? (так/ні): ");
            String answer = scanner.nextLine();
            if (!answer.equalsIgnoreCase("так")) {
                System.out.println("Збереження скасовано.");
                return;
            }
        }

        // Використовуємо try-with-resources для автоматичного закриття ресурсів
        try (PrintWriter writer = new PrintWriter(filename)) {
            // Запис квітів
            writer.println("Квіти:");
            for (Flower flower : bouquet.getFlowers()) {
                writer.print(flower.getName() + "," +
                        flower.getColor() + "," +
                        flower.getFreshnessLevel() + "," +
                        flower.getStemLength() + "," +
                        flower.getFlowerSize() + "," +
                        flower.getPrice() + "," +
                        flower.getOriginCountry() + ",");

                // Перевіряємо тип квітки та записуємо унікальні характеристики через кому
                if (flower instanceof Rose) {
                    Rose rose = (Rose) flower;
                    writer.println(rose.isHasThorns() + "," + rose.getRoseType());
                } else if (flower instanceof Lily) {
                    Lily lily = (Lily) flower;
                    writer.println(lily.getNumberOfFlowersOnStem() + "," + lily.getFragrance());
                } else if (flower instanceof Orchid) {
                    Orchid orchid = (Orchid) flower;
                    writer.println(orchid.getOrchidType() + "," + orchid.getLipColor());
                } else if (flower instanceof Hydrangea) {
                    Hydrangea hydrangea = (Hydrangea) flower;
                    writer.println(hydrangea.getInflorescenceType() + "," + hydrangea.getInflorescenceColor());
                }
            }

            // Запис аксесуарів
            writer.println("\nАксесуари:");
            for (Accessory accessory : bouquet.getAccessories()) {
                String className = accessory.getClass().getSimpleName();

                // Заміна англійських назв на українські
                className = switch (className) {
                    case "Wrapper" -> "Обгортка";
                    case "Basket" -> "Кошик";
                    case "Ribbon" -> "Стрічка";
                    case "Postcard" -> "Листівка";
                    default -> className;
                };

                writer.println(className + "," +
                        accessory.getMaterial() + "," +
                        accessory.getColor() + "," +
                        accessory.getPrice() + "," +
                        accessory.getUniqueCharacteristic());
            }
        }
    }
}