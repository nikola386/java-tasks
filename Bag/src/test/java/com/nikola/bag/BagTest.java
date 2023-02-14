package com.nikola.bag;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class BagTest {
    Bag<Object> collection;
    @BeforeEach
    void setUp() {
        collection = new Bag<>(10);
    }

    @Test
    @DisplayName("Ensure an exception is thrown when initialised with negative capacity")
    void initialiseWithNegativeSize() {
        assertThrows(IllegalArgumentException.class, () -> collection = new Bag<>(-1));
    }

    @Test
    @DisplayName("Ensure random object is grabbed from the bag")
    void grabRandom() {
        collection.add("item");
        Object item = collection.grab();
        assertEquals("item", item);
    }

    @Test
    @DisplayName("Ensure grab throws an exception when the bag is empty")
    void grabRandomShouldThrowExceptionWhenEmpty() {
        assertThrows(IllegalStateException.class, () -> collection.grab());
    }

    @Test
    @DisplayName("Ensure object at specified index is grabbed from the bag")
    void grabByIndex() {
        for (int i = 0; i < 10; i++) {
            collection.add("item_" + i);
        }

        Object item = collection.grab(4);
        assertEquals("item_4", item);
    }

    @Test
    @DisplayName("Ensure grab by index throws an exception when the bag is empty")
    void grabByIndexShouldThrowExceptionWhenEmpty() {
        assertThrows(IllegalStateException.class, () -> collection.grab(0));
    }

    @Test
    @DisplayName("Ensure a new object is added to the bag")
    void add() {
        assertTrue(collection.add("item"));
    }

    @Test
    @DisplayName("Ensure false is returned when the bag is full")
    void addWhenFull() {
        for (int i = 0; i < 10; i++) {
            collection.add("item_" + i);
        }
        assertFalse(collection.add("item"));
    }

    @Test
    @DisplayName("Ensure an object is removed from the bag")
    void remove() {
        collection.add("item");
        assertTrue(collection.remove("item"));
    }

    @Test
    @DisplayName("Ensure an exception is thrown when the bag is empty")
    void removeShouldThrow() {
        assertThrows(IllegalStateException.class, () -> collection.remove("missing_item"));
    }

    @Test
    @DisplayName("Ensure a false is returned when the item is missing")
    void removeShouldReturnFalse() {
        collection.add("item");
        assertFalse(collection.remove("missing_item"));
    }

    @Test
    @DisplayName("Ensure an object on specified index is removed from the bag")
    void removeByIndex() {
        for (int i = 0; i < 10; i++) {
            collection.add("item_" + i);
        }

        assertTrue(collection.remove(4));
    }

    @Test
    @DisplayName("Ensure an exception is thrown when the bag is empty")
    void removeByIndexShouldThrowWhenEmpty() {
        assertThrows(IllegalStateException.class, () -> collection.remove(0));
    }

    @Test
    @DisplayName("Ensure an exception is thrown when the index is negative")
    void removeByIndexShouldThrowWhenIndexNegative() {
        assertThrows(IllegalArgumentException.class, () -> collection.remove(-1));
    }

    @Test
    @DisplayName("Ensure the bag contains the specified object")
    void contains() {
        for (int i = 0; i < 10; i++) {
            collection.add("item_" + i);
        }

        assertTrue(collection.contains("item_4"));
    }

    @Test
    @DisplayName("Ensure the bag does not contain an item")
    void containsNothing() {
        assertFalse(collection.contains("missing_item"));
    }

    @Test
    @DisplayName("Ensure a bag is empty")
    void isEmpty() {
        assertTrue(collection.isEmpty());
    }

    @Test
    @DisplayName("Ensure the bag is cleared")
    void clear() {
        collection.add("item");
        collection.clear();
        assertTrue(collection.isEmpty());
    }

    @Test
    @DisplayName("Ensure the correct size of the bag")
    void size() {
        collection.add("item");
        assertEquals(collection.size(), 1);
    }

    @Test
    @DisplayName("Ensure the bag is converted to an array")
    void toArray() {
        collection.add("item");
        Object[] array = collection.toArray();
        assertTrue(array.getClass().isArray());
    }

    @Test
    @DisplayName("Ensure iterator works correctly")
    void iterator() {
        for (int i = 0; i < 10; i++) {
            collection.add("item_" + i);
        }

        Iterator<Object> itr = collection.iterator();

        int items = 0;
        while(itr.hasNext()) {
            Object item = itr.next();
            assertEquals("item_" + items, item);
            items++;
        }

        assertEquals(items, 10);
        assertThrows(NoSuchElementException.class, itr::next);
    }

    @Test
    @DisplayName("Ensure toString returns correct string")
    void testToString() {
        assertEquals("", collection.toString());
    }
}