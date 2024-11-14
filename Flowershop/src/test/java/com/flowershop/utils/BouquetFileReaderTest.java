package com.flowershop.utils;

import com.flowershop.model.*;
import com.flowershop.model.accessories.*;
import com.flowershop.model.flowers.*;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.flowershop.utils.BouquetFileReader;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BouquetFileReaderTest {

    @Test
    void testLoadBouquet() throws IOException {
        String filename = "bouquet.txt"; // Назва файлу з букетом

        Bouquet expectedBouquet = new Bouquet();
        expectedBouquet.addFlower(new Rose("червоний", 1, 70.0, 6.0, 100.0, "Німеччина", true, "Фрідом"));
        expectedBouquet.addFlower(new Lily("білий", 3, 25.0, 16.0, 80.0, "Польща", 2, "сильний"));
        expectedBouquet.addFlower(new Orchid("білий", 1, 30.0, 7.0, 150.0, "Україна", "Каттлея", "червоний"));
        expectedBouquet.addFlower(new Hydrangea("синій", 4, 80.0, 30.0, 500.0, "Австрія", "кругле", "голубий"));
        expectedBouquet.addAccessory(new Wrapper("папір", "білий", 55.0, "60 x 48"));
        expectedBouquet.addAccessory(new Basket("дерево", "коричневий", 186.0, "53 x 32"));
        expectedBouquet.addAccessory(new Postcard("папір", "бежевий", 45.0, "З днем народженням!"));
        expectedBouquet.addAccessory(new Ribbon("поліестр", "червоний", 10.0, 3.0, 33.0));

        // Зчитуємо букет з файлу
        Bouquet actualBouquet = BouquetFileReader.loadBouquet(filename);

        // Перевіряємо кількість квітів та аксесуарів
        assertEquals(expectedBouquet.getFlowers().size(), actualBouquet.getFlowers().size());
        assertEquals(expectedBouquet.getAccessories().size(), actualBouquet.getAccessories().size());

        // Порівняємо квіти
        List<Flower> expectedFlowers = expectedBouquet.getFlowers();
        List<Flower> actualFlowers = actualBouquet.getFlowers();
        for (int i = 0; i < expectedFlowers.size(); i++) {
            Flower expectedFlower = expectedFlowers.get(i);
            Flower actualFlower = actualFlowers.get(i);

            assertEquals(expectedFlower.getName(), actualFlower.getName());
            assertEquals(expectedFlower.getColor(), actualFlower.getColor());
            assertEquals(expectedFlower.getFreshnessLevel(), actualFlower.getFreshnessLevel());
            assertEquals(expectedFlower.getStemLength(), actualFlower.getStemLength(), 0.001);
            assertEquals(expectedFlower.getFlowerSize(), actualFlower.getFlowerSize(), 0.001);
            assertEquals(expectedFlower.getPrice(), actualFlower.getPrice(), 0.001);
            assertEquals(expectedFlower.getOriginCountry(), actualFlower.getOriginCountry());
            assertEquals(expectedFlower.getUniqueCharacteristic(), actualFlower.getUniqueCharacteristic());
        }

        // Порівняємо аксесуари
        List<Accessory> expectedAccessories = expectedBouquet.getAccessories();
        List<Accessory> actualAccessories = actualBouquet.getAccessories();
        for (int i = 0; i < expectedAccessories.size(); i++) {
            Accessory expectedAccessory = expectedAccessories.get(i);
            Accessory actualAccessory = actualAccessories.get(i);

            assertEquals(expectedAccessory.getMaterial(), actualAccessory.getMaterial());
            assertEquals(expectedAccessory.getColor(), actualAccessory.getColor());
            assertEquals(expectedAccessory.getPrice(), actualAccessory.getPrice(), 0.001);
            assertEquals(expectedAccessory.getUniqueCharacteristic(), actualAccessory.getUniqueCharacteristic());
        }

        // Порівняємо дату створення
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        assertEquals(expectedBouquet.getCreationDate().format(formatter), actualBouquet.getCreationDate().format(formatter));
    }

    @Test
    void testLoadBouquetFileNotFound() {
        String filename = "nonexistent_file.txt"; // Неіснуючий файл
        assertThrows(IOException.class, () -> BouquetFileReader.loadBouquet(filename));
    }

    @Test
    void testLoadBouquetInvalidData() throws IOException {
        String filename = "invalid_bouquet.txt"; // Файл з некоректними даними
        assertThrows(IllegalArgumentException.class, () -> BouquetFileReader.loadBouquet(filename));
    }
}