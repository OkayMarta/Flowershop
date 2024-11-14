package com.flowershop.model.flowers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HydrangeaTest {

    @Test
    void testGetName() {
        Hydrangea hydrangea = new Hydrangea("синій", 4, 80.0, 30.0, 500.0, "Австрія", "кругле", "голубий");
        assertEquals("Гортензія", hydrangea.getName());
    }

    @Test
    void testGetColor() {
        Hydrangea hydrangea = new Hydrangea("синій", 4, 80.0, 30.0, 500.0, "Австрія", "кругле", "голубий");
        assertEquals("синій", hydrangea.getColor());
    }

    @Test
    void testSetColor() {
        Hydrangea hydrangea = new Hydrangea("синій", 4, 80.0, 30.0, 500.0, "Австрія", "кругле", "голубий");
        hydrangea.setColor("рожевий");
        assertEquals("рожевий", hydrangea.getColor());
    }

    @Test
    void testGetFreshnessLevel() {
        Hydrangea hydrangea = new Hydrangea("синій", 4, 80.0, 30.0, 500.0, "Австрія", "кругле", "голубий");
        assertEquals(4, hydrangea.getFreshnessLevel());
    }

    @Test
    void testSetFreshnessLevel() {
        Hydrangea hydrangea = new Hydrangea("синій", 4, 80.0, 30.0, 500.0, "Австрія", "кругле", "голубий");
        hydrangea.setFreshnessLevel(1);
        assertEquals(1, hydrangea.getFreshnessLevel());
        assertThrows(IllegalArgumentException.class, () -> hydrangea.setFreshnessLevel(0));
    }

    @Test
    void testGetStemLength() {
        Hydrangea hydrangea = new Hydrangea("синій", 4, 80.0, 30.0, 500.0, "Австрія", "кругле", "голубий");
        assertEquals(80.0, hydrangea.getStemLength());
    }

    @Test
    void testSetStemLength() {
        Hydrangea hydrangea = new Hydrangea("синій", 4, 80.0, 30.0, 500.0, "Австрія", "кругле", "голубий");
        hydrangea.setStemLength(40);
        assertEquals(40, hydrangea.getStemLength());
        assertThrows(IllegalArgumentException.class, () -> hydrangea.setStemLength(-5));
    }

    @Test
    void testGetFlowerSize() {
        Hydrangea hydrangea = new Hydrangea("синій", 4, 80.0, 30.0, 500.0, "Австрія", "кругле", "голубий");
        assertEquals(30.0, hydrangea.getFlowerSize());
    }

    @Test
    void testSetFlowerSize() {
        Hydrangea hydrangea = new Hydrangea("синій", 4, 80.0, 30.0, 500.0, "Австрія", "кругле", "голубий");
        hydrangea.setFlowerSize(10);
        assertEquals(10, hydrangea.getFlowerSize());
        assertThrows(IllegalArgumentException.class, () -> hydrangea.setFlowerSize(-4));

    }

    @Test
    void testGetPrice() {
        Hydrangea hydrangea = new Hydrangea("синій", 4, 80.0, 30.0, 500.0, "Австрія", "кругле", "голубий");
        assertEquals(500.0, hydrangea.getPrice());
    }

    @Test
    void testSetPrice() {
        Hydrangea hydrangea = new Hydrangea("синій", 4, 80.0, 30.0, 500.0, "Австрія", "кругле", "голубий");
        hydrangea.setPrice(250);
        assertEquals(250, hydrangea.getPrice());
        assertThrows(IllegalArgumentException.class, ()-> hydrangea.setPrice(-5));
    }

    @Test
    void testGetOriginCountry() {
        Hydrangea hydrangea = new Hydrangea("синій", 4, 80.0, 30.0, 500.0, "Австрія", "кругле", "голубий");
        assertEquals("Австрія", hydrangea.getOriginCountry());
    }

    @Test
    void testSetOriginCountry() {
        Hydrangea hydrangea = new Hydrangea("синій", 4, 80.0, 30.0, 500.0, "Австрія", "кругле", "голубий");
        hydrangea.setOriginCountry("Germany");
        assertEquals("Germany", hydrangea.getOriginCountry());
    }

    @Test
    void testGetInflorescenceType() {
        Hydrangea hydrangea = new Hydrangea("синій", 4, 80.0, 30.0, 500.0, "Австрія", "кругле", "голубий");
        assertEquals("кругле", hydrangea.getInflorescenceType());
    }

    @Test
    void testSetInflorescenceType() {
        Hydrangea hydrangea = new Hydrangea("синій", 4, 80.0, 30.0, 500.0, "Австрія", "кругле", "голубий");
        hydrangea.setInflorescenceType("плоске");
        assertEquals("плоске", hydrangea.getInflorescenceType());
    }


    @Test
    void testGetInflorescenceColor() {
        Hydrangea hydrangea = new Hydrangea("синій", 4, 80.0, 30.0, 500.0, "Австрія", "кругле", "голубий");
        assertEquals("голубий", hydrangea.getInflorescenceColor());
    }


    @Test
    void testSetInflorescenceColor() {
        Hydrangea hydrangea = new Hydrangea("синій", 4, 80.0, 30.0, 500.0, "Австрія", "кругле", "голубий");
        hydrangea.setInflorescenceColor("рожевий");
        assertEquals("рожевий", hydrangea.getInflorescenceColor());
    }

    @Test
    void testGetUniqueCharacteristic() {
        Hydrangea hydrangea = new Hydrangea("синій", 4, 80.0, 30.0, 500.0, "Австрія", "кругле", "голубий");
        assertEquals("Тип суцвіття: кругле, колір суцвіття: голубий", hydrangea.getUniqueCharacteristic());
    }
}