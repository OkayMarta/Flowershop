package com.flowershop.menu.commands;

import com.flowershop.manager.BouquetManager;
import com.flowershop.menu.commands.AddBasketCommand;
import com.flowershop.model.accessories.Basket;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

class AddBasketCommandTest {

    @Test
    void testAddBasket_newBasket() {
        String input = "дерево\nкоричневий\n150\nрозмір: 30 x 40\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);

        BouquetManager bouquetManager = new BouquetManager();
        AddBasketCommand command = new AddBasketCommand(bouquetManager);
        command.setScanner(scanner);
        command.execute();

        Basket basket = (Basket) bouquetManager.getBouquet().getAccessories().get(0);
        assertEquals("дерево", basket.getMaterial());
        assertEquals("коричневий", basket.getColor());
        assertEquals(150.0, basket.getPrice());
        assertEquals("30 x 40", basket.getUniqueCharacteristic());
        System.setIn(System.in);
    }

    @Test
    void testAddBasket_replaceBasket() {
        String input = "так\nпластик\nчорний\n200\nрозмір: 20 x 30\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);

        BouquetManager bouquetManager = new BouquetManager();
        bouquetManager.addAccessory(new Basket("дерево", "коричневий", 150, "розмір: 30 x 40"));
        AddBasketCommand command = new AddBasketCommand(bouquetManager);
        command.setScanner(scanner);
        command.execute();

        Basket basket = (Basket) bouquetManager.getBouquet().getAccessories().get(0);
        assertEquals("пластик", basket.getMaterial());
        assertEquals("чорний", basket.getColor());
        assertEquals(200.0, basket.getPrice());
        assertEquals("20 x 30", basket.getUniqueCharacteristic());

        System.setIn(System.in);
    }

    @Test
    void testAddBasket_cancelReplaceBasket() {
        String input = "ні\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);

        BouquetManager bouquetManager = new BouquetManager();
        Basket initialBasket = new Basket("дерево", "коричневий", 150, "розмір: 30 x 40");
        bouquetManager.addAccessory(initialBasket);

        AddBasketCommand command = new AddBasketCommand(bouquetManager);
        command.setScanner(scanner);
        command.execute();

        Basket basket = (Basket) bouquetManager.getBouquet().getAccessories().get(0);

        assertEquals(initialBasket, basket);

        System.setIn(System.in);
    }

    @Test
    void testGetDoubleInput_validInput() {
        String input = "123.45\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        BouquetManager bouquetManager = new BouquetManager();
        AddBasketCommand command = new AddBasketCommand(bouquetManager);
        Scanner scanner = new Scanner(System.in);
        double result = command.getDoubleInput("Введіть число: ", scanner);

        assertEquals(123.45, result, 0.001);

        System.setIn(System.in); // Повернення до початкового System.in
    }

    @Test
    void testGetDoubleInput_invalidInput() {
        String input = "abc\n123.45\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        BouquetManager bouquetManager = new BouquetManager();
        AddBasketCommand command = new AddBasketCommand(bouquetManager);
        Scanner scanner = new Scanner(System.in);
        double result = command.getDoubleInput("Введіть число: ", scanner);

        assertEquals(123.45, result, 0.001);

        System.setIn(System.in); // Повернення до початкового System.in
    }

}