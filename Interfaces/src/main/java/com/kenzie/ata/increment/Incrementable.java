package com.kenzie.ata.increment;

/**
 * Defines interface for incrementing integer values.
 */
public interface Incrementable {
    /**
     * Increment the value according to the Incrementable's rule.
     * @return The new value, after incrementing
     */
    int increment();

    /**
     * Returns the Incrementable's current value, without performing an increment.
     * @return The current value
     */
    int getValue();
}
