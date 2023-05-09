package com.kenzie.set;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SetTest {

    @Test
    public void constructor_withNegativeCapacity_throwsException() {
        // GIVEN - negative capacity
        int capacity = -1;

        // WHEN - construct Set
        // THEN - throw exception
        assertThrows(IllegalArgumentException.class,
                     () -> new com.kenzie.set.Set(capacity),
                     "Expected IllegalArgumentException when passing the constructor a negative capacity");
    }

    @Test
    public void constructor_withZeroCapacity_throwsExcpetion() {
        // GIVEN - zero capacity
        int capacity = 0;

        // WHEN - construct Set
        // THEN - throw exception
        assertThrows(IllegalArgumentException.class,
                     () -> new com.kenzie.set.Set(capacity),
                     "Expected IllegalArgumentException when passing the constructor a zero capacity");
    }

    @Test
    public void constructor_withPositiveCapacity_constructsInstance() {
        // GIVEN - positive capacity
        int capacity = 10;

        // WHEN - construct Set
        com.kenzie.set.Set<Integer> set = new com.kenzie.set.Set(capacity);

        // THEN - new Set has correct size, and is empty
        assertEquals(capacity, set.availableCapacity(), "Expected new Set to have full capacity.");
        assertEquals(0, set.size(), "Expected size to be 0.");
    }

    @Test
    public void availableCapacity_emptySet_returnsSize() {
        // GIVEN - a new empty Set
        int capacity = 10;
        com.kenzie.set.Set<Integer> set = new com.kenzie.set.Set(capacity);

        // WHEN - call to get capacity
        int availableCapacity = set.availableCapacity();

        // THEN - new Set available capacity is equal to starting capacity
        assertEquals(capacity, availableCapacity, "Expected new Set to have full available capacity.");
    }

    @Test
    public void availableCapacity_nonEmptySet_returnsCorrectRemainingCapacity() {
        // GIVEN - an Set with one item added to it
        int capacity = 10;
        com.kenzie.set.Set<Integer> set = new com.kenzie.set.Set(capacity);
        set.add(1);

        // WHEN - call to get capacity
        int availableCapacity = set.availableCapacity();

        // THEN - Set has one less available capacity
        assertEquals(capacity - 1, availableCapacity, "Expected Set to have one less available " +
            "capacity after adding one element to it.");
    }

    @Test
    public void capacity_emptySet_returnsInitialCapacity() {
        // GIVEN - an empty Set
        int capacity = 10;
        com.kenzie.set.Set<Integer> set = new com.kenzie.set.Set(capacity);

        // WHEN - call to get capacity
        int result = set.capacity();

        // THEN - Set still has initial capactiy
        assertEquals(capacity, result, "Expected Set to have capacity it was initialized with.");
    }

    @Test
    public void capacity_nonemptySet_returnsInitialCapacity() {
        // GIVEN - an Set with one item added to it
        int capacity = 10;
        com.kenzie.set.Set<Integer> set = new com.kenzie.set.Set(capacity);
        set.add(1);

        // WHEN - call to get capacity
        int result = set.capacity();

        // THEN - Set still has initial capactiy
        assertEquals(capacity, result, "Expected Set to have capacity is was initialized with.");
    }

    @Test
    public void size_emptySet_returnsZero() {
        // GIVEN - an empty Set
        int capacity = 10;
        com.kenzie.set.Set<Integer> set = new com.kenzie.set.Set(capacity);

        // WHEN - call to get size
        int size = set.size();

        // THEN - new Set is empty
        assertEquals(0, size, "Expected new Set to have size 0.");
    }

    @Test
    public void size_nonemptySet_returnsCorrectSize() {
        // GIVEN - an Set, with 2 items added to it
        int capacity = 5;
        com.kenzie.set.Set<Integer> set = new com.kenzie.set.Set(capacity);
        set.add(1);
        set.add(2);

        // WHEN - call to get size
        int size = set.size();

        // THEN - Set has 2 items in it
        assertEquals(2, size, "Expected nonempty Set to have size > 0.");
    }

    @Test
    public void size_fullSet_returnsCapacity() {
        // GIVEN - a full Set
        int capacity = 3;
        com.kenzie.set.Set<Integer> set = new com.kenzie.set.Set(capacity);
        set.add(1);
        set.add(2);
        set.add(3);

        // WHEN - call to get size
        int size = set.size();

        // THEN - new Set is empty
        assertEquals(capacity, size, "Expected Set to be full.");
        assertEquals(0, set.availableCapacity(), "Expected full Set to have zero capacity.");
    }

    @Test
    public void contains_nullCheck_throwsException() {
        // GIVEN - a full Set
        int capacity = 3;
        com.kenzie.set.Set<Integer> set = new com.kenzie.set.Set(capacity);
        set.add(1);
        set.add(2);
        set.add(3);

        // WHEN - call to check if set contains null, THEN - throw NullPointerException
        assertThrows(NullPointerException.class,
                     () -> set.contains(null),
                     "Expected an exception when checking if Set contains a null element.");
    }

    @Test
    public void contains_elementPresent_returnsTrue() {
        // GIVEN - a full Set
        int capacity = 3;
        com.kenzie.set.Set<Integer> set = new com.kenzie.set.Set(capacity);
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
        // GIVEN - a full Set
        int capacity = 3;
        com.kenzie.set.Set<Integer> set = new com.kenzie.set.Set(capacity);
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
        // GIVEN - an Set, with capacity
        int capacity = 3;
        com.kenzie.set.Set<Integer> set = new com.kenzie.set.Set(capacity);
        set.add(1);
        set.add(2);

        // WHEN - call to add null, THEN - throw NullPointerException
        assertThrows(NullPointerException.class,
                     () -> set.add(null),
                     "Expected an exception when adding a null element to an Set.");
    }

    @Test
    public void add_elementPresent_returnsFalse() {
        // GIVEN - an Set, with capacity
        int capacity = 3;
        com.kenzie.set.Set<Integer> set = new com.kenzie.set.Set(capacity);
        set.add(1);
        set.add(2);

        // WHEN - call to add an existing element
        boolean result = set.add(2);

        // THEN - the element is already present and false is returned
        assertFalse(result, "Expected add to return false for an element that is already present.");
        assertTrue(set.contains(2), "Expected element to be already present in the Set.");
    }

    @Test
    public void add_elementNotPresent_returnsTrue() {
        // GIVEN - an Set, with capacity
        int capacity = 3;
        com.kenzie.set.Set<Integer> set = new com.kenzie.set.Set(capacity);
        set.add(1);
        set.add(2);
        int originalSize = set.size();

        // WHEN - call to add a new element
        boolean result = set.add(4);

        // THEN - the element is added and true is returned
        assertTrue(result, "Expected add to return true for an element that is not present.");
        assertTrue(set.contains(4), "Expected element to be added to the Set.");
        assertEquals(originalSize + 1, set.size(), "Expected size to increase by one when adding" +
            "a new value.");
    }

    @Test
    public void add_elementNotPresentNoCapacity_returnsFalse() {
        // GIVEN - an Set, with no capacity
        int capacity = 3;
        com.kenzie.set.Set<Integer> set = new com.kenzie.set.Set(capacity);
        set.add(1);
        set.add(2);
        set.add(3);

        // WHEN - call to add a new element
        boolean result = set.add(4);

        // THEN - the element is not added and false is returned
        assertFalse(result, "Expected add to return false for a set without capacity.");
        assertFalse(set.contains(4), "Expected element to not be present in the Set.");
    }

    @Test
    public void remove_nullElement_throwsException() {
        // GIVEN - an Set, with 3 items added to it
        int capacity = 3;
        com.kenzie.set.Set<Integer> set = new com.kenzie.set.Set(capacity);
        set.add(1);
        set.add(2);
        set.add(3);

        // WHEN - call to remove null, THEN - throw NullPointerException
        assertThrows(NullPointerException.class,
                     () -> set.remove(null),
                     "Expected an exception when removing a null element from an Set.");
    }

    @Test
    public void remove_elementPresent_returnsTrue() {
        // GIVEN - an Set, with 3 items added to it
        int capacity = 3;
        com.kenzie.set.Set<Integer> set = new com.kenzie.set.Set(capacity);
        set.add(1);
        set.add(2);
        set.add(3);
        int originalSize = set.size();

        // WHEN - call to remove an existing element
        boolean result = set.remove(2);

        // THEN - the element is removed and true is returned
        assertTrue(result, "Expected remove to return true for an element that is present.");
        assertFalse(set.contains(2), "Expected element to be removed from the Set.");
        assertEquals(originalSize - 1, set.size(), "Expected size to decrease by one when removing" +
            "an element.");
    }

    @Test
    public void remove_elementNotPresent_returnsFalse() {
        // GIVEN - an Set, with 3 items added to it
        int capacity = 3;
        com.kenzie.set.Set<Integer> set = new com.kenzie.set.Set(capacity);
        set.add(1);
        set.add(2);
        set.add(3);

        // WHEN - call to remove an element that is not in the Set
        boolean result = set.remove(4);

        // THEN - false is returned
        assertFalse(result, "Expected remove to return false for an element that is not present.");
    }

    @Test
    public void iterator_nextWithNulls_skipsNulls() {
        int capacity = 3;
        com.kenzie.set.Set<Integer> set = new com.kenzie.set.Set(capacity);
        set.add(1);
        set.add(2);
        set.add(3);
        set.remove(2);

        Iterator<Integer> itr = set.iterator();
        itr.next();

        assertEquals(3, (int) itr.next(), "Expected iterator to skip nulls in Set");
    }

    @Test
    public void iterator_nextWithoutNulls_skipsNulls() {
        int capacity = 3;
        com.kenzie.set.Set<Integer> set = new com.kenzie.set.Set(capacity);
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
        com.kenzie.set.Set<Integer> set = new com.kenzie.set.Set(capacity);
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
        com.kenzie.set.Set<Integer> set = new com.kenzie.set.Set(capacity);
        set.add(1);

        Iterator<Integer> itr = set.iterator();
        itr.next();

        assertThrows(NoSuchElementException.class,
                     () -> itr.next(),
                     "Expected iterator to throw exception when no more elements exist.");
    }

    @Test
    public void iterator_nextEmptySet_throwsException() {
        int capacity = 1;
        com.kenzie.set.Set<Integer> set = new com.kenzie.set.Set(capacity);

        Iterator<Integer> itr = set.iterator();

        assertThrows(NoSuchElementException.class,
                     () -> itr.next(),
                     "Expected iterator to throw exception when Set is empty.");
    }

    @Test
    public void iterator_hasNextWithNullsLeft_returnsFalse() {
        int capacity = 3;
        com.kenzie.set.Set<Integer> set = new com.kenzie.set.Set(capacity);
        set.add(1);

        Iterator<Integer> itr = set.iterator();
        itr.next();

        assertFalse(itr.hasNext(), "Expected hasNext() to return false when only empty spots left in Set");
    }

    @Test
    public void iterator_hasNextWithNoElementsLeft_returnsFalse() {
        int capacity = 3;
        com.kenzie.set.Set<Integer> set = new com.kenzie.set.Set(capacity);
        set.add(1);

        Iterator<Integer> itr = set.iterator();
        itr.next();

        assertFalse(itr.hasNext(), "Expected hasNext() to return false when iterated through entire Set.");
    }

    @Test
    public void iterator_hasNextEmptySet_returnsFalse() {
        int capacity = 3;
        com.kenzie.set.Set<Integer> set = new com.kenzie.set.Set(capacity);

        Iterator<Integer> itr = set.iterator();

        assertFalse(itr.hasNext(), "Expected hasNext() to return false when Set is empty.");
    }

    @Test
    public void iterator_hasNextWithElementsLeft_returnsTrue() {
        int capacity = 3;
        com.kenzie.set.Set<Integer> set = new com.kenzie.set.Set(capacity);
        set.add(1);

        Iterator<Integer> itr = set.iterator();

        assertTrue(itr.hasNext(), "Expected hasNext() to return true when elements left in Set");
    }
}
