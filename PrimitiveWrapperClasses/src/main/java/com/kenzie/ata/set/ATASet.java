package com.kenzie.ata.set;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A fixed capacity unique collection of elements. The user of this collection does not have control over where elements
 * are inserted. They are not guaranteed to be inserted sequentially. The user can add and search for elements in the
 * collection. Null elements are not allowed.
 *
 * @param <E> the type of elements in this collection
 */
public class ATASet<E> implements Iterable<E> {
    private final E[] values;
    private final int capacity;

    /**
     * Creates a set of fixed size determined by the provided capacity value.
     *
     * @param capacity the desired size of the ATASet to be created, must be greater than 0
     */
    public ATASet(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("A positive value, greater than 0, must be provided for the set's" +
                                                   "capacity.");
        }
        this.values = (E[]) new Object[capacity];
        this.capacity = capacity;
    }


    /**
     * Returns the number of available spots in the collection (initial capacity - size()).
     *
     * @return the count of available spots
     */
    public int availableCapacity() {
        return this.capacity - size();
    }

    /**
     * Returns the number of elements this collection is capable of storing. This is the same as the original provided
     * capacity value during construction. This does not account for what spots are taken.
     *
     * @return the number of elements this collection is capable of storing.
     */
    public int capacity() {
        return this.capacity;
    }

    /**
     * Returns the number of elements this collection contains.
     *
     * @return the count of elements this collection is capable of containing
     */
    public int size() {
        int filledSpots = 0;
        for (E e : values) {
            if (e != null) {
                filledSpots++;
            }
        }
        return filledSpots;
    }

    /**
     * Returns <tt>true</tt> if this collection contains the specified element. If a null element is
     * provided, false is returned.
     *
     * @param e element whose presence in this colelction is to be tested
     * @return <tt>true</tt> if this collection contains the specified element
     */
    public boolean contains(E e) {
        if (e == null) {
            throw new NullPointerException("Null values are not permitted in this collection.");
        }
        for (int i = 0; i < values.length; i++) {
            if (e.equals(values[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Inserts the provided element to the collection if there is capacity and the element is not already present in the
     * collection. This method does not guarantee which location the element will be added to. Null values are not
     * permitted.
     *
     * @param e the element to be added
     * @return true if the element was added, false otherwise
     * @throws NullPointerException if the specified element is null
     */
    public boolean add(E e) {
        if (e == null) {
            throw new NullPointerException("Null values are not permitted in this collection.");
        }
        if (contains(e)) {
            return false;
        }
        for (int i = 0; i < values.length; i++) {
            if (values[i] == null) {
                values[i] = e;
                return true;
            }
        }
        return false;
    }

    /**
     * Removes the element from the collection if it is present. If the element is not present the collection remains
     * unchanged.
     *
     * @param e the element to be removed
     * @return true if this list contained the specified element
     * @throws NullPointerException if the specified element is null
     */
    public boolean remove(E e) {
        if (e == null) {
            throw new NullPointerException("Null values are not permitted in this collection.");
        }
        for (int i = 0; i < values.length; i++) {
            if (e.equals(values[i])) {
                values[i] = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(values[0].toString());
        for (int i = 1; i < values.length; i++) {
            builder.append(", ");
            builder.append(values[i]);
        }
        return builder.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    /**
     * Does not handle concurrent modifications. Provides access to each element in the set, skipping null cells.
     */
    private class Itr implements Iterator<E> {
        int cursor = 0;

        @Override
        public boolean hasNext() {
            int i = cursor;
            while (i < capacity) {
                if (values[i] != null) {
                    return true;
                }
                i++;
            }
            return false;
        }

        @Override
        public E next() {
            if (cursor >= capacity) {
                throw new NoSuchElementException();
            }
            while (values[cursor] == null) {
                cursor++;
                if (cursor >= capacity) {
                    throw new NoSuchElementException();
                }
            }
            E next = values[cursor];
            cursor++;
            return next;
        }
    }
}
