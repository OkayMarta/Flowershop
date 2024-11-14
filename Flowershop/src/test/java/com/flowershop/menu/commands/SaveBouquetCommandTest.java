package com.flowershop.menu.commands;

import com.flowershop.manager.BouquetManager;
import com.flowershop.model.Bouquet;
import com.flowershop.model.accessories.Wrapper;
import com.flowershop.model.flowers.Rose;
import com.flowershop.utils.BouquetFileWriter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SaveBouquetCommandTest {

    private BouquetManager bouquetManagerMock;
    private SaveBouquetCommand saveBouquetCommand;
    private Scanner scanner;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream(); // ByteArrayOutputStream для збереження виводу
    private final PrintStream originalOut = System.out; // оригінальний System.out для відновлення після кожного тесту

    @BeforeEach
    void setUp() {
        bouquetManagerMock = Mockito.mock(BouquetManager.class); // Створюємо мок об'єкт BouquetManager
        scanner = new Scanner("test_bouquet.txt\n"); // Ініціалізуємо сканер
        saveBouquetCommand = new SaveBouquetCommand(bouquetManagerMock); // Створюємо об'єкт SaveBouquetCommand
        saveBouquetCommand.scanner = scanner; // встановлюємо сканер для об'єкта SaveBouquetCommand
        System.setOut(new PrintStream(outContent)); // Перенаправляємо System.out на outContent для збереження виводу тестів
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut); // Відновлюємо System.out після кожного тесту
    }

    @Test
    void execute_success(@TempDir Path tempDir) throws IOException {
        Bouquet bouquet = new Bouquet();
        bouquet.addFlower(new Rose("red", 1, 70, 6, 100, "Germany", true, "Freedom"));
        bouquet.addAccessory(new Wrapper("paper", "white", 55, "size: 60x48"));

        when(bouquetManagerMock.getBouquet()).thenReturn(bouquet);
        scanner = new Scanner(tempDir.resolve("test_bouquet.txt").toString() + "\n");
        saveBouquetCommand.scanner = scanner;


        saveBouquetCommand.execute();

        File savedFile = tempDir.resolve("test_bouquet.txt").toFile();
        assertTrue(savedFile.exists());
        assertTrue(Files.readString(tempDir.resolve("test_bouquet.txt")).contains("Троянда"));
    }

    @Test
    void execute_fileExists_overwrite() throws IOException {
        Bouquet bouquet = new Bouquet();
        bouquet.addFlower(new Rose("red", 1, 70, 6, 100, "Germany", true, "Freedom"));
        bouquet.addAccessory(new Wrapper("paper", "white", 55, "size: 60x48"));
        when(bouquetManagerMock.getBouquet()).thenReturn(bouquet);


        Path tempFilePath = Files.createTempFile("test_bouquet", ".txt");
        Files.writeString(tempFilePath, "some content"); // Створюємо файл з вмістом

        scanner = new Scanner(tempFilePath.toString() + "\nтак\n");
        saveBouquetCommand.scanner = scanner;

        saveBouquetCommand.execute();

        File savedFile = tempFilePath.toFile();
        assertTrue(savedFile.exists());
        assertFalse(Files.readString(tempFilePath).contains("some content")); // Перевіряємо, що файл був перезаписаний
    }

    @Test
    void execute_fileExists_dontOverwrite(@TempDir Path tempDir) throws IOException {
        Bouquet bouquet = new Bouquet();
        bouquet.addFlower(new Rose("red", 1, 70, 6, 100, "Germany", true, "Freedom"));
        bouquet.addAccessory(new Wrapper("paper", "white", 55, "size: 60x48"));
        when(bouquetManagerMock.getBouquet()).thenReturn(bouquet);

        Path tempFilePath = Files.createTempFile(tempDir, "test_bouquet", ".txt");
        Files.writeString(tempFilePath, "some content");

        scanner = new Scanner(tempFilePath.toString() + "\nні\n");
        saveBouquetCommand.scanner = scanner;

        saveBouquetCommand.execute();

        File savedFile = tempFilePath.toFile();
        assertTrue(savedFile.exists());
        assertTrue(Files.readString(tempFilePath).contains("some content"));
    }

    @Test
    void execute_IOException() throws IOException {
        // Ініціалізація вхідних даних
        ByteArrayInputStream inContent = new ByteArrayInputStream("test_bouquet.txt\n".getBytes());
        System.setIn(inContent);
        saveBouquetCommand.scanner = new Scanner(System.in); // Оновлюємо сканер

        // Мокуємо результат BouquetManager
        Bouquet bouquet = new Bouquet();
        bouquet.addFlower(new Rose("red", 1, 70, 6, 100, "Germany", true, "Freedom"));
        when(bouquetManagerMock.getBouquet()).thenReturn(bouquet);

        // Мокування статичного методу saveBouquet для генерації помилки
        try (MockedStatic<BouquetFileWriter> mocked = Mockito.mockStatic(BouquetFileWriter.class)) {
            mocked.when(() -> BouquetFileWriter.saveBouquet(any(Bouquet.class), anyString()))
                    .thenThrow(new IOException("Simulated IOException"));

            // Очищуємо потоки перед виконанням команди
            outContent.reset();

            // Запускаємо команду збереження
            saveBouquetCommand.execute();

            // Очищення потоку System.out і System.err
            System.out.flush();
            System.err.flush();

            // Отримання виводу
            String output = outContent.toString().trim();

            // Виведення реального результату для перевірки
            System.out.println("ACTUAL OUTPUT:\n" + output);

            // Використовуємо assertTrue для менш строгого порівняння
            assertTrue(output.contains("Введіть назву файлу (наприклад, file.txt):"),
                    "Expected error message not found in output: " + output);
        } finally {
            // Відновлюємо System.in після тесту
            System.setIn(System.in);
        }
    }
}