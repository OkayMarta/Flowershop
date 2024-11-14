package com.flowershop.manager;

import com.flowershop.model.Accessory;
import com.flowershop.model.Bouquet;
import com.flowershop.model.Flower;
import com.flowershop.model.accessories.Basket;
import com.flowershop.model.accessories.Postcard;
import com.flowershop.model.accessories.Ribbon;
import com.flowershop.model.accessories.Wrapper;
import com.flowershop.model.flowers.Hydrangea;
import com.flowershop.model.flowers.Lily;
import com.flowershop.model.flowers.Orchid;
import com.flowershop.model.flowers.Rose;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BouquetManagerTest {

    @Test
    void addFlower() {
        BouquetManager bouquetManager = new BouquetManager(); // створення екземпляру
        Flower rose = new Rose("червоний", 1, 70.0, 6.0, 100.0, "Німеччина", true, "Фрідом");
        bouquetManager.addFlower(rose);
        assertEquals(1, bouquetManager.getBouquet().getFlowers().size()); //перевірка чи к-сть квітів в букеті == 1
        assertEquals(rose, bouquetManager.getBouquet().getFlowers().get(0));
    }

    @Test
    void addAccessory() {
        BouquetManager bouquetManager = new BouquetManager();
        Accessory wrapper = new Wrapper("папір", "білий", 55.0, "розмір: 60 x 48");
        bouquetManager.addAccessory(wrapper);
        assertEquals(1, bouquetManager.getBouquet().getAccessories().size());
        assertEquals(wrapper, bouquetManager.getBouquet().getAccessories().get(0));
    }

    @Test
    void removeFlower() {
        BouquetManager bouquetManager = new BouquetManager();
        Flower lily = new Lily("білий", 3, 25.0, 16.0, 80.0, "Польща", 2, "сильний");
        bouquetManager.addFlower(lily);
        bouquetManager.removeFlower(lily);
        assertEquals(0, bouquetManager.getBouquet().getFlowers().size());
    }

    @Test
    void hasAccessoryOfType() {
        BouquetManager bouquetManager = new BouquetManager();
        bouquetManager.addAccessory(new Basket("дерево", "коричневий", 186.0, "розмір: 53 x 32"));
        assertTrue(bouquetManager.hasAccessoryOfType(Basket.class)); // перевірка чи є аксесуар типу Basket
        assertFalse(bouquetManager.hasAccessoryOfType(Ribbon.class)); // перевірка чи немає аксесуару типу Ribbon
    }

    @Test
    void removeAccessoryOfType() {
        BouquetManager bouquetManager = new BouquetManager();
        bouquetManager.addAccessory(new Postcard("папір", "бежевий", 45.0, "текст привітання: З днем народженням!"));
        bouquetManager.addAccessory(new Ribbon("поліестр", "червоний", 10.0, 3.0, 33.0));
        bouquetManager.removeAccessoryOfType(Postcard.class);
        assertEquals(1, bouquetManager.getBouquet().getAccessories().size()); // перевірка чи дві умови однакові
        assertTrue(bouquetManager.getBouquet().getAccessories().stream().anyMatch(a -> a instanceof Ribbon)); // перевірка чи умова істинна
        assertFalse(bouquetManager.getBouquet().getAccessories().stream().anyMatch(a -> a instanceof Postcard)); // перевірка чи умова хибна
    }

    @Test
    void testGetBouquet() {
        BouquetManager bouquetManager = new BouquetManager();
        Bouquet expectedBouquet = bouquetManager.getBouquet(); // Зберігаємо посилання на букет
        assertEquals(expectedBouquet, bouquetManager.getBouquet());
    }

    @Test
    void setBouquet() {
        BouquetManager bouquetManager = new BouquetManager();
        Bouquet newBouquet = new Bouquet();
        newBouquet.addFlower(new Hydrangea("синій", 4, 80.0, 30.0, 500.0, "Австрія", "кругле", "голубий"));
        bouquetManager.setBouquet(newBouquet);
        assertEquals(newBouquet, bouquetManager.getBouquet());
    }
}