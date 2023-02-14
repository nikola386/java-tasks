package com.nikola.bag;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayIteratorTest {
    ArrayIterator<Object> itr;
    @BeforeEach
    void setUp() {
        itr = new ArrayIterator<>(new Object[]{"item_1", "item_2"});
    }

    @Test
    @DisplayName("Ensure hasNext")
    void hasNext() {
        assertTrue(itr.hasNext());
    }

    @Test
    @DisplayName("Ensure next return next item")
    void next() {
        Object item = itr.next();
        assertEquals(item, "item_1");
    }

    @Test
    @DisplayName("Ensure an exception is thrown when item not found")
    void nextShouldThrow() {
        itr = new ArrayIterator<>(new Object[]{});
        assertThrows(NoSuchElementException.class, () -> itr.next());
    }
}
