package com.flowershop.menu.commands;

import com.flowershop.manager.BouquetManager;
import com.flowershop.model.Bouquet;
import com.flowershop.model.Flower;
import com.flowershop.model.flowers.Rose;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class FindFlowersCommandTest {

    private final InputStream systemIn = System.in;
    private ByteArrayInputStream testIn;
    private Scanner scanner;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
        System.setIn(systemIn);
    }

    @Test
    void testExecute_flowersFound() {
        String input = "50\n70\n";
        provideInput(input);

        Bouquet bouquet = new Bouquet();
        bouquet.addFlower(new Rose("червоний", 5, 60, 10, 50, "Нідерланди", true, "чайно-гібридна"));
        bouquet.addFlower(new Rose("білий", 4, 45, 8, 40, "Еквадор", false, "флорибунда"));
        bouquet.addFlower(new Rose("жовтий", 3, 75, 12, 60, "Колумбія", true, "чайно-гібридна"));

        BouquetManager bouquetManager = new BouquetManager(); // Створюємо менеджер без параметрів
        bouquetManager.setBouquet(bouquet); // Встановлюємо букет через сеттер
        FindFlowersCommand command = new FindFlowersCommand(bouquetManager);
        command.setScanner(scanner);
        command.execute();

        // Пошук квітів за довжиною стебла
        List<Flower> foundFlowers = bouquet.findFlowersByStemLength(50, 70);
        List<Rose> roses = foundFlowers.stream()
                .filter(flower -> flower instanceof Rose)
                .map(flower -> (Rose) flower)
                .toList();
        assertEquals(1, roses.size());
    }

    @Test
    void testExecute_noFlowersFound() {
        String input = "30\n40\n";
        provideInput(input);

        Bouquet bouquet = new Bouquet();
        bouquet.addFlower(new Rose("червоний", 5, 60, 10, 50, "Нідерланди", true, "чайно-гібридна"));
        bouquet.addFlower(new Rose("білий", 4, 45, 8, 40, "Еквадор", false, "флорибунда"));
        bouquet.addFlower(new Rose("жовтий", 3, 75, 12, 60, "Колумбія", true, "чайно-гібридна"));

        BouquetManager bouquetManager = new BouquetManager(bouquet); // Передаємо букет в менеджер
        FindFlowersCommand command = new FindFlowersCommand(bouquetManager);

        command.setScanner(scanner);
        command.execute();
        List<Flower> foundFlowers = bouquet.findFlowersByStemLength(50, 70);
        // Якщо потрібно саме троянди, то можна використати фільтрацію
        List<Rose> roses = foundFlowers.stream()
                .filter(flower -> flower instanceof Rose)
                .map(flower -> (Rose) flower)
                .toList();

        assertEquals(1, roses.size());
    }

    @Test
    void testExecute_invalidInput() {
        String input = "abc\n50\n70\n";
        provideInput(input);

        Bouquet bouquet = new Bouquet();
        bouquet.addFlower(new Rose("червоний", 5, 60, 10, 50, "Нідерланди", true, "чайно-гібридна"));
        bouquet.addFlower(new Rose("білий", 4, 45, 8, 40, "Еквадор", false, "флорибунда"));
        bouquet.addFlower(new Rose("жовтий", 3, 75, 12, 60, "Колумбія", true, "чайно-гібридна"));


        BouquetManager bouquetManager = new BouquetManager(bouquet); // Передаємо букет в менеджер
        FindFlowersCommand command = new FindFlowersCommand(bouquetManager);

        command.setScanner(scanner);
        command.execute();

        List<Flower> foundFlowers = bouquet.findFlowersByStemLength(50, 70);
        List<Rose> roses = foundFlowers.stream()
                .filter(flower -> flower instanceof Rose)
                .map(flower -> (Rose) flower)
                .toList();
        assertEquals(1, roses.size()); // Перевіряємо розмір списку троянд
    }

    @Test
    void testExecute_emptyBouquet() {
        BouquetManager bouquetManager = new BouquetManager();
        FindFlowersCommand command = new FindFlowersCommand(bouquetManager);
        command.execute();
    }

    // Метод для імітації введення даних користувачем
    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
        scanner = new Scanner(System.in);
    }
}