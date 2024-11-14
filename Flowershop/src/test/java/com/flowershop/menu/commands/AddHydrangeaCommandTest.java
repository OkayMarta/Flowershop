package com.flowershop.menu.commands;

import com.flowershop.manager.BouquetManager;
import com.flowershop.menu.commands.AddHydrangeaCommand;
import com.flowershop.model.flowers.Hydrangea;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class AddHydrangeaCommandTest {
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
        String input = "рожевий\n3\n50.5\n20.2\n150.75\nНідерланди\nкулястий\nфіолетовий\n";
        provideInput(input);

        BouquetManager bouquetManager = new BouquetManager();
        AddHydrangeaCommand command = new AddHydrangeaCommand(bouquetManager);
        command.setScanner(scanner);
        command.execute();

        Hydrangea hydrangea = (Hydrangea) bouquetManager.getBouquet().getFlowers().get(0);
        assertEquals("рожевий", hydrangea.getColor());
        assertEquals(3, hydrangea.getFreshnessLevel());
        assertEquals(50.5, hydrangea.getStemLength());
        assertEquals(20.2, hydrangea.getFlowerSize());
        assertEquals(150.75, hydrangea.getPrice());
        assertEquals("Нідерланди", hydrangea.getOriginCountry());
        assertEquals("кулястий", hydrangea.getInflorescenceType());
        assertEquals("фіолетовий", hydrangea.getInflorescenceColor());
    }

    @Test
    void testExecute_invalidFreshnessLevel() {
        String input = "рожевий\n0\n3\n50.5\n20.2\n150.75\nНідерланди\nкулястий\nфіолетовий\n";
        provideInput(input);

        BouquetManager bouquetManager = new BouquetManager();
        AddHydrangeaCommand command = new AddHydrangeaCommand(bouquetManager);
        command.setScanner(scanner);
        command.execute();

        Hydrangea hydrangea = (Hydrangea) bouquetManager.getBouquet().getFlowers().get(0);
        assertEquals(3, hydrangea.getFreshnessLevel());
    }

    @Test
    void testExecute_invalidDoubleInput() {
        String input = "рожевий\n3\nab\n50.5\n20.2\n150.75\nНідерланди\nкулястий\nфіолетовий\n";
        provideInput(input);

        BouquetManager bouquetManager = new BouquetManager();
        AddHydrangeaCommand command = new AddHydrangeaCommand(bouquetManager);
        command.setScanner(scanner);
        command.execute();


        Hydrangea hydrangea = (Hydrangea) bouquetManager.getBouquet().getFlowers().get(0);
        assertEquals(50.5, hydrangea.getStemLength());
    }

    // Метод для імітації введення даних користувачем
    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
        scanner = new Scanner(System.in);
    }
}