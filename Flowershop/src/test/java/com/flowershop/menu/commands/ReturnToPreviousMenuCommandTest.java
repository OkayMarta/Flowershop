package com.flowershop.menu.commands;

import com.flowershop.menu.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class ReturnToPreviousMenuCommandTest {

    private Menu menuMock;
    private ReturnToPreviousMenuCommand returnToPreviousMenuCommand;

    @BeforeEach
    void setUp() {
        menuMock = Mockito.mock(Menu.class); // Створюємо мок об'єкт класу Menu
        returnToPreviousMenuCommand = new ReturnToPreviousMenuCommand(menuMock); // Створюємо об'єкт класу ReturnToPreviousMenuCommand
    }

    @Test
    void execute_callsReturnToPreviousMenu() {
        returnToPreviousMenuCommand.execute();
        verify(menuMock, times(1)).returnToPreviousMenu(); // Перевіряємо, чи метод returnToPreviousMenu() був викликаний 1 раз
    }
}