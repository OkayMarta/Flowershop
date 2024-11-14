package com.flowershop.menu.commands;

import com.flowershop.manager.BouquetManager;
import com.flowershop.model.Bouquet;
import com.flowershop.model.accessories.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RemoveAccessoryCommandTest {

    private BouquetManager bouquetManagerMock;
    private RemoveAccessoryCommand removeAccessoryCommand;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    // Перенаправл. стандартний потік виводу на об'єкт ByteArrayOutputStream
    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    // Повернення стандартного потоку виводу
    @BeforeEach
    void setUp() {
        bouquetManagerMock = Mockito.mock(BouquetManager.class);
        removeAccessoryCommand = new RemoveAccessoryCommand(bouquetManagerMock);
    }

    @Test
    void execute_emptyBouquet() {
        when(bouquetManagerMock.getBouquet()).thenReturn(new Bouquet());
        removeAccessoryCommand.execute();
        assertEquals("У букеті немає аксесуарів.".trim(), outContent.toString().trim());

        System.setOut(originalOut);
    }

    @Test
    void execute_validInput() {
        Bouquet bouquetMock = Mockito.mock(Bouquet.class); // Create a mock Bouquet
        Wrapper wrapper = new Wrapper("папір", "білий", 55, "розмір: 60x48");
        Ribbon ribbon = new Ribbon("шовк", "фіолетовий", 10.0, 5.0, 33.0);

        // Використовую doReturn, щоб налаштувати поведінку методів bouquetMock
        when(bouquetMock.getAccessories()).thenReturn(Arrays.asList(wrapper, ribbon)); // Повернути список аксесуарів букету
        doNothing().when(bouquetMock).removeAccessory(wrapper); // Змусити removeAccessory() нічого не повертати

        when(bouquetManagerMock.getBouquet()).thenReturn(bouquetMock); // Повернути mock букет
        removeAccessoryCommand.scanner = new Scanner("1\n"); // Введення користувача

        removeAccessoryCommand.execute();

        assertTrue(outContent.toString().contains("Аксесуар видалений з букету."));
        verify(bouquetMock, times(1)).removeAccessory(wrapper); // Перевірка на mock букет
        System.setOut(originalOut);
    }

    @Test
    void execute_invalidInput() {
        Bouquet bouquetMock = Mockito.mock(Bouquet.class); // Mock the Bouquet object
        Wrapper wrapper = new Wrapper("папір", "білий", 55, "розмір: 60x48");
        Ribbon ribbon = new Ribbon("шовк", "фіолетовий", 10.0, 5.0, 33.0);

        when(bouquetMock.getAccessories()).thenReturn(Arrays.asList(wrapper, ribbon)); // Налаштувати поведінку getAccessories().
        when(bouquetManagerMock.getBouquet()).thenReturn(bouquetMock); // Повернути mock букет

        removeAccessoryCommand.scanner = new Scanner("3\n"); // Невірний ввід користувача

        removeAccessoryCommand.execute();

        assertTrue(outContent.toString().contains("Невірний номер аксесуару."));
        verify(bouquetMock, never()).removeAccessory(any()); // Перевірка на mock букет
        System.setOut(originalOut);
    }
}