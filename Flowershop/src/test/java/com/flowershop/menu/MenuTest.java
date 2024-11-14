package com.flowershop.menu;

import com.flowershop.manager.BouquetManager;
import com.flowershop.menu.commands.Command;
import com.flowershop.menu.commands.ExitCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MenuTest {

    private Menu menu;
    private BouquetManager bouquetManagerMock; // Мок-об'єкт менеджера букетів
    private Scanner scannerMock; // Мок-об'єкт сканера
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream(); // Для перенаправлення виводу

    @BeforeEach
    void setUp() {
        bouquetManagerMock = Mockito.mock(BouquetManager.class);
        scannerMock = Mockito.mock(Scanner.class);
        menu = new Menu();
        menu.bouquetManager = bouquetManagerMock;
        menu.scanner = scannerMock;
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testCreateMainMenu() {
        List<MenuItem> mainMenu = menu.createMainMenu();
        assertEquals(4, mainMenu.size()); // Перевірка кількості пунктів головного меню
        assertEquals("Вихід", mainMenu.get(3).getTitle()); // Перевірка назви останнього пункту
        assertTrue(mainMenu.get(3).getCommand() instanceof ExitCommand); // Перевірка типу команди останнього пункту
    }

    @Test
    void testGetChoice_validInput() {
        when(scannerMock.hasNextInt()).thenReturn(true);
        when(scannerMock.nextInt()).thenReturn(1);
        assertEquals(1, menu.getChoice());
        verify(scannerMock).hasNextInt(); // Перевірка, що метод hasNextInt() був викликаний на об'єкті scannerMock
        verify(scannerMock).nextInt(); // Перевірка, що метод nextInt() був викликаний на об'єкті scannerMock
    }

    @Test
    void testGetChoice_invalidInput() {
        when(scannerMock.hasNextInt()).thenReturn(false, true);
        when(scannerMock.next()).thenReturn("abc");
        when(scannerMock.nextInt()).thenReturn(1);
        assertEquals(1, menu.getChoice());
        verify(scannerMock, times(2)).hasNextInt(); // Перевірка, що метод hasNextInt() був викликаний 2 рази
        verify(scannerMock, times(1)).next(); // Перевірка, що метод next() був викликаний 1 раз
        verify(scannerMock, times(1)).nextInt(); // Перевірка, що метод nextInt() був викликаний 1 раз
    }


    @Test
    void testHandleChoice_validChoice_noSubMenu() {
        Command mockCommand = Mockito.mock(Command.class);
        List<MenuItem> currentMenu = new ArrayList<>();
        currentMenu.add(new MenuItem("Test", mockCommand));
        menu.currentMenu = currentMenu;
        menu.handleChoice(1);
        verify(mockCommand).execute(); // Перевірка, що метод execute() був викликаний на об'єкті mockCommand
    }

    @Test
    void testReturnToMainMenu() {
        menu.currentMenu = menu.createBouquetEditSubMenu(); // Переходимо до підменю
        menu.returnToMainMenu();
        assertEquals(menu.mainMenu, menu.currentMenu); // Перевірка, чи повернулись до головного меню
    }


    @Test
    void testReturnToPreviousMenu() {
        List<MenuItem> subMenu = menu.createBouquetEditSubMenu();
        menu.previousMenu = menu.mainMenu;
        menu.currentMenu = subMenu; // Перехід до підменю
        menu.returnToPreviousMenu();
        assertEquals(menu.mainMenu, menu.currentMenu); // Перевірка, чи повернулись до головного меню
    }
}