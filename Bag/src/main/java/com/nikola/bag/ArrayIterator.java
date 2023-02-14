package com.nikola.bag;

import java.util.Iterator;
import java.util.NoSuchElementException;

class ArrayIterator<E> implements Iterator<E> {
    private int index;
    private final Object[] elements;

    public ArrayIterator(final Object[] elements) {
        index = 0;
        this.elements = elements;
    }

    public boolean hasNext() {
        return index < elements.length;
    }

    public E next() {
        if (hasNext()) {
            return (E) elements[index++];
        }
        throw new NoSuchElementException();
    }
}