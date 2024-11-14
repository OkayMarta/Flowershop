package com.flowershop.model;

import com.flowershop.model.accessories.*;
import com.flowershop.model.flowers.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BouquetTest {

    @Test
    void testAddFlower() {
        Bouquet bouquet = new Bouquet();
        Rose rose = new Rose("червоний", 1, 70.0, 6.0, 100.0, "Україна", true, "Фрідом");
        bouquet.addFlower(rose);
        assertEquals(1, bouquet.getFlowers().size());
        assertEquals(rose, bouquet.getFlowers().get(0));
    }

    @Test
    void testAddAccessory() {
        Bouquet bouquet = new Bouquet();
        Wrapper wrapper = new Wrapper("папір", "білий", 55.0, "60 x 48");
        bouquet.addAccessory(wrapper);
        assertEquals(1, bouquet.getAccessories().size());
        assertEquals(wrapper, bouquet.getAccessories().get(0));


        // Перевірка на додавання однакового аксесуару
        Basket basket1 = new Basket("дерево", "коричневий", 180.0, "50 х 40");
        bouquet.addAccessory(basket1);
        assertEquals(2, bouquet.getAccessories().size());
        Basket basket2 = new Basket("метал", "чорний", 200.0, "60 х 50");
        bouquet.addAccessory(basket2); // Спроба додати кошик, коли він вже є
        assertEquals(2, bouquet.getAccessories().size()); // Розмір має залишитись той самий
    }

    @Test
    void testRemoveFlower() {
        Bouquet bouquet = new Bouquet();
        Rose rose = new Rose("червоний", 1, 70.0, 6.0, 100.0, "Україна", true, "Фрідом");
        bouquet.addFlower(rose);
        assertEquals(1, bouquet.getFlowers().size());

        bouquet.removeFlower(rose);
        assertEquals(0, bouquet.getFlowers().size());


        Lily lily = new Lily("білий", 3, 55.0, 12.0, 85.0, "Польща", 4, "сильний");
        bouquet.addFlower(rose);
        bouquet.addFlower(lily);
        assertEquals(2, bouquet.getFlowers().size());
        bouquet.removeFlower(lily);
        assertEquals(1, bouquet.getFlowers().size());
        assertEquals(rose, bouquet.getFlowers().get(0));
    }

    @Test
    void testRemoveAccessory() {
        Bouquet bouquet = new Bouquet();
        Wrapper wrapper = new Wrapper("папір", "білий", 55.0, "60 x 48");
        bouquet.addAccessory(wrapper);
        assertEquals(1, bouquet.getAccessories().size());
        bouquet.removeAccessory(wrapper);
        assertEquals(0, bouquet.getAccessories().size());

        Basket basket = new Basket("дерево", "коричневий", 180.0, "50 х 40");
        bouquet.addAccessory(wrapper);
        bouquet.addAccessory(basket);
        assertEquals(2, bouquet.getAccessories().size());
        bouquet.removeAccessory(basket);
        assertEquals(1, bouquet.getAccessories().size());
        assertEquals(wrapper, bouquet.getAccessories().get(0));

    }

    @Test
    void testSortFlowersByFreshness() {
        Bouquet bouquet = new Bouquet();
        Rose rose1 = new Rose("червоний", 1, 70.0, 6.0, 100.0, "Україна", true, "Фрідом");
        Lily lily = new Lily("білий", 3, 25.0, 16.0, 80.0, "Польща", 2, "сильний");
        Orchid orchid = new Orchid("білий", 1, 30.0, 7.0, 150.0, "Україна", "Каттлея", "червоний");
        Hydrangea hydrangea = new Hydrangea("синій", 4, 80.0, 30.0, 500.0, "Австрія", "кругле", "голубий");
        Rose rose2 = new Rose("жовтий", 5, 60.0, 5.0, 90.0, "Нідерланди", false, "Флорибунда");

        bouquet.addFlower(rose1);
        bouquet.addFlower(lily);
        bouquet.addFlower(orchid);
        bouquet.addFlower(hydrangea);
        bouquet.addFlower(rose2);

        bouquet.sortFlowersByFreshness();

        List<Flower> sortedFlowers = bouquet.getFlowers();
        assertEquals(rose2, sortedFlowers.get(0)); // Найбільша свіжість - rose2 (5)
        assertEquals(hydrangea, sortedFlowers.get(1)); // Наступна - hydrangea (4)
        assertEquals(lily, sortedFlowers.get(2));// Наступна - lily (3)
    }

    @Test
    void testFindFlowersByStemLength() {
        Bouquet bouquet = new Bouquet();
        Rose rose = new Rose("червоний", 1, 70.0, 6.0, 100.0, "Україна", true, "Фрідом");
        Lily lily = new Lily("білий", 3, 25.0, 16.0, 80.0, "Польща", 2, "сильний");

        bouquet.addFlower(rose);
        bouquet.addFlower(lily);

        List<Flower> foundFlowers = bouquet.findFlowersByStemLength(20, 60);
        assertEquals(1, foundFlowers.size());
        assertTrue(foundFlowers.contains(lily));
    }

    @Test
    void testCalculateTotalPrice() {
        Bouquet bouquet = new Bouquet();
        Rose rose = new Rose("червоний", 1, 70.0, 6.0, 100.0, "Україна", true, "Фрідом");
        Wrapper wrapper = new Wrapper("папір", "білий", 55.0, "60 x 48");

        bouquet.addFlower(rose);
        bouquet.addAccessory(wrapper);

        double totalPrice = bouquet.calculateTotalPrice();
        assertEquals(155.0, totalPrice);
    }

    @Test
    void testGetBouquetInfo() {
        Bouquet bouquet = new Bouquet();
        Rose rose = new Rose("червоний", 1, 70.0, 6.0, 100.0, "Україна", true, "Фрідом");
        Wrapper wrapper = new Wrapper("папір", "білий", 55.0, "60 x 48");

        bouquet.addFlower(rose);
        bouquet.addAccessory(wrapper);


        // Отримуємо дату та час створення букету
        LocalDateTime creationDate = bouquet.getCreationDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        String formattedDate = creationDate.format(formatter);

        String expectedInfoWithoutDate = "Інформація про букет:\n" +
                "Квіти:\n" +
                "- Троянда, червоний, свіжість: 1, довжина стебла: 70.0 см, розмір квітки: 6.0 см, ціна: 100.0 грн, країна походження: Україна, Наявність шипів: true, сорт: Фрідом\n" +
                "Аксесуари:\n" +
                "- Обгортка, матеріал: папір, колір: білий, ціна: 55.0 грн, 60 x 48\n" +
                "Дата створення: ";

        String expectedFullInfo = expectedInfoWithoutDate + formattedDate + "\n" + "Загальна вартість: 155.0 грн\n";

        assertEquals(expectedFullInfo, bouquet.getBouquetInfo());
    }
}