package com.kenzie.ata.recommender;

/**
 * A collection of the n most recently used items. When item n+1 is added it replaces the oldest item in the collection,
 * the one that was added before any of the other items.
 *
 * @param <E> The type to store in the collection.
 */
public class MostRecentlyUsed<E> {
    private int oldestIndex = 0;
    private int size = 0;
    private E[] elements;

    /**
     * Instantiates MostRecentlyUsed collection that can hold "capacity" items.
     *
     * @param capacity The number of items the collection can store.
     */
    public MostRecentlyUsed(int capacity) {
        elements = (E[]) new Object[capacity];
    }

    /**
     * Adds an item to the collection. If the collection is already full, this item replaces the oldest item in the
     * collection.
     *
     * @param mostRecentlyUsed Item to add to the collection
     * @return The oldest item that is being replaced in the collection.
     */
    public E add(E mostRecentlyUsed) {
        int newIndex;
        if (size < elements.length) {
            newIndex = (oldestIndex + size) % elements.length;
            size++;
        } else {
            newIndex = oldestIndex;
            oldestIndex = (oldestIndex + 1) % elements.length;
        }

        E overwrittenElement = elements[newIndex];
        elements[newIndex] = mostRecentlyUsed;
        return overwrittenElement;
    }

    /**
     * Return's the nth oldest element in the collection.
     *
     * @param index Index of the element to return. If 0 returns the oldest, if size returns the newest.
     * @return The nth oldest element in the collection.
     */
    public E get(int index) {
        if (index > size) {
            throw new IndexOutOfBoundsException("Cannot ask for index larger than size. Current size: " + size);
        }
        int shiftedIndex = (oldestIndex + index) % elements.length;
        return elements[shiftedIndex];
    }

    public int getSize() {
        return size;
    }
}
