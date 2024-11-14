package com.flowershop.menu.commands;

import com.flowershop.manager.BouquetManager;
import com.flowershop.model.flowers.Lily;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class AddLilyCommandTest {

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
    void testExecute_validInput() {
        String input = "білий\n4\n70.3\n12.7\n85.50\nГолландія\n3\nприємний\n";
        provideInput(input);

        BouquetManager bouquetManager = new BouquetManager();
        AddLilyCommand command = new AddLilyCommand(bouquetManager);
        command.setScanner(scanner);
        command.execute();

        Lily lily = (Lily) bouquetManager.getBouquet().getFlowers().get(0);
        assertEquals("білий", lily.getColor());
        assertEquals(4, lily.getFreshnessLevel());
        assertEquals(70.3, lily.getStemLength());
        assertEquals(12.7, lily.getFlowerSize());
        assertEquals(85.50, lily.getPrice());
        assertEquals("Голландія", lily.getOriginCountry());
        assertEquals(3, lily.getNumberOfFlowersOnStem());
        assertEquals("приємний", lily.getFragrance());
    }

    @Test
    void testExecute_invalidFreshnessLevelInput() {
        String input = "білий\n0\n4\n70.3\n12.7\n85.50\nГолландія\n3\nприємний\n";
        provideInput(input);

        BouquetManager bouquetManager = new BouquetManager();
        AddLilyCommand command = new AddLilyCommand(bouquetManager);
        command.setScanner(scanner);
        command.execute();

        Lily lily = (Lily) bouquetManager.getBouquet().getFlowers().get(0);
        assertEquals(4, lily.getFreshnessLevel());

    }

    @Test
    void testExecute_invalidNumberOfFlowersInput() {
        String input = "білий\n4\n70.3\n12.7\n85.50\nГолландія\n0\n3\nприємний\n";
        provideInput(input);

        BouquetManager bouquetManager = new BouquetManager();
        AddLilyCommand command = new AddLilyCommand(bouquetManager);
        command.setScanner(scanner);
        command.execute();
        Lily lily = (Lily) bouquetManager.getBouquet().getFlowers().get(0);
        assertEquals(3, lily.getNumberOfFlowersOnStem());
    }

    @Test
    void testExecute_invalidDoubleInput() {

        String input = "білий\n4\n70.3a\n70.3\n12.7\n85.50\nГолландія\n3\nприємний\n";
        provideInput(input);
        BouquetManager bouquetManager = new BouquetManager();
        AddLilyCommand command = new AddLilyCommand(bouquetManager);
        command.setScanner(scanner); // Передаємо Scanner в команду
        command.execute();
        Lily lily = (Lily) bouquetManager.getBouquet().getFlowers().get(0);
        assertEquals(70.3, lily.getStemLength());
    }

    // Метод для імітації введення даних користувачем
    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
        scanner = new Scanner(System.in);
    }
}