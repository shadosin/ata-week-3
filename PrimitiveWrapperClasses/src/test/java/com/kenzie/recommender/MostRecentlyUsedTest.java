package com.kenzie.recommender;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MostRecentlyUsedTest {
    MostRecentlyUsed<Integer> mostRecentlyUsed;
    int capacity = 3;

    @BeforeEach
    void setUp() {
        mostRecentlyUsed = new MostRecentlyUsed<>(capacity);
    }

    @Test
    public void size_withoutAddingAnything_shouldHaveZeroSize() {
        // WHEN
        int size = mostRecentlyUsed.getSize();

        // THEN
        assertEquals(0, size, "Size should be 0.");
    }

    @Test
    public void add_oneItemIsAdded_itIsTheOnlyElement() {
        // GIVEN
        int[] elementsToAdd = {3};

        // WHEN
        addElements(elementsToAdd);

        // THEN
        assertElements(elementsToAdd);
    }

    @Test
    public void add_addFullCapacity_allElementsPresentInOrder() {
        // GIVEN
        int[] elementsToAdd = {3, 1, 4};

        // WHEN
        addElements(elementsToAdd);

        // THEN
        assertElements(elementsToAdd);
    }

    @Test
    public void add_addMoreThanCapacity_returnsFirstElementAdded() {
        // GIVEN
        int[] elementsToAdd = {3, 1, 4};
        int elementOverCapacity = 1;
        addElements(elementsToAdd);

        // WHEN
        int removed = mostRecentlyUsed.add(elementOverCapacity);

        // THEN
        assertEquals(elementsToAdd[0], removed, "First element added should have been the one removed.");
    }

    @Test
    public void get_addMoreThanCapacity_lastElementsPresentInOrder() {
        // GIVEN
        int[] elementsToAdd = {3, 1, 4, 1};
        int[] expectedElements = Arrays.copyOfRange(elementsToAdd, 1, elementsToAdd.length);

        // WHEN
        addElements(elementsToAdd);

        // THEN
        assertElements(expectedElements);
    }

    private void addElements(int[] elementsToAdd) {
        for (int element : elementsToAdd) {
            mostRecentlyUsed.add(element);
        }
    }

    private void assertElements(int[] expectedElements) {
        assertEquals(expectedElements.length, mostRecentlyUsed.getSize(), "Size should be " + expectedElements.length);

        for (int i = 0; i < expectedElements.length; i++) {
            assertEquals(expectedElements[i], mostRecentlyUsed.get(i),
                String.format("Expected next element to be %d, but was %d", expectedElements[i],
                    mostRecentlyUsed.get(i)));
        }
    }
}
