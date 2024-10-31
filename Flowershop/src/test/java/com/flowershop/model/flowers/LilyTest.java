package com.flowershop.model.flowers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LilyTest {

    @Test
    void testGetName() {
        Lily lily = new Lily("білий", 3, 25.0, 16.0, 80.0, "Польща", 2, "сильний");
        assertEquals("Лілія", lily.getName());
    }

    @Test
    void testGetColor() {
        Lily lily = new Lily("білий", 3, 25.0, 16.0, 80.0, "Польща", 2, "сильний");
        assertEquals("білий", lily.getColor());
    }

    @Test
    void testSetColor() {
        Lily lily = new Lily("білий", 3, 25.0, 16.0, 80.0, "Польща", 2, "сильний");
        lily.setColor("рожевий");
        assertEquals("рожевий", lily.getColor());
    }

    @Test
    void testGetFreshnessLevel() {
        Lily lily = new Lily("білий", 3, 25.0, 16.0, 80.0, "Польща", 2, "сильний");
        assertEquals(3, lily.getFreshnessLevel());
    }

    @Test
    void testSetFreshnessLevel() {
        Lily lily = new Lily("білий", 3, 25.0, 16.0, 80.0, "Польща", 2, "сильний");
        lily.setFreshnessLevel(1);
        assertEquals(1, lily.getFreshnessLevel());

        assertThrows(IllegalArgumentException.class, () -> lily.setFreshnessLevel(0));
    }

    @Test
    void testGetStemLength() {
        Lily lily = new Lily("білий", 3, 25.0, 16.0, 80.0, "Польща", 2, "сильний");
        assertEquals(25.0, lily.getStemLength());
    }

    @Test
    void testSetStemLength() {
        Lily lily = new Lily("білий", 3, 25.0, 16.0, 80.0, "Польща", 2, "сильний");
        lily.setStemLength(30);
        assertEquals(30, lily.getStemLength());
        assertThrows(IllegalArgumentException.class, () -> lily.setStemLength(-5));
    }

    @Test
    void testGetFlowerSize() {
        Lily lily = new Lily("білий", 3, 25.0, 16.0, 80.0, "Польща", 2, "сильний");
        assertEquals(16.0, lily.getFlowerSize());
    }

    @Test
    void testSetFlowerSize() {
        Lily lily = new Lily("білий", 3, 25.0, 16.0, 80.0, "Польща", 2, "сильний");
        lily.setFlowerSize(10);
        assertEquals(10, lily.getFlowerSize());
        assertThrows(IllegalArgumentException.class, () -> lily.setFlowerSize(-1));
    }

    @Test
    void testGetPrice() {
        Lily lily = new Lily("білий", 3, 25.0, 16.0, 80.0, "Польща", 2, "сильний");
        assertEquals(80.0, lily.getPrice());
    }

    @Test
    void testSetPrice() {
        Lily lily = new Lily("білий", 3, 25.0, 16.0, 80.0, "Польща", 2, "сильний");
        lily.setPrice(90);
        assertEquals(90, lily.getPrice());
        assertThrows(IllegalArgumentException.class, () -> lily.setPrice(-1));

    }

    @Test
    void testGetOriginCountry() {
        Lily lily = new Lily("білий", 3, 25.0, 16.0, 80.0, "Польща", 2, "сильний");
        assertEquals("Польща", lily.getOriginCountry());
    }

    @Test
    void testSetOriginCountry() {
        Lily lily = new Lily("білий", 3, 25.0, 16.0, 80.0, "Польща", 2, "сильний");
        lily.setOriginCountry("Germany");
        assertEquals("Germany", lily.getOriginCountry());
    }

    @Test
    void testGetNumberOfFlowersOnStem() {
        Lily lily = new Lily("білий", 3, 25.0, 16.0, 80.0, "Польща", 2, "сильний");
        assertEquals(2, lily.getNumberOfFlowersOnStem());
    }


    @Test
    void testSetNumberOfFlowersOnStem() {
        Lily lily = new Lily("білий", 3, 25.0, 16.0, 80.0, "Польща", 2, "сильний");
        lily.setNumberOfFlowersOnStem(5);
        assertEquals(5, lily.getNumberOfFlowersOnStem());

        assertThrows(IllegalArgumentException.class, () -> lily.setNumberOfFlowersOnStem(-1));
    }

    @Test
    void testGetFragrance() {
        Lily lily = new Lily("білий", 3, 25.0, 16.0, 80.0, "Польща", 2, "сильний");
        assertEquals("сильний", lily.getFragrance());
    }

    @Test
    void testSetFragrance() {
        Lily lily = new Lily("білий", 3, 25.0, 16.0, 80.0, "Польща", 2, "сильний");
        lily.setFragrance("легкий");
        assertEquals("легкий", lily.getFragrance());
    }

    @Test
    void testGetUniqueCharacteristic() {
        Lily lily = new Lily("білий", 3, 25.0, 16.0, 80.0, "Польща", 2, "сильний");
        assertEquals("Кількість квіток на стеблі: 2, аромат: сильний", lily.getUniqueCharacteristic());
    }
}