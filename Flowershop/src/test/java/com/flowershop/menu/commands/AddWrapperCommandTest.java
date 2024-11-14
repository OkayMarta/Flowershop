package com.flowershop.menu.commands;

import com.flowershop.manager.BouquetManager;
import com.flowershop.model.accessories.Wrapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class AddWrapperCommandTest {

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
    void testExecute_addWrapper() {
        String input = "папір\nрожевий\n10.00\n60 x 70\n";
        provideInput(input);

        BouquetManager bouquetManager = new BouquetManager();
        AddWrapperCommand command = new AddWrapperCommand(bouquetManager);
        command.setScanner(scanner);
        command.execute();

        Wrapper wrapper = (Wrapper) bouquetManager.getBouquet().getAccessories().get(0);
        assertEquals("папір", wrapper.getMaterial());
        assertEquals("рожевий", wrapper.getColor());
        assertEquals(10.00, wrapper.getPrice());
        assertEquals("60 x 70", wrapper.getSize());
    }

    @Test
    void testExecute_replaceWrapper() {
        String input = "так\nплівка\nпрозорий\n12.00\n70 x 80\n";
        provideInput(input);
        BouquetManager bouquetManager = new BouquetManager();

        bouquetManager.addAccessory(new Wrapper("папір", "рожевий", 10.00, "60 x 70"));

        AddWrapperCommand command = new AddWrapperCommand(bouquetManager);
        command.setScanner(scanner);
        command.execute();

        Wrapper wrapper = (Wrapper) bouquetManager.getBouquet().getAccessories().get(0);
        assertEquals("плівка", wrapper.getMaterial());
        assertEquals("прозорий", wrapper.getColor());
        assertEquals(12.00, wrapper.getPrice());
        assertEquals("70 x 80", wrapper.getSize());
    }

    @Test
    void testExecute_cancelReplaceWrapper() {
        String input = "ні\n";
        provideInput(input);
        BouquetManager bouquetManager = new BouquetManager();
        Wrapper initialWrapper = new Wrapper("папір", "рожевий", 10.00, "60 x 70");
        bouquetManager.addAccessory(initialWrapper);
        AddWrapperCommand command = new AddWrapperCommand(bouquetManager);
        command.setScanner(scanner);
        command.execute();

        Wrapper wrapper = (Wrapper) bouquetManager.getBouquet().getAccessories().get(0);

        assertEquals(initialWrapper, wrapper);
    }

    @Test
    void testExecute_invalidDoubleInput() {
        String input = "папір\nрожевий\n10.00a\n10.00\n60 x 70\n";
        provideInput(input);
        BouquetManager bouquetManager = new BouquetManager();
        AddWrapperCommand command = new AddWrapperCommand(bouquetManager);
        command.setScanner(scanner);
        command.execute();
        Wrapper wrapper = (Wrapper) bouquetManager.getBouquet().getAccessories().get(0);
        assertEquals(10.00, wrapper.getPrice());
    }

    // Метод для імітації введення даних користувачем
    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
        scanner = new Scanner(System.in);
    }
}