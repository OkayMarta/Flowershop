package com.flowershop.menu.commands;

import com.flowershop.manager.BouquetManager;
import com.flowershop.model.Bouquet;
import com.flowershop.model.flowers.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RemoveFlowerCommandTest {

    private BouquetManager bouquetManagerMock;
    private RemoveFlowerCommand removeFlowerCommand;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    // Перенаправлення System.out в outContent
    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    // Повернення System.out в оригінальний потік
    @BeforeEach
    void setUp() {
        bouquetManagerMock = Mockito.mock(BouquetManager.class);
        removeFlowerCommand = new RemoveFlowerCommand(bouquetManagerMock);
    }

    @Test
    void execute_emptyBouquet() {
        when(bouquetManagerMock.getBouquet()).thenReturn(new Bouquet());
        removeFlowerCommand.execute();
        assertEquals("У букеті немає квітів.".trim(), outContent.toString().trim());
        System.setOut(originalOut);
    }

    @Test
    void execute_validInput() {
        Bouquet bouquetMock = Mockito.mock(Bouquet.class);
        Rose rose = new Rose("червоний", 3, 70, 6, 100, "Польща", true, "Freedom");
        Hydrangea hydrangea = new Hydrangea("жовтий", 2, 50, 50, 600, "Голландія", "кругле", "голубий");

        when(bouquetMock.getFlowers()).thenReturn(Arrays.asList(rose, hydrangea));
        doNothing().when(bouquetMock).removeFlower(rose);
        when(bouquetManagerMock.getBouquet()).thenReturn(bouquetMock);
        removeFlowerCommand.scanner = new Scanner("1\n");

        removeFlowerCommand.execute();

        assertTrue(outContent.toString().contains("Квітка видалена з букету."));
        verify(bouquetMock, times(1)).removeFlower(rose);
        System.setOut(originalOut);
    }

    @Test
    void execute_invalidInput() {
        Bouquet bouquetMock = Mockito.mock(Bouquet.class);
        Rose rose = new Rose("червоний", 3, 70, 6, 100, "Польща", true, "Freedom");
        Hydrangea hydrangea = new Hydrangea("жовтий", 2, 50, 50, 600, "Голландія", "кругле", "голубий");

        when(bouquetMock.getFlowers()).thenReturn(Arrays.asList(rose, hydrangea));
        when(bouquetManagerMock.getBouquet()).thenReturn(bouquetMock);
        removeFlowerCommand.scanner = new Scanner("3\n");

        removeFlowerCommand.execute();

        assertTrue(outContent.toString().contains("Невірний номер квітки."));
        verify(bouquetMock, never()).removeFlower(any());
        System.setOut(originalOut);
    }
}