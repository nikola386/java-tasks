
import java.util.Iterator;

public class Bag<E> implements Iterable<E> {
    private final Object[] store;
    private final int capacity;
    private int size;

    public Bag(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("The capacity is negative: " + capacity);
        }

        this.store = new Object[capacity];
        this.capacity = capacity;
        this.size = 0;
    }

    public E grab() {
        int i;
        if (size == 0) {
            throw new IllegalStateException("Bag size is zero");
        }

        i = (int) (Math.random() * size);
        return (E) store[i];
    }

    public E grab(int index) {
        if (size == 0) {
            throw new IllegalStateException("Bag size is zero");
        }

        return (E) store[index];
    }

    public boolean add(E item) {
        if (size >= capacity) {
            return false;
        }

        store[size] = item;
        size++;

        return true;
    }

    public boolean remove(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("index is negative: " + index);
        }

        if (size == 0) {
            throw new IllegalStateException("Bag size is zero");
        }

        size--;
        store[index] = store[size];
        store[size] = null;
        return true;
    }

    public boolean remove(E item) {
        if (size == 0) {
            throw new IllegalStateException("Bag size is zero");
        }

        int index = indexOf(item);

        if (index == -1)
            return false;
        else {
            size--;
            store[index] = store[size];
            store[size] = null;
            return true;
        }
    }

    public boolean contains(E item) {
        return indexOf(item) > -1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        do {
            remove(size);
        } while (!isEmpty());
    }

    public int size() {
        return size;
    }

    public Object[] toArray() {
        return trimToSize();
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayIterator<>(store);
    }

    @Override
    public String toString() {
        return "";
    }

    private int indexOf(E item) {
        int index = 0;
        while ((index < size) && (!item.equals(store[index]))) {
            index++;
        }

        if (index == size) {
            return -1;
        }

        return index;
    }

    public Object[] trimToSize() {
        Object[] trimmedArray;
        trimmedArray = new Object[size];
        System.arraycopy(store, 0, trimmedArray, 0, size);

        return trimmedArray;
    }
}
