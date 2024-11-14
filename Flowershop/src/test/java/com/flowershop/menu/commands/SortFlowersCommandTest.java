package com.flowershop.menu.commands;

import com.flowershop.manager.BouquetManager;
import com.flowershop.model.Bouquet;
import com.flowershop.model.Flower;
import com.flowershop.model.flowers.Rose;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class SortFlowersCommandTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Test
    void execute_bouquetNotEmpty() {
        // Створення моку BouquetManager
        BouquetManager bouquetManagerMock = Mockito.mock(BouquetManager.class);

        // Створення зразкового букету з квітами
        Bouquet bouquet = new Bouquet();
        bouquet.addFlower(new Rose("red", 1, 70, 6, 100, "Germany", true, "Freedom"));
        bouquet.addFlower(new Rose("white", 10, 50, 4, 50, "Netherlands", false, "Elegance"));
        bouquet.addFlower(new Rose("yellow", 5, 60, 5, 75, "Ukraine", true, "Sunshine"));


        // Вказуємо, що bouquetManagerMock.getBouquet() має повертати bouquet
        when(bouquetManagerMock.getBouquet()).thenReturn(bouquet);

        // Створення об'єкта SortFlowersCommand
        SortFlowersCommand command = new SortFlowersCommand(bouquetManagerMock);

        // Виконання команди та перевірка виводу
        System.setOut(new PrintStream(outContent));
        command.execute();
        assertTrue(outContent.toString().contains("Квіти в букеті відсортовані за свіжістю."));
        verify(bouquetManagerMock, times(1)).getBouquet(); // Перевіряємо, що метод getBouquet викликався 1 раз
        System.setOut(originalOut);

        // Перевірка сортування (за свіжістю)
        assertEquals(10, bouquet.getFlowers().get(0).getFreshnessLevel());
        assertEquals(5, bouquet.getFlowers().get(1).getFreshnessLevel());
        assertEquals(1, bouquet.getFlowers().get(2).getFreshnessLevel());
    }

    @Test
    void execute_bouquetEmpty() {
        // Створення моку BouquetManager
        BouquetManager bouquetManagerMock = Mockito.mock(BouquetManager.class);

        // Створення моку букету
        Bouquet bouquetMock = Mockito.mock(Bouquet.class);
        when(bouquetManagerMock.getBouquet()).thenReturn(bouquetMock);
        when(bouquetMock.getFlowers()).thenReturn(new ArrayList<>());

        // Створення об'єкта SortFlowersCommand
        SortFlowersCommand command = new SortFlowersCommand(bouquetManagerMock);

        // Виконання команди та перевірка виводу
        System.setOut(new PrintStream(outContent));
        command.execute();
        assertTrue(outContent.toString().contains("Сортування неможливе, бо букет не створено."));
        System.setOut(originalOut);
        verify(bouquetMock, never()).sortFlowersByFreshness(); // Перевіряємо, що метод сортування не викликався
    }
}