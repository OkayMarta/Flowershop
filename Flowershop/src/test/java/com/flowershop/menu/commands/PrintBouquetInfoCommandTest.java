package com.flowershop.menu.commands;

import com.flowershop.manager.BouquetManager;
import com.flowershop.model.Bouquet;
import com.flowershop.model.flowers.*;
import com.flowershop.model.accessories.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PrintBouquetInfoCommandTest {

    private BouquetManager bouquetManagerMock;
    private PrintBouquetInfoCommand printBouquetInfoCommand;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    // Перед кожним тестом очищаємо вміст outContent
    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    // Після кожного тесту відновлюємо System.out
    @BeforeEach
    void setUp() {
        bouquetManagerMock = Mockito.mock(BouquetManager.class);
        printBouquetInfoCommand = new PrintBouquetInfoCommand(bouquetManagerMock);
    }

    @Test
    void execute_emptyBouquet() {
        // Перевірка, що метод getBouquet() викликано один раз
        when(bouquetManagerMock.getBouquet()).thenReturn(new Bouquet());

        printBouquetInfoCommand.execute();

        // Перевірка, що на екран виведено відповідне повідомлення
        assertEquals("Букету не створено.".trim(), outContent.toString().trim());

        // Відновлення System.out
        System.setOut(originalOut);
    }

    @Test
    void execute_nonEmptyBouquet() {
        Bouquet bouquet = new Bouquet();
        bouquet.addFlower(new Rose("червоний", 3, 70, 6, 100, "Польща", true, "Freedom"));
        bouquet.addFlower(new Hydrangea("жовтий", 2, 50, 50, 600, "Голландія", "кругле", "голубий"));
        bouquet.addAccessory(new Wrapper("папір", "білий", 55, "розмір: 60x48"));
        bouquet.addAccessory(new Ribbon("шовк", "фіолетовий", 10.0, 5.0, 33.0));

        when(bouquetManagerMock.getBouquet()).thenReturn(bouquet); // Коли викликається метод getBouquet(), повертати створений букет

        printBouquetInfoCommand.execute();

        String expectedOutput = bouquet.getBouquetInfo();

        assertEquals(expectedOutput.trim(), outContent.toString().trim());
        System.setOut(originalOut);
    }
}