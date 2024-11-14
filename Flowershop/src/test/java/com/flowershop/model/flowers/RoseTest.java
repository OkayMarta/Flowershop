package com.flowershop.model.flowers;

import com.flowershop.model.flowers.Rose;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RoseTest {

    @Test
    void testGetName() {
        Rose rose = new Rose("червоний", 1, 70.0, 6.0, 100.0, "Україна", true, "Фрідом");
        assertEquals("Троянда", rose.getName());
    }

    @Test
    void testGetColor() {
        Rose rose = new Rose("червоний", 1, 70.0, 6.0, 100.0, "Україна", true, "Фрідом");
        assertEquals("червоний", rose.getColor());
    }

    @Test
    void testSetColor() {
        Rose rose = new Rose("червоний", 1, 70.0, 6.0, 100.0, "Україна", true, "Фрідом");
        rose.setColor("білий");
        assertEquals("білий", rose.getColor());
    }

    @Test
    void testGetFreshnessLevel() {
        Rose rose = new Rose("червоний", 1, 70.0, 6.0, 100.0, "Україна", true, "Фрідом");
        assertEquals(1, rose.getFreshnessLevel());
    }

    @Test
    void setFreshnessLevel() {
        Rose rose = new Rose("червоний", 1, 70.0, 6.0, 100.0, "Україна", true, "Фрідом");
        rose.setFreshnessLevel(3);
        assertEquals(3, rose.getFreshnessLevel());

        assertThrows(IllegalArgumentException.class, () -> rose.setFreshnessLevel(7));
        assertThrows(IllegalArgumentException.class, () -> rose.setFreshnessLevel(0));
    }

    @Test
    void setStemLength() {
        Rose rose = new Rose("червоний", 1, 70.0, 6.0, 100.0, "Україна", true, "Фрідом");
        rose.setStemLength(55);
        assertEquals(55, rose.getStemLength());
        assertThrows(IllegalArgumentException.class, () -> rose.setStemLength(-5));
    }


    @Test
    void setFlowerSize() {
        Rose rose = new Rose("червоний", 1, 70.0, 6.0, 100.0, "Україна", true, "Фрідом");
        rose.setFlowerSize(7);
        assertEquals(7, rose.getFlowerSize());
        assertThrows(IllegalArgumentException.class, () -> rose.setFlowerSize(0));

    }

    @Test
    void setOriginCountry() {
        Rose rose = new Rose("червоний", 1, 70.0, 6.0, 100.0, "Україна", true, "Фрідом");
        rose.setOriginCountry("Німеччина");
        assertEquals("Німеччина", rose.getOriginCountry());
    }

    @Test
    void testIsHasThorns() {
        Rose rose = new Rose("червоний", 1, 70.0, 6.0, 100.0, "Україна", true, "Фрідом");
        assertTrue(rose.isHasThorns());

        rose.setHasThorns(false);  // Тестуємо сеттер
        assertFalse(rose.isHasThorns());
    }

    @Test
    void testSetHasThorns() {
        Rose rose = new Rose("червоний", 1, 70.0, 6.0, 100.0, "Україна", true, "Фрідом");
        rose.setHasThorns(false);
        assertFalse(rose.isHasThorns());
    }

    @Test
    void testGetRoseType() {
        Rose rose = new Rose("червоний", 1, 70.0, 6.0, 100.0, "Україна", true, "Фрідом");
        assertEquals("Фрідом", rose.getRoseType());
    }

    @Test
    void testSetRoseType() {
        Rose rose = new Rose("червоний", 1, 70.0, 6.0, 100.0, "Україна", true, "Фрідом");
        rose.setRoseType("Сантана");
        assertEquals("Сантана", rose.getRoseType());
    }

    @Test
    void testGetUniqueCharacteristic() {
        Rose rose = new Rose("червоний", 1, 70.0, 6.0, 100.0, "Україна", true, "Фрідом");
        assertEquals("Наявність шипів: true, сорт: Фрідом", rose.getUniqueCharacteristic());
    }

    @Test
    void testSetFreshnessLevel_boundaryValues() {
        Rose rose = new Rose("red", 1, 70.0, 6.0, 100.0, "Ukraine", true, "Freedom");
        rose.setFreshnessLevel(1);
        assertEquals(1, rose.getFreshnessLevel());
        rose.setFreshnessLevel(5);
        assertEquals(5, rose.getFreshnessLevel());
    }

    @Test
    void testSetFreshnessLevel_invalidValues() {
        Rose rose = new Rose("red", 1, 70.0, 6.0, 100.0, "Ukraine", true, "Freedom");
        assertThrows(IllegalArgumentException.class, () -> rose.setFreshnessLevel(0));
        assertThrows(IllegalArgumentException.class, () -> rose.setFreshnessLevel(6));
        assertThrows(IllegalArgumentException.class, () -> rose.setFreshnessLevel(-1));
    }

    @Test
    void testSetStemLength_boundaryValues() {
        Rose rose = new Rose("red", 1, 70.0, 6.0, 100.0, "Ukraine", true, "Freedom");
        rose.setStemLength(Double.MIN_VALUE); // Перевірка на дуже мале значення
        rose.setStemLength(Double.MAX_VALUE); // Перевірка на дуже велике значення
    }

    @Test
    void testSetStemLength_invalidValue() {
        Rose rose = new Rose("red", 1, 70.0, 6.0, 100.0, "Ukraine", true, "Freedom");
        assertThrows(IllegalArgumentException.class, () -> rose.setStemLength(-5));
    }


    @Test
    void testSetFlowerSize_boundaryValues() {
        Rose rose = new Rose("red", 1, 70.0, 6.0, 100.0, "Ukraine", true, "Freedom");
        rose.setFlowerSize(Double.MIN_VALUE);
        rose.setFlowerSize(Double.MAX_VALUE);
    }

    @Test
    void testSetFlowerSize_invalidValue() {
        Rose rose = new Rose("red", 1, 70.0, 6.0, 100.0, "Ukraine", true, "Freedom");
        assertThrows(IllegalArgumentException.class, () -> rose.setFlowerSize(-1));
    }

    @Test
    void testConstructor_nullValues() {
        assertDoesNotThrow(() -> new Rose(null, 1, 70, 6, 100, null, true, null));
    }
}