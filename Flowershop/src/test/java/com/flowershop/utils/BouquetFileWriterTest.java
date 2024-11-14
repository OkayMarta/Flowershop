package com.flowershop.utils;

import com.flowershop.model.accessories.*;
import com.flowershop.model.flowers.*;
import com.flowershop.model.Bouquet;
import com.flowershop.utils.BouquetFileWriter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class BouquetFileWriterTest {

    @Test
    void testSaveBouquet(@TempDir Path tempDir) throws IOException {
        // Створення тестового букету
        Bouquet bouquet = new Bouquet();

        bouquet.addFlower(new Rose("червоний", 5, 70.0, 6.0, 100.0, "Німеччина", true, "Фрідом"));
        bouquet.addFlower(new Lily("білий", 3, 25.0, 16.0, 80.0, "Польща", 2, "сильний"));
        bouquet.addFlower(new Orchid("білий", 1, 30.0, 7.0, 150.0, "Україна", "Каттлея", "червоний"));
        bouquet.addFlower(new Hydrangea("синій", 4, 80.0, 30.0, 500.0, "Австрія", "кругле", "голубий"));

        bouquet.addAccessory(new Wrapper("папір", "білий", 55.0, "розмір: 60 x 48"));
        bouquet.addAccessory(new Basket("дерево", "коричневий", 186.0, "розмір: 53 x 32"));
        bouquet.addAccessory(new Postcard("папір", "бежевий", 45.0, "текст привітання: З днем народженням!"));
        bouquet.addAccessory(new Ribbon("поліестр", "червоний", 10.0, 3.0, 33.0));

        // Збереження букету у файл
        Path tempFilePath = tempDir.resolve("test_bouquet.txt");
        Scanner scanner = new Scanner("ні\n"); // Simulate "ні" input
        String filename = "test_bouquet.txt";
        BouquetFileWriter.saveBouquet(bouquet, tempFilePath.toString());

        // Перевірка вмісту файлу
        List<String> lines = Files.readAllLines(tempFilePath);

        // Перевірка назви квітки
        assertTrue(lines.contains("Троянда,червоний,5,70.0,6.0,100.0,Німеччина,true,Фрідом"));
        assertTrue(lines.contains("Лілія,білий,3,25.0,16.0,80.0,Польща,2,сильний"));
        assertTrue(lines.contains("Орхідея,білий,1,30.0,7.0,150.0,Україна,Каттлея,червоний"));
        assertTrue(lines.contains("Гортензія,синій,4,80.0,30.0,500.0,Австрія,кругле,голубий"));

        // Перевірка назви аксесуару
        assertTrue(lines.contains("Обгортка,папір,білий,55.0,60 x 48"));
        assertTrue(lines.contains("Кошик,дерево,коричневий,186.0,53 x 32"));
        assertTrue(lines.contains("Листівка,папір,бежевий,45.0,З днем народженням!"));
        assertTrue(lines.contains("Стрічка,поліестр,червоний,10.0,3.0 33.0"));

        // Видалення тестового файлу
        Files.delete(tempFilePath);
    }
}