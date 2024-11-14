package com.flowershop.menu.commands;

import com.flowershop.manager.BouquetManager;
import com.flowershop.model.flowers.Rose;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class AddRoseCommandTest {

    private final InputStream systemIn = System.in;
    private ByteArrayInputStream testIn;
    private Scanner scanner;

    @BeforeEach
    void setUp() {

    }

    //Відновлення стандартного введення після кожного тесту
    @AfterEach
    void tearDown() {
        System.setIn(systemIn);
    }

    @Test
    void testExecute_validInput() {
        String input = "червоний\n5\n60.5\n11.2\n65.70\nКенія\ntrue\nчайно-гібридна\nприємний\n";
        provideInput(input);

        BouquetManager bouquetManager = new BouquetManager();
        AddRoseCommand command = new AddRoseCommand(bouquetManager);
        command.setScanner(scanner);
        command.execute();

        Rose rose = (Rose) bouquetManager.getBouquet().getFlowers().get(0);

        assertEquals("червоний", rose.getColor());
        assertEquals(5, rose.getFreshnessLevel());
        assertEquals(60.5, rose.getStemLength());
        assertEquals(11.2, rose.getFlowerSize());
        assertEquals(65.70, rose.getPrice());
        assertEquals("Кенія", rose.getOriginCountry());
        assertTrue(rose.isHasThorns());
        assertEquals("чайно-гібридна", rose.getRoseType());
    }

    @Test
    void testExecute_invalidFreshnessLevelInput() {
        String input = "червоний\n0\n5\n60.5\n11.2\n65.70\nКенія\ntrue\nчайно-гібридна\nприємний\n";
        provideInput(input);

        BouquetManager bouquetManager = new BouquetManager();
        AddRoseCommand command = new AddRoseCommand(bouquetManager);
        command.setScanner(scanner);
        command.execute();

        Rose rose = (Rose) bouquetManager.getBouquet().getFlowers().get(0);
        assertEquals(5, rose.getFreshnessLevel());
    }

    @Test
    void testExecute_invalidDoubleInput() {
        String input = "червоний\n5\n60.5a\n60.5\n11.2\n65.70\nКенія\ntrue\nчайно-гібридна\nприємний\n";
        provideInput(input);

        BouquetManager bouquetManager = new BouquetManager();
        AddRoseCommand command = new AddRoseCommand(bouquetManager);
        command.setScanner(scanner);
        command.execute();
        Rose rose = (Rose) bouquetManager.getBouquet().getFlowers().get(0);
        assertEquals(60.5, rose.getStemLength());
    }

    @Test
    void testExecute_invalidBooleanInput() {
        String input = "червоний\n5\n60.5\n11.2\n65.70\nКенія\naaa\ntrue\nчайно-гібридна\nприємний\n";
        provideInput(input);

        BouquetManager bouquetManager = new BouquetManager();
        AddRoseCommand command = new AddRoseCommand(bouquetManager);
        command.setScanner(scanner);
        command.execute();

        Rose rose = (Rose) bouquetManager.getBouquet().getFlowers().get(0);
        assertTrue(rose.isHasThorns());
    }

    // Метод для імітації введення даних користувачем
    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
        scanner = new Scanner(System.in);
    }
}