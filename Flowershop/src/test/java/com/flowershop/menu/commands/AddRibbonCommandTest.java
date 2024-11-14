package com.flowershop.menu.commands;

import com.flowershop.manager.BouquetManager;
import com.flowershop.model.accessories.Ribbon;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class AddRibbonCommandTest {

    private final InputStream systemIn = System.in;
    private ByteArrayInputStream testIn;
    private Scanner scanner;

    // Відновлення стандартного потоку вводу після кожного тесту
    @AfterEach
    void tearDown() {
        System.setIn(systemIn);
    }

    @Test
    void testExecute_addRibbon() {
        String input = "атлас\nзолотий\n25.00\n5.0\n100.0\n";
        provideInput(input);

        BouquetManager bouquetManager = new BouquetManager();
        AddRibbonCommand command = new AddRibbonCommand(bouquetManager);
        command.setScanner(scanner);
        command.execute();

        Ribbon ribbon = (Ribbon) bouquetManager.getBouquet().getAccessories().get(0);
        assertEquals("атлас", ribbon.getMaterial());
        assertEquals("золотий", ribbon.getColor());
        assertEquals(25.00, ribbon.getPrice());
        assertEquals(5.0, ribbon.getWidth());
        assertEquals(100.0, ribbon.getLength());
    }

    @Test
    void testExecute_replaceRibbon() {
        String input = "так\nшовк\nсрібний\n30.00\n2.5\n50.0\n";
        provideInput(input);
        BouquetManager bouquetManager = new BouquetManager();
        bouquetManager.addAccessory(new Ribbon("атлас", "золотий", 25.00, 5.0, 100.0));
        AddRibbonCommand command = new AddRibbonCommand(bouquetManager);
        command.setScanner(scanner);
        command.execute();

        Ribbon ribbon = (Ribbon) bouquetManager.getBouquet().getAccessories().get(0);
        assertEquals("шовк", ribbon.getMaterial());
        assertEquals("срібний", ribbon.getColor());
        assertEquals(30.00, ribbon.getPrice());
        assertEquals(2.5, ribbon.getWidth());
        assertEquals(50.0, ribbon.getLength());
    }

    @Test
    void testExecute_cancelReplaceRibbon() {
        String input = "ні\n";
        provideInput(input);
        BouquetManager bouquetManager = new BouquetManager();

        Ribbon initialRibbon = new Ribbon("атлас", "золотий", 25.00, 5.0, 100.0);
        bouquetManager.addAccessory(initialRibbon);


        AddRibbonCommand command = new AddRibbonCommand(bouquetManager);
        command.setScanner(scanner);
        command.execute();

        Ribbon ribbon = (Ribbon) bouquetManager.getBouquet().getAccessories().get(0);
        assertEquals(initialRibbon, ribbon);
    }

    @Test
    void testExecute_invalidDoubleInput() {
        String input = "атлас\nзолотий\n25.00a\n25.00\n5.0\n100.0\n";
        provideInput(input);
        BouquetManager bouquetManager = new BouquetManager();
        AddRibbonCommand command = new AddRibbonCommand(bouquetManager);
        command.setScanner(scanner);
        command.execute();

        Ribbon ribbon = (Ribbon) bouquetManager.getBouquet().getAccessories().get(0);
        assertEquals(25.00, ribbon.getPrice());
    }

    // Метод для імітації введення даних користувачем
    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes()); // перетворюємо рядок в байти
        System.setIn(testIn);
        scanner = new Scanner(System.in);
    }
}