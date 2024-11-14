package com.flowershop.model.accessories;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PostcardTest {

    @Test
    void testGetMaterial() {
        Postcard postcard = new Postcard("папір", "бежевий", 45.0, "З днем народженням!");
        assertEquals("папір", postcard.getMaterial());
    }

    @Test
    void testSetMaterial() {
        Postcard postcard = new Postcard("папір", "бежевий", 45.0, "З днем народженням!");
        postcard.setMaterial("картон");
        assertEquals("картон", postcard.getMaterial());
    }

    @Test
    void testGetColor() {
        Postcard postcard = new Postcard("папір", "бежевий", 45.0, "З днем народженням!");
        assertEquals("бежевий", postcard.getColor());
    }

    @Test
    void testSetColor() {
        Postcard postcard = new Postcard("папір", "бежевий", 45.0, "З днем народженням!");
        postcard.setColor("червоний");
        assertEquals("червоний", postcard.getColor());
    }

    @Test
    void testGetPrice() {
        Postcard postcard = new Postcard("папір", "бежевий", 45.0, "З днем народженням!");
        assertEquals(45.0, postcard.getPrice());
    }

    @Test
    void testSetPrice() {
        Postcard postcard = new Postcard("папір", "бежевий", 45.0, "З днем народженням!");
        postcard.setPrice(60.0);
        assertEquals(60.0, postcard.getPrice());
        assertThrows(IllegalArgumentException.class, () -> postcard.setPrice(-5)); // перевырка чи викидається певне виключення
    }

    @Test
    void testGetGreetingText() {
        Postcard postcard = new Postcard("папір", "бежевий", 45.0, "З днем народженням!");
        assertEquals("З днем народженням!", postcard.getGreetingText());
    }

    @Test
    void testSetGreetingText() {
        Postcard postcard = new Postcard("папір", "бежевий", 45.0, "З днем народженням!");
        postcard.setGreetingText("Вітаю!");
        assertEquals("Вітаю!", postcard.getGreetingText());
    }

    @Test
    void testGetUniqueCharacteristic() {
        Postcard postcard = new Postcard("папір", "бежевий", 45.0, "З днем народженням!");
        assertEquals("З днем народженням!", postcard.getUniqueCharacteristic());
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