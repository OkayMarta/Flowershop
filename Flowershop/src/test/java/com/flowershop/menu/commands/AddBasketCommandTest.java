package com.flowershop.menu.commands;

import com.flowershop.manager.BouquetManager;
import com.flowershop.model.accessories.Basket;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AddBasketCommandTest {

    private BouquetManager bouquetManager;
    private AddBasketCommand addBasketCommand;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;
    private InputStream originalIn;

    @BeforeEach
    void setUp() {
        bouquetManager = mock(BouquetManager.class);
        addBasketCommand = new AddBasketCommand(bouquetManager);
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        originalIn = System.in;
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void execute_noBasket() {
        when(bouquetManager.hasAccessoryOfType(Basket.class)).thenReturn(false);

        String input = "Дерево\nКоричневий\n186.0\nрозмір: 53 x 32\n100.0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        addBasketCommand.execute();
        verify(bouquetManager, times(1)).hasAccessoryOfType(Basket.class);
        verify(bouquetManager, times(1)).addAccessory(any(Basket.class));


        String output = outputStream.toString();
        assertTrue(output.contains("Введіть характеристики кошика:"));
        assertTrue(output.contains("Кошик доданий до букету."));
    }

    @Test
    void execute_replaceBasket() {
        when(bouquetManager.hasAccessoryOfType(Basket.class)).thenReturn(true);


        String input = "так\nПластик\nЗелений\n100.0\nрозмір: 40 x 30\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        addBasketCommand.execute();


        verify(bouquetManager, times(1)).hasAccessoryOfType(Basket.class);
        verify(bouquetManager, times(1)).removeAccessoryOfType(Basket.class);
        verify(bouquetManager, times(1)).addAccessory(any(Basket.class));


        String output = outputStream.toString();
        assertTrue(output.contains("Кошик вже існує. Замінити його? (так/ні): "));
        assertTrue(output.contains("Кошик замінений."));
    }

    @Test
    void execute_cancelReplaceBasket() {
        when(bouquetManager.hasAccessoryOfType(Basket.class)).thenReturn(true);


        String input = "ні\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        addBasketCommand.execute();


        verify(bouquetManager, times(1)).hasAccessoryOfType(Basket.class);
        verify(bouquetManager, never()).removeAccessoryOfType(Basket.class);
        verify(bouquetManager, never()).addAccessory(any(Basket.class));


        String output = outputStream.toString();
        assertTrue(output.contains("Кошик вже існує. Замінити його? (так/ні): "));
        assertTrue(output.contains("Заміна кошика скасована."));
    }

    @Test
    void getAndAddBasket() {
        when(bouquetManager.hasAccessoryOfType(Basket.class)).thenReturn(false);


        String input = "Дерево\nКоричневий\n186.0\nрозмір: 53 x 32\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        addBasketCommand.getAndAddBasket();


        verify(bouquetManager, times(1)).addAccessory(any(Basket.class));
    }

    @Test
    void getInput() {

        String input = "Текст\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));  // Заменяем поток ввода

        String result = addBasketCommand.getInput("Введіть текст: ");  // Вызываем метод getInput


        assertEquals("Текст", result);
    }

    @Test
    void getDoubleInput() {
        // Ввод данных для теста
        String input = "123.45\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        double result = addBasketCommand.getDoubleInput("Введіть число: ");

        assertEquals(123.45, result, 0.001);
    }

    @Test
    void getDoubleInput_invalidInput() {
        // Ввод данных для теста
        String input = "abc\n123.45\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        double result = addBasketCommand.getDoubleInput("Введіть число: ");

        assertEquals(123.45, result, 0.001);
        String output = outputStream.toString();
        assertTrue(output.contains("Невірний формат вводу. Введіть число."));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }
}