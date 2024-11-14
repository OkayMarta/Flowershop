package com.flowershop.model.accessories;

import com.flowershop.model.accessories.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WrapperTest {

    @Test
    void testGetMaterial() {
        Wrapper wrapper = new Wrapper("папір", "білий", 55.0, "60 x 48");
        assertEquals("папір", wrapper.getMaterial());
    }

    @Test
    void testSetMaterial() {
        Wrapper wrapper = new Wrapper("папір", "білий", 55.0, "60 x 48");
        wrapper.setMaterial("плівка");
        assertEquals("плівка", wrapper.getMaterial());
    }


    @Test
    void testGetColor() {
        Wrapper wrapper = new Wrapper("папір", "білий", 55.0, "60 x 48");
        assertEquals("білий", wrapper.getColor());
    }

    @Test
    void testSetColor() {
        Wrapper wrapper = new Wrapper("папір", "білий", 55.0, "60 x 48");
        wrapper.setColor("червоний");
        assertEquals("червоний", wrapper.getColor());
    }

    @Test
    void testGetPrice() {
        Wrapper wrapper = new Wrapper("папір", "білий", 55.0, "60 x 48");
        assertEquals(55.0, wrapper.getPrice());
    }

    @Test
    void testSetPrice() {
        Wrapper wrapper = new Wrapper("папір", "білий", 55.0, "60 x 48");
        wrapper.setPrice(75);
        assertEquals(75, wrapper.getPrice());
        assertThrows(IllegalArgumentException.class, () -> wrapper.setPrice(-5));
    }

    @Test
    void testGetUniqueCharacteristic() {
        Wrapper wrapper = new Wrapper("папір", "білий", 55.0, "60 x 48");
        assertEquals("60 x 48", wrapper.getUniqueCharacteristic());
    }

    @Test
    void testGetSize() {
        Wrapper wrapper = new Wrapper("папір", "білий", 55.0, "60 x 48");
        assertEquals("60 x 48", wrapper.getSize());
    }

    @Test
    void testSetSize() {
        Wrapper wrapper = new Wrapper("папір", "білий", 55.0, "60 x 48");
        wrapper.setSize("70 x 50");
        assertEquals("70 x 50", wrapper.getSize());
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