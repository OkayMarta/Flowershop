package com.flowershop.menu.commands;

import com.flowershop.manager.BouquetManager;
import com.flowershop.model.Bouquet;
import com.flowershop.model.flowers.Rose;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import static org.mockito.Mockito.*;

public class LoadBouquetCommandTest {

    private BouquetManager bouquetManagerMock;
    private LoadBouquetCommand loadBouquetCommand;
    private Scanner scanner;

    // Перед кожним тестом створюємо новий об'єкт Scanner
    @BeforeEach
    void setUp() {
        bouquetManagerMock = Mockito.mock(BouquetManager.class);
        scanner = new Scanner("test_bouquet.txt\n");
        loadBouquetCommand = new LoadBouquetCommand(bouquetManagerMock);
        loadBouquetCommand.scanner = scanner; // Injecting the mock Scanner
    }

    @Test
    void execute_success() throws IOException {
        // Створюємо тимчасовий файл
        File tempFile = File.createTempFile("test_bouquet", ".txt");
        tempFile.deleteOnExit();

        // Записуємо деякі дані у файл
        try (PrintWriter writer = new PrintWriter(tempFile)) {
            writer.println("Квіти:\n" +
                    "Троянда,червоний,1,70.0,6.0,100.0,Німеччина,true,Фрідом\n" +
                    "Лілія,білий,3,25.0,16.0,80.0,Польща,2,сильний");
        }

        scanner = new Scanner(tempFile.getAbsolutePath() + "\n");
        loadBouquetCommand.scanner = scanner;

        // Перевіряємо, що метод setBouquet викликаний один раз
        when(bouquetManagerMock.getBouquet()).thenReturn(new Bouquet());

        loadBouquetCommand.execute(); // Викликаємо метод execute з Scanner, який повертає шлях до тимчасового файлу

        verify(bouquetManagerMock, times(1)).setBouquet(any(Bouquet.class)); // Перевіряємо, що метод setBouquet викликаний один раз
    }

    @Test
    void execute_fileNotFound() {
        scanner = new Scanner("non_existent_file.txt\n"); // Файл не існує
        loadBouquetCommand.scanner = scanner;

        when(bouquetManagerMock.getBouquet()).thenReturn(new Bouquet());

        loadBouquetCommand.execute(); // Викликаємо метод execute з Scanner, який повертає шлях до неіснуючого файлу

        verify(bouquetManagerMock, times(0)).setBouquet(any(Bouquet.class)); // Перевіряємо, що метод setBouquet не викликаний
    }


    @Test
    void execute_existingBouquet_noConfirmation() {

        Bouquet existingBouquet = new Bouquet();
        existingBouquet.addFlower(new Rose("red", 1, 50, 5, 10, "Ukraine", true, "Tea"));
        when(bouquetManagerMock.getBouquet()).thenReturn(existingBouquet);

        Scanner scannerNo = new Scanner("test.txt\nні\n"); // Відповідь користувача "ні"
        loadBouquetCommand.scanner = scannerNo;

        loadBouquetCommand.execute(); // Викликаємо метод execute з Scanner, який повертає шлях до тимчасового файлу

        verify(bouquetManagerMock, never()).setBouquet(any()); // Перевіряємо, що метод setBouquet не викликаний
    }

    @Test
    void execute_existingBouquet_confirmation() throws IOException {
        Bouquet existingBouquet = new Bouquet();
        existingBouquet.addFlower(new Rose("red", 1, 50, 5, 10, "Ukraine", true, "Tea"));
        when(bouquetManagerMock.getBouquet()).thenReturn(existingBouquet);

        // Створюємо тимчасовий файл
        File tempFile = File.createTempFile("test_bouquet", ".txt");
        tempFile.deleteOnExit();

        // Записуємо деякі дані у файл
        try (PrintWriter writer = new PrintWriter(tempFile)) {
            writer.println("Квіти:\n" +
                    "Троянда,червоний,1,70.0,6.0,100.0,Німеччина,true,Фрідом\n" +
                    "Лілія,білий,3,25.0,16.0,80.0,Польща,2,сильний");
        }
        Scanner scannerYes = new Scanner(tempFile.getAbsolutePath() + "\nтак\n");
        loadBouquetCommand.scanner = scannerYes;

        loadBouquetCommand.loadFromFile(tempFile.getAbsolutePath());

        verify(bouquetManagerMock, times(1)).setBouquet(any(Bouquet.class));
    }
}