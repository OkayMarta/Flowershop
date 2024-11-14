package com.flowershop.model.flowers;

import com.flowershop.model.flowers.Orchid;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

    class OrchidTest {

    @Test
    void testGetName() {
        Orchid orchid = new Orchid("білий", 1, 30.0, 7.0, 150.0, "Україна", "Каттлея", "червоний");
        assertEquals("Орхідея", orchid.getName());
    }

    @Test
    void testGetColor() {
        Orchid orchid = new Orchid("білий", 1, 30.0, 7.0, 150.0, "Україна", "Каттлея", "червоний");
        assertEquals("білий", orchid.getColor());
    }

    @Test
    void testSetColor() {
        Orchid orchid = new Orchid("білий", 1, 30.0, 7.0, 150.0, "Україна", "Каттлея", "червоний");
        orchid.setColor("рожевий");
        assertEquals("рожевий", orchid.getColor());
    }

    @Test
    void testGetFreshnessLevel() {
        Orchid orchid = new Orchid("білий", 1, 30.0, 7.0, 150.0, "Україна", "Каттлея", "червоний");
        assertEquals(1, orchid.getFreshnessLevel());
    }

    @Test
    void testSetFreshnessLevel() {
        Orchid orchid = new Orchid("білий", 1, 30.0, 7.0, 150.0, "Україна", "Каттлея", "червоний");
        orchid.setFreshnessLevel(3);
        assertEquals(3, orchid.getFreshnessLevel());
        assertThrows(IllegalArgumentException.class, () -> orchid.setFreshnessLevel(0));
    }


    @Test
    void testGetStemLength() {
        Orchid orchid = new Orchid("білий", 1, 30.0, 7.0, 150.0, "Україна", "Каттлея", "червоний");
        assertEquals(30.0, orchid.getStemLength());
    }

    @Test
    void testSetStemLength() {
        Orchid orchid = new Orchid("білий", 1, 30.0, 7.0, 150.0, "Україна", "Каттлея", "червоний");
        orchid.setStemLength(40);
        assertEquals(40, orchid.getStemLength());
        assertThrows(IllegalArgumentException.class, () -> orchid.setStemLength(-5));
    }

    @Test
    void testGetFlowerSize() {
        Orchid orchid = new Orchid("білий", 1, 30.0, 7.0, 150.0, "Україна", "Каттлея", "червоний");
        assertEquals(7.0, orchid.getFlowerSize());
    }

    @Test
    void testSetFlowerSize() {
        Orchid orchid = new Orchid("білий", 1, 30.0, 7.0, 150.0, "Україна", "Каттлея", "червоний");
        orchid.setFlowerSize(15);
        assertEquals(15, orchid.getFlowerSize());
        assertThrows(IllegalArgumentException.class, () -> orchid.setFlowerSize(0));
    }


    @Test
    void testGetPrice() {
        Orchid orchid = new Orchid("білий", 1, 30.0, 7.0, 150.0, "Україна", "Каттлея", "червоний");
        assertEquals(150.0, orchid.getPrice());
    }


    @Test
    void testSetPrice() {
        Orchid orchid = new Orchid("білий", 1, 30.0, 7.0, 150.0, "Україна", "Каттлея", "червоний");
        orchid.setPrice(100);
        assertEquals(100, orchid.getPrice());
        assertThrows(IllegalArgumentException.class, () -> orchid.setPrice(-8));
    }



    @Test
    void testGetOriginCountry() {
        Orchid orchid = new Orchid("білий", 1, 30.0, 7.0, 150.0, "Україна", "Каттлея", "червоний");
        assertEquals("Україна", orchid.getOriginCountry());
    }

    @Test
    void testSetOriginCountry() {
        Orchid orchid = new Orchid("білий", 1, 30.0, 7.0, 150.0, "Україна", "Каттлея", "червоний");
        orchid.setOriginCountry("Germany");
        assertEquals("Germany", orchid.getOriginCountry());
    }

    @Test
    void testGetOrchidType() {
        Orchid orchid = new Orchid("білий", 1, 30.0, 7.0, 150.0, "Україна", "Каттлея", "червоний");
        assertEquals("Каттлея", orchid.getOrchidType());
    }

    @Test
    void testSetOrchidType() {
        Orchid orchid = new Orchid("білий", 1, 30.0, 7.0, 150.0, "Україна", "Каттлея", "червоний");
        orchid.setOrchidType("Фаленопсис");
        assertEquals("Фаленопсис", orchid.getOrchidType());
    }

    @Test
    void testGetLipColor() {
        Orchid orchid = new Orchid("білий", 1, 30.0, 7.0, 150.0, "Україна", "Каттлея", "червоний");
        assertEquals("червоний", orchid.getLipColor());
    }

    @Test
    void testSetLipColor() {
        Orchid orchid = new Orchid("білий", 1, 30.0, 7.0, 150.0, "Україна", "Каттлея", "червоний");
        orchid.setLipColor("рожевий");
        assertEquals("рожевий", orchid.getLipColor());
    }

    @Test
    void testGetUniqueCharacteristic() {
        Orchid orchid = new Orchid("білий", 1, 30.0, 7.0, 150.0, "Україна", "Каттлея", "червоний");
        assertEquals("Тип орхідеї: Каттлея, колір губи: червоний", orchid.getUniqueCharacteristic());
    }

}