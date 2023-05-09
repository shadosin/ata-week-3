package com.kenzie.ata.set;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ATASetTest {

    @Test
    public void constructor_withNegativeCapacity_throwsException() {
        // GIVEN - negative capacity
        int capacity = -1;

        // WHEN - construct ATASet
        // THEN - throw exception
        assertThrows(IllegalArgumentException.class,
                     () -> new ATASet(capacity),
                     "Expected IllegalArgumentException when passing the constructor a negative capacity");
    }

    @Test
    public void constructor_withZeroCapacity_throwsExcpetion() {
        // GIVEN - zero capacity
        int capacity = 0;

        // WHEN - construct ATASet
        // THEN - throw exception
        assertThrows(IllegalArgumentException.class,
                     () -> new ATASet(capacity),
                     "Expected IllegalArgumentException when passing the constructor a zero capacity");
    }

    @Test
    public void constructor_withPositiveCapacity_constructsInstance() {
        // GIVEN - positive capacity
        int capacity = 10;

        // WHEN - construct ATASet
        ATASet<Integer> set = new ATASet(capacity);

        // THEN - new ATASet has correct size, and is empty
        assertEquals(capacity, set.availableCapacity(), "Expected new ATASet to have full capacity.");
        assertEquals(0, set.size(), "Expected size to be 0.");
    }

    @Test
    public void availableCapacity_emptyATASet_returnsSize() {
        // GIVEN - a new empty ATASet
        int capacity = 10;
        ATASet<Integer> set = new ATASet(capacity);

        // WHEN - call to get capacity
        int availableCapacity = set.availableCapacity();

        // THEN - new ATASet available capacity is equal to starting capacity
        assertEquals(capacity, availableCapacity, "Expected new ATASet to have full available capacity.");
    }

    @Test
    public void availableCapacity_nonEmptyATASet_returnsCorrectRemainingCapacity() {
        // GIVEN - an ATASet with one item added to it
        int capacity = 10;
        ATASet<Integer> set = new ATASet(capacity);
        set.add(1);

        // WHEN - call to get capacity
        int availableCapacity = set.availableCapacity();

        // THEN - ATASet has one less available capacity
        assertEquals(capacity - 1, availableCapacity, "Expected ATASet to have one less available " +
            "capacity after adding one element to it.");
    }

    @Test
    public void capacity_emptyATASet_returnsInitialCapacity() {
        // GIVEN - an empty ATASet
        int capacity = 10;
        ATASet<Integer> set = new ATASet(capacity);

        // WHEN - call to get capacity
        int result = set.capacity();

        // THEN - ATASet still has initial capactiy
        assertEquals(capacity, result, "Expected ATASet to have capacity it was initialized with.");
    }

    @Test
    public void capacity_nonemptyATASet_returnsInitialCapacity() {
        // GIVEN - an ATASet with one item added to it
        int capacity = 10;
        ATASet<Integer> set = new ATASet(capacity);
        set.add(1);

        // WHEN - call to get capacity
        int result = set.capacity();

        // THEN - ATASet still has initial capactiy
        assertEquals(capacity, result, "Expected ATASet to have capacity is was initialized with.");
    }

    @Test
    public void size_emptyATASet_returnsZero() {
        // GIVEN - an empty ATASet
        int capacity = 10;
        ATASet<Integer> set = new ATASet(capacity);

        // WHEN - call to get size
        int size = set.size();

        // THEN - new ATASet is empty
        assertEquals(0, size, "Expected new ATASet to have size 0.");
    }

    @Test
    public void size_nonemptyATASet_returnsCorrectSize() {
        // GIVEN - an ATASet, with 2 items added to it
        int capacity = 5;
        ATASet<Integer> set = new ATASet(capacity);
        set.add(1);
        set.add(2);

        // WHEN - call to get size
        int size = set.size();

        // THEN - ATASet has 2 items in it
        assertEquals(2, size, "Expected nonempty ATASet to have size > 0.");
    }

    @Test
    public void size_fullATASet_returnsCapacity() {
        // GIVEN - a full ATASet
        int capacity = 3;
        ATASet<Integer> set = new ATASet(capacity);
        set.add(1);
        set.add(2);
        set.add(3);

        // WHEN - call to get size
        int size = set.size();

        // THEN - new ATASet is empty
        assertEquals(capacity, size, "Expected ATASet to be full.");
        assertEquals(0, set.availableCapacity(), "Expected full ATASet to have zero capacity.");
    }

    @Test
    public void contains_nullCheck_throwsException() {
        // GIVEN - a full ATASet
        int capacity = 3;
        ATASet<Integer> set = new ATASet(capacity);
        set.add(1);
        set.add(2);
        set.add(3);

        // WHEN - call to check if set contains null, THEN - throw NullPointerException
        assertThrows(NullPointerException.class,
                     () -> set.contains(null),
                     "Expected an exception when checking if ATASet contains a null element.");
    }

    @Test
    public void contains_elementPresent_returnsTrue() {
        // GIVEN - a full ATASet
        int capacity = 3;
        ATASet<Integer> set = new ATASet(capacity);
        set.add(1);
        set.add(2);
        set.add(3);

        // WHEN - call to check if set contains a present element
        boolean result = set.contains(2);

        // THEN - true is returned
        assertTrue(result, "Expected contains to return true for an element that is already present.");
    }

    @Test
    public void contains_elementNotPresent_returnsFalse() {
        // GIVEN - a full ATASet
        int capacity = 3;
        ATASet<Integer> set = new ATASet(capacity);
        set.add(1);
        set.add(2);
        set.add(3);

        // WHEN - call to check if set contains a non-present element
        boolean result = set.contains(4);

        // THEN - false is returned
        assertFalse(result, "Expected contains to return false for an element that is not present.");
    }

    @Test
    public void add_addNullElement_throwsException() {
        // GIVEN - an ATASet, with capacity
        int capacity = 3;
        ATASet<Integer> set = new ATASet(capacity);
        set.add(1);
        set.add(2);

        // WHEN - call to add null, THEN - throw NullPointerException
        assertThrows(NullPointerException.class,
                     () -> set.add(null),
                     "Expected an exception when adding a null element to an ATASet.");
    }

    @Test
    public void add_elementPresent_returnsFalse() {
        // GIVEN - an ATASet, with capacity
        int capacity = 3;
        ATASet<Integer> set = new ATASet(capacity);
        set.add(1);
        set.add(2);

        // WHEN - call to add an existing element
        boolean result = set.add(2);

        // THEN - the element is already present and false is returned
        assertFalse(result, "Expected add to return false for an element that is already present.");
        assertTrue(set.contains(2), "Expected element to be already present in the ATASet.");
    }

    @Test
    public void add_elementNotPresent_returnsTrue() {
        // GIVEN - an ATASet, with capacity
        int capacity = 3;
        ATASet<Integer> set = new ATASet(capacity);
        set.add(1);
        set.add(2);
        int originalSize = set.size();

        // WHEN - call to add a new element
        boolean result = set.add(4);

        // THEN - the element is added and true is returned
        assertTrue(result, "Expected add to return true for an element that is not present.");
        assertTrue(set.contains(4), "Expected element to be added to the ATASet.");
        assertEquals(originalSize + 1, set.size(), "Expected size to increase by one when adding" +
            "a new value.");
    }

    @Test
    public void add_elementNotPresentNoCapacity_returnsFalse() {
        // GIVEN - an ATASet, with no capacity
        int capacity = 3;
        ATASet<Integer> set = new ATASet(capacity);
        set.add(1);
        set.add(2);
        set.add(3);

        // WHEN - call to add a new element
        boolean result = set.add(4);

        // THEN - the element is not added and false is returned
        assertFalse(result, "Expected add to return false for a set without capacity.");
        assertFalse(set.contains(4), "Expected element to not be present in the ATASet.");
    }

    @Test
    public void remove_nullElement_throwsException() {
        // GIVEN - an ATASet, with 3 items added to it
        int capacity = 3;
        ATASet<Integer> set = new ATASet(capacity);
        set.add(1);
        set.add(2);
        set.add(3);

        // WHEN - call to remove null, THEN - throw NullPointerException
        assertThrows(NullPointerException.class,
                     () -> set.remove(null),
                     "Expected an exception when removing a null element from an ATASet.");
    }

    @Test
    public void remove_elementPresent_returnsTrue() {
        // GIVEN - an ATASet, with 3 items added to it
        int capacity = 3;
        ATASet<Integer> set = new ATASet(capacity);
        set.add(1);
        set.add(2);
        set.add(3);
        int originalSize = set.size();

        // WHEN - call to remove an existing element
        boolean result = set.remove(2);

        // THEN - the element is removed and true is returned
        assertTrue(result, "Expected remove to return true for an element that is present.");
        assertFalse(set.contains(2), "Expected element to be removed from the ATASet.");
        assertEquals(originalSize - 1, set.size(), "Expected size to decrease by one when removing" +
            "an element.");
    }

    @Test
    public void remove_elementNotPresent_returnsFalse() {
        // GIVEN - an ATASet, with 3 items added to it
        int capacity = 3;
        ATASet<Integer> set = new ATASet(capacity);
        set.add(1);
        set.add(2);
        set.add(3);

        // WHEN - call to remove an element that is not in the ATASet
        boolean result = set.remove(4);

        // THEN - false is returned
        assertFalse(result, "Expected remove to return false for an element that is not present.");
    }

    @Test
    public void iterator_nextWithNulls_skipsNulls() {
        int capacity = 3;
        ATASet<Integer> set = new ATASet(capacity);
        set.add(1);
        set.add(2);
        set.add(3);
        set.remove(2);

        Iterator<Integer> itr = set.iterator();
        itr.next();

        assertEquals(3, (int) itr.next(), "Expected iterator to skip nulls in ATASet");
    }

    @Test
    public void iterator_nextWithoutNulls_skipsNulls() {
        int capacity = 3;
        ATASet<Integer> set = new ATASet(capacity);
        set.add(1);
        set.add(2);
        set.add(3);

        Iterator<Integer> itr = set.iterator();
        itr.next();
        itr.next();

        assertEquals(3, (int) itr.next(), "Expected iterator to return correct next value.");
    }

    @Test
    public void iterator_nextNoMoreElementsOnlyNulls_throwsException() {
        int capacity = 3;
        ATASet<Integer> set = new ATASet(capacity);
        set.add(1);

        Iterator<Integer> itr = set.iterator();
        itr.next();

        assertThrows(NoSuchElementException.class,
                     () -> itr.next(),
                     "Expected iterator to throw exception when no more elements exist.");
    }

    @Test
    public void iterator_nextNoMoreElements_throwsException() {
        int capacity = 1;
        ATASet<Integer> set = new ATASet(capacity);
        set.add(1);

        Iterator<Integer> itr = set.iterator();
        itr.next();

        assertThrows(NoSuchElementException.class,
                     () -> itr.next(),
                     "Expected iterator to throw exception when no more elements exist.");
    }

    @Test
    public void iterator_nextEmptyATASet_throwsException() {
        int capacity = 1;
        ATASet<Integer> set = new ATASet(capacity);

        Iterator<Integer> itr = set.iterator();

        assertThrows(NoSuchElementException.class,
                     () -> itr.next(),
                     "Expected iterator to throw exception when ATASet is empty.");
    }

    @Test
    public void iterator_hasNextWithNullsLeft_returnsFalse() {
        int capacity = 3;
        ATASet<Integer> set = new ATASet(capacity);
        set.add(1);

        Iterator<Integer> itr = set.iterator();
        itr.next();

        assertFalse(itr.hasNext(), "Expected hasNext() to return false when only empty spots left in ATASet");
    }

    @Test
    public void iterator_hasNextWithNoElementsLeft_returnsFalse() {
        int capacity = 3;
        ATASet<Integer> set = new ATASet(capacity);
        set.add(1);

        Iterator<Integer> itr = set.iterator();
        itr.next();

        assertFalse(itr.hasNext(), "Expected hasNext() to return false when iterated through entire ATASet.");
    }

    @Test
    public void iterator_hasNextEmptyATASet_returnsFalse() {
        int capacity = 3;
        ATASet<Integer> set = new ATASet(capacity);

        Iterator<Integer> itr = set.iterator();

        assertFalse(itr.hasNext(), "Expected hasNext() to return false when ATASet is empty.");
    }

    @Test
    public void iterator_hasNextWithElementsLeft_returnsTrue() {
        int capacity = 3;
        ATASet<Integer> set = new ATASet(capacity);
        set.add(1);

        Iterator<Integer> itr = set.iterator();

        assertTrue(itr.hasNext(), "Expected hasNext() to return true when elements left in ATASet");
    }
}
