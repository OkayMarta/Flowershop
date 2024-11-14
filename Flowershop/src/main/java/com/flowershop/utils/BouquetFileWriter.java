package com.flowershop.utils;

import com.flowershop.model.Bouquet;
import com.flowershop.model.Accessory;
import com.flowershop.model.Flower;
import com.flowershop.model.accessories.Ribbon;
import com.flowershop.model.flowers.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BouquetFileWriter {

    private static final Logger logger = Logger.getLogger(BouquetFileWriter.class.getName());

    // Метод для збереження букету у файл
    public static void saveBouquet(Bouquet bouquet, String filename) throws IOException {
        File file = new File(filename);

        // Використовуємо try-with-resources для автоматичного закриття ресурсів
        try (PrintWriter writer = new PrintWriter(filename)) {
            logger.info("Запущено збереження букету у файл: " + filename);

            // Запис квітів
            writer.println("Квіти:");
            for (Flower flower : bouquet.getFlowers()) {
                logger.fine("Записується квітка: " + flower.getName());
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
                    logger.fine("Додаткові характеристики троянди: Наявність шипів - " + rose.isHasThorns() + ", Сорт - " + rose.getRoseType());
                } else if (flower instanceof Lily) {
                    Lily lily = (Lily) flower;
                    writer.println(lily.getNumberOfFlowersOnStem() + "," + lily.getFragrance());
                    logger.fine("Додаткові характеристики лілії: Кількість квіток на стеблі - " + lily.getNumberOfFlowersOnStem() + ", Аромат - " + lily.getFragrance());
                } else if (flower instanceof Orchid) {
                    Orchid orchid = (Orchid) flower;
                    writer.println(orchid.getOrchidType() + "," + orchid.getLipColor());
                    logger.fine("Додаткові характеристики орхідеї: Тип орхідеї - " + orchid.getOrchidType() + ", Колір губи - " + orchid.getLipColor());
                } else if (flower instanceof Hydrangea) {
                    Hydrangea hydrangea = (Hydrangea) flower;
                    writer.println(hydrangea.getInflorescenceType() + "," + hydrangea.getInflorescenceColor());
                    logger.fine("Додаткові характеристики гортензії: Тип суцвіття - " + hydrangea.getInflorescenceType() + ", Колір суцвіття - " + hydrangea.getInflorescenceColor());
                }
            }

            // Запис аксесуарів
            writer.println("\nАксесуари:");
            for (Accessory accessory : bouquet.getAccessories()) {
                logger.fine("Записується аксесуар: " + accessory.getClass().getSimpleName());
                String className = accessory.getClass().getSimpleName();

                // Заміна англійських назв на українські
                className = switch (className) {
                    case "Wrapper" -> "Обгортка";
                    case "Basket" -> "Кошик";
                    case "Ribbon" -> "Стрічка";
                    case "Postcard" -> "Листівка";
                    default -> className;
                };

                if (className.equals("Стрічка")) {
                    Ribbon ribbon = (Ribbon) accessory;
                    writer.println(className + "," +
                            accessory.getMaterial() + "," +
                            accessory.getColor() + "," +
                            accessory.getPrice() + "," +
                            ribbon.getWidth() + " " + ribbon.getLength());
                    logger.fine("Додаткові характеристики стрічки: Ширина - " + ribbon.getWidth() + ", Довжина - " + ribbon.getLength());
                } else {
                    writer.println(className + "," +
                            accessory.getMaterial() + "," +
                            accessory.getColor() + "," +
                            accessory.getPrice() + "," +
                            accessory.getUniqueCharacteristic());
                    logger.fine("Додаткова характеристика аксесуару: " + accessory.getUniqueCharacteristic());
                }
            }

            logger.info("Букет успішно збережено у файл: " + filename);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Помилка при записі файлу: " + filename, e);
            throw e;
        }
    }
}