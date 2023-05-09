package com.kenzie.increment;

import java.util.Random;

/**
 * An Incrementable that increments by a random integer amount each
 * time increment() is called. The increment size is capped by
 * maxIncrement, and will always be at least 1.
 */
public class RandomSequentialIncrementer implements Incrementable {
    private static final int MIN_INCREMENT = 1;

    private final Random random;
    private final int maxIncrement;
    private int value;

    /**
     * Constructs a RandomSequentialIncrementer with the specified maximum
     * increment size, and a default starting value of 0.
     * @param maxIncrement The maximum increment size allowed
     */
    public RandomSequentialIncrementer(int maxIncrement) {
        this(maxIncrement, 0);
    }

    /**
     * Constructs a RandomSequentialIncrementer with the specified maximum
     * increment size and starting value.
     * @param maxIncrement The maximum increment size allowed
     * @param value The starting value
     */
    public RandomSequentialIncrementer(int maxIncrement, int value) {
        this(maxIncrement, value, new Random());
    }

    /**
     * Constructs a RandomSequentialIncrementer with the specified maximum
     * increment size, starting value and Random number generator.
     * @param maxIncrement The maximum increment size allowed
     * @param value The starting value
     * @param random The random number generator to use
     */
    public RandomSequentialIncrementer(int maxIncrement, int value, Random random) {
        this.maxIncrement = maxIncrement;
        this.value = value;
        this.random = random;
    }

    @Override
    public int increment() {
        // TODO: Add logic here to increment the value by a random increment
        // Hint: random.nextInt() may be helpful here. It returns a value on [0, arg)

        // returns the newly updated value, after incrementing
        return value;
    }

    @Override
    public int getValue() {
        return value;
    }
}
