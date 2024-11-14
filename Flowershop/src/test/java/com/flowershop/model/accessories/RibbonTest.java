package com.flowershop.model.accessories;

import com.flowershop.model.accessories.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RibbonTest {

    @Test
    void testGetMaterial() {
        Ribbon ribbon = new Ribbon("атлас", "червоний", 10.0, 3.0, 33.0);
        assertEquals("атлас", ribbon.getMaterial());
    }

    @Test
    void testSetMaterial() {
        Ribbon ribbon = new Ribbon("атлас", "червоний", 10.0, 3.0, 33.0);
        ribbon.setMaterial("шовк");
        assertEquals("шовк", ribbon.getMaterial());
    }

    @Test
    void testGetColor() {
        Ribbon ribbon = new Ribbon("атлас", "червоний", 10.0, 3.0, 33.0);
        assertEquals("червоний", ribbon.getColor());
    }

    @Test
    void testSetColor() {
        Ribbon ribbon = new Ribbon("атлас", "червоний", 10.0, 3.0, 33.0);
        ribbon.setColor("синій");
        assertEquals("синій", ribbon.getColor());
    }

    @Test
    void testGetPrice() {
        Ribbon ribbon = new Ribbon("атлас", "червоний", 10.0, 3.0, 33.0);
        assertEquals(10.0, ribbon.getPrice());
    }

    @Test
    void testSetPrice() {
        Ribbon ribbon = new Ribbon("атлас", "червоний", 10.0, 3.0, 33.0);
        ribbon.setPrice(15.0);
        assertEquals(15.0, ribbon.getPrice());
        assertThrows(IllegalArgumentException.class, () -> ribbon.setPrice(-8));
    }

    @Test
    void testGetWidth() {
        Ribbon ribbon = new Ribbon("атлас", "червоний", 10.0, 3.0, 33.0);
        assertEquals(3.0, ribbon.getWidth());
    }

    @Test
    void testSetWidth() {
        Ribbon ribbon = new Ribbon("атлас", "червоний", 10.0, 3.0, 33.0);
        ribbon.setWidth(5.0);
        assertEquals(5.0, ribbon.getWidth());
        assertThrows(IllegalArgumentException.class, () -> ribbon.setWidth(-1));

    }

    @Test
    void testGetLength() {
        Ribbon ribbon = new Ribbon("атлас", "червоний", 10.0, 3.0, 33.0);
        assertEquals(33.0, ribbon.getLength());
    }

    @Test
    void testSetLength() {
        Ribbon ribbon = new Ribbon("атлас", "червоний", 10.0, 3.0, 33.0);
        ribbon.setLength(40.0);
        assertEquals(40.0, ribbon.getLength());
        assertThrows(IllegalArgumentException.class, () -> ribbon.setLength(-7));

    }

    @Test
    void testGetUniqueCharacteristic() {
        Ribbon ribbon = new Ribbon("атлас", "червоний", 10.0, 3.0, 33.0);
        assertEquals("ширина: 3.0 см, довжина: 33.0 см", ribbon.getUniqueCharacteristic());
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