package com.flowershop.model.accessories;

import com.flowershop.model.accessories.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BasketTest {

    @Test
    void testGetMaterial() {
        Basket basket = new Basket("дерево", "коричневий", 186.0, "53 x 32");
        assertEquals("дерево", basket.getMaterial());
    }

    @Test
    void testSetMaterial() {
        Basket basket = new Basket("дерево", "коричневий", 186.0, "53 x 32");
        basket.setMaterial("пластик");
        assertEquals("пластик", basket.getMaterial());
    }

    @Test
    void testGetColor() {
        Basket basket = new Basket("дерево", "коричневий", 186.0, "53 x 32");
        assertEquals("коричневий", basket.getColor());
    }

    @Test
    void testSetColor() {
        Basket basket = new Basket("дерево", "коричневий", 186.0, "53 x 32");
        basket.setColor("білий");
        assertEquals("білий", basket.getColor());
    }

    @Test
    void testGetPrice() {
        Basket basket = new Basket("дерево", "коричневий", 186.0, "53 x 32");
        assertEquals(186.0, basket.getPrice());
    }

    @Test
    void testSetPrice() {
        Basket basket = new Basket("дерево", "коричневий", 186.0, "53 x 32");
        basket.setPrice(200.0);
        assertEquals(200.0, basket.getPrice());

        assertThrows(IllegalArgumentException.class, ()-> basket.setPrice(-1));
    }

    @Test
    void testGetUniqueCharacteristic() {
        Basket basket = new Basket("дерево", "коричневий", 186.0, "53 x 32");
        assertEquals("53 x 32", basket.getUniqueCharacteristic());
    }

    @Test
    void getSize() {
        Basket basket = new Basket("дерево", "коричневий", 186.0, "53 x 32");
        assertEquals("53 x 32", basket.getSize());
        basket.setSize("50 x 40");
        assertEquals("50 x 40", basket.getSize());
    }

    @Test
    void testUniqueCharacteristicUniqueness() {
        Basket basket = new Basket("дерево", "коричневий", 186.0, "53 x 32");
        Postcard postcard = new Postcard("папір", "бежевий", 45.0, "З днем народженням!");
        Ribbon ribbon = new Ribbon("атлас", "червоний", 10.0, 3.0, 33.0);
        Wrapper wrapper = new Wrapper("папір", "білий", 55.0, "60 x 48");

        assertNotEquals(basket.getUniqueCharacteristic(), postcard.getUniqueCharacteristic());
        assertNotEquals(basket.getUniqueCharacteristic(), ribbon.getUniqueCharacteristic());
        assertNotEquals(basket.getUniqueCharacteristic(), wrapper.getUniqueCharacteristic());
        assertNotEquals(postcard.getUniqueCharacteristic(), ribbon.getUniqueCharacteristic());
        assertNotEquals(postcard.getUniqueCharacteristic(), wrapper.getUniqueCharacteristic());
        assertNotEquals(ribbon.getUniqueCharacteristic(), wrapper.getUniqueCharacteristic());
    }
}