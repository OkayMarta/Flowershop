package com.flowershop.menu.commands;

import com.flowershop.manager.BouquetManager;
import com.flowershop.model.flowers.Orchid;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class AddOrchidCommandTest {

    private final InputStream systemIn = System.in;
    private ByteArrayInputStream testIn;
    private Scanner scanner;

    // Відновлення стандартного потоку введення
    @AfterEach
    void tearDown() {
        System.setIn(systemIn);
    }

    @Test
    void testExecute_validInput() {
        String input = "фіолетовий\n3\n45.2\n10.8\n70.00\nТаїланд\nФаленопсис\nтемно-фіолетовий\n";
        provideInput(input);

        BouquetManager bouquetManager = new BouquetManager();
        AddOrchidCommand command = new AddOrchidCommand(bouquetManager);
        command.setScanner(scanner);
        command.execute();

        Orchid orchid = (Orchid) bouquetManager.getBouquet().getFlowers().get(0);
        assertEquals("фіолетовий", orchid.getColor());
        assertEquals(3, orchid.getFreshnessLevel());
        assertEquals(45.2, orchid.getStemLength());
        assertEquals(10.8, orchid.getFlowerSize());
        assertEquals(70.00, orchid.getPrice());
        assertEquals("Таїланд", orchid.getOriginCountry());
        assertEquals("Фаленопсис", orchid.getOrchidType());
        assertEquals("темно-фіолетовий", orchid.getLipColor());
    }

    @Test
    void testExecute_invalidFreshnessLevel() {
        String input = "фіолетовий\n0\n3\n45.2\n10.8\n70.00\nТаїланд\nФаленопсис\nтемно-фіолетовий\n";
        provideInput(input);

        BouquetManager bouquetManager = new BouquetManager();
        AddOrchidCommand command = new AddOrchidCommand(bouquetManager);
        command.setScanner(scanner);
        command.execute();
        Orchid orchid = (Orchid) bouquetManager.getBouquet().getFlowers().get(0);
        assertEquals(3, orchid.getFreshnessLevel());
    }

    @Test
    void testExecute_invalidDoubleInput() {
        String input = "фіолетовий\n3\n45.2a\n45.2\n10.8\n70.00\nТаїланд\nФаленопсис\nтемно-фіолетовий\n";
        provideInput(input);
        BouquetManager bouquetManager = new BouquetManager();
        AddOrchidCommand command = new AddOrchidCommand(bouquetManager);
        command.setScanner(scanner);
        command.execute();
        Orchid orchid = (Orchid) bouquetManager.getBouquet().getFlowers().get(0);
        assertEquals(45.2, orchid.getStemLength());
    }

    // Метод для імітації введення даних користувачем
    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
        scanner = new Scanner(System.in);
    }
}