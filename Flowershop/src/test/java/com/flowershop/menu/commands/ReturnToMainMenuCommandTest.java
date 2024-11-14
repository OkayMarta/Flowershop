package com.flowershop.menu.commands;

import com.flowershop.menu.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class ReturnToMainMenuCommandTest {

    private Menu menuMock;
    private ReturnToMainMenuCommand returnToMainMenuCommand;

    //
    @BeforeEach
    void setUp() {
        menuMock = Mockito.mock(Menu.class); // Створюємо мок об'єкт класу Menu
        returnToMainMenuCommand = new ReturnToMainMenuCommand(menuMock); // Створюємо об'єкт класу ReturnToMainMenuCommand
    }

    @Test
    void execute_callsReturnToMainMenu() {
        returnToMainMenuCommand.execute();
        verify(menuMock, times(1)).returnToMainMenu(); // Перевіряємо, чи метод returnToMainMenu() був викликаний один раз
    }
}