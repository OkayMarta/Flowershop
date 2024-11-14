package com.flowershop.menu.commands;

import com.flowershop.manager.BouquetManager;
import com.flowershop.model.accessories.Postcard;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class AddPostcardCommandTest {

    private final InputStream systemIn = System.in;
    private ByteArrayInputStream testIn;
    private Scanner scanner;

    // Відновлення стандартного потоку введення
    @AfterEach
    void tearDown() {
        System.setIn(systemIn);
    }

    @Test
    void testExecute_addPostcard() {
        String input = "картон\nчервоний\n15.50\nЗ Днем Народження!\n";
        provideInput(input);

        BouquetManager bouquetManager = new BouquetManager();
        AddPostcardCommand command = new AddPostcardCommand(bouquetManager);
        command.setScanner(scanner);
        command.execute();

        Postcard postcard = (Postcard) bouquetManager.getBouquet().getAccessories().get(0);

        assertEquals("картон", postcard.getMaterial());
        assertEquals("червоний", postcard.getColor());
        assertEquals(15.50, postcard.getPrice());
        assertEquals("З Днем Народження!", postcard.getGreetingText());
    }

    @Test
    void testExecute_replacePostcard() {
        String input = "так\nпапір\nсиній\n20.00\nВітаю!\n";
        provideInput(input);

        BouquetManager bouquetManager = new BouquetManager();
        bouquetManager.addAccessory(new Postcard("картон", "червоний", 15.50, "З Днем Народження!"));

        AddPostcardCommand command = new AddPostcardCommand(bouquetManager);
        command.setScanner(scanner);
        command.execute();

        Postcard postcard = (Postcard) bouquetManager.getBouquet().getAccessories().get(0);
        assertEquals("папір", postcard.getMaterial());
        assertEquals("синій", postcard.getColor());
        assertEquals(20.00, postcard.getPrice());
        assertEquals("Вітаю!", postcard.getGreetingText());
    }

    @Test
    void testExecute_cancelReplacePostcard() {
        String input = "ні\n";
        provideInput(input);

        BouquetManager bouquetManager = new BouquetManager();
        Postcard initialPostcard = new Postcard("картон", "червоний", 15.50, "З Днем Народження!");
        bouquetManager.addAccessory(initialPostcard);
        AddPostcardCommand command = new AddPostcardCommand(bouquetManager);
        command.setScanner(scanner);
        command.execute();

        Postcard postcard = (Postcard) bouquetManager.getBouquet().getAccessories().get(0);
        assertEquals(initialPostcard, postcard);
    }

    @Test
    void testExecute_invalidDoubleInput() {
        String input = "картон\nчервоний\n15.50a\n15.50\nЗ Днем Народження!\n";
        provideInput(input);
        BouquetManager bouquetManager = new BouquetManager();
        AddPostcardCommand command = new AddPostcardCommand(bouquetManager);
        command.setScanner(scanner);
        command.execute();
        Postcard postcard = (Postcard) bouquetManager.getBouquet().getAccessories().get(0);
        assertEquals(15.50, postcard.getPrice());
    }

    // Метод для імітації введення даних користувачем
    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
        scanner = new Scanner(System.in);
    }
}