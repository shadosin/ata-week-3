package com.kenzie.devices.alexa.quality;

import com.kenzie.increment.FixedIncrementer;
import com.kenzie.increment.Incrementable;
import com.kenzie.increment.RandomSequentialIncrementer;
import com.kenzie.increment.SequentialIncrementer;

import org.junit.jupiter.api.Test;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlexaInspectionDeviceSelectorTest {

    private AlexaInspectionDeviceSelector selector;

    @Test
    public void getSampleDevicePosition_firstCallWithSequentialIncrementer_returnsOne() {
        // GIVEN - a selector object and a new incrementer
        final Incrementable incrementer = new SequentialIncrementer();
        selector = new AlexaInspectionDeviceSelector(incrementer);

        // WHEN - call getSampleDevicePosition for the first time
        int result = selector.getSampleDevicePosition();

        // THEN - returns 1, the first device
        assertEquals(1, result, "Expected first call to incremental device selector to return 1.");
    }

    @Test
    public void getSampleDevicePosition_firstCallWithSequentialIncrementerWithStartValue_returnsOne() {
        // GIVEN - a selector object and a new incrementer initialized with a start value
        int startValue = 10;
        final Incrementable incrementer = new SequentialIncrementer(startValue);
        selector = new AlexaInspectionDeviceSelector(incrementer);

        // WHEN - call getSampleDevicePosition for the first time
        int result = selector.getSampleDevicePosition();

        // THEN - returns 1 more than the starting value
        assertEquals(startValue + 1, result,
                "Expected first call to incremental device selector to return 1 more than start value.");
    }

    @Test
    public void getSampleDevicePosition_secondCallWithSequentialIncrementerWithStartValue_returnsTwo() {
        // GIVEN - a selector object, a new incrementer, and an initial call to the selector
        final Incrementable incrementer = new SequentialIncrementer();
        selector = new AlexaInspectionDeviceSelector(incrementer);
        selector.getSampleDevicePosition();

        // WHEN - call getSampleDevicePosition
        int result = selector.getSampleDevicePosition();

        // THEN - returns 2, the second device
        assertEquals(2, result, "Expected the second call to incremental device selector to return 2.");
    }

    @Test
    public void getSampleDevicePosition_secondCallWithSequentialIncrementerWithStartValue_returnsTwoMoreThanStart() {
        // GIVEN - a selector object, a new incrementer initialized with a start value, and an initial call to the selector
        int startValue = 10;
        final Incrementable incrementer = new SequentialIncrementer(startValue);
        selector = new AlexaInspectionDeviceSelector(incrementer);
        selector.getSampleDevicePosition();

        // WHEN - call getSampleDevicePosition
        int result = selector.getSampleDevicePosition();

        // THEN - returns 2 more than the starting value
        assertEquals(startValue + 2, result,
                "Expected the second call to incremental device selector to return 2 more than the start value.");
    }

    @Test
    void getSampleDevicePosition_firstCallWithFixedIncrementer_returnsStartValuePlusIncrementSize() {
        // GIVEN
        // start and increment size
        int startValue = 10;
        int incrementSize = 4;
        // FixedIncrementer
        Incrementable incrementer = new FixedIncrementer(incrementSize, startValue);
        // the selector under test
        selector = new AlexaInspectionDeviceSelector(incrementer);

        // WHEN
        int result = selector.getSampleDevicePosition();

        // THEN
        assertEquals(
                startValue + incrementSize,
                result,
                String.format(
                        "Expected the first sample position to be incrementSize (%d) past start value (%d), but wasn't",
                        incrementSize,
                        startValue)
        );
    }

    @Test
    void getSampleDevicePosition_secondCallWithFixedIncrementer_returnsStartValuePlusTwoTimesIncrement() {
        // GIVEN
        // start and increment size
        int startValue = 2;
        int incrementSize = 18;
        // FixedIncrementer
        Incrementable incrementer = new FixedIncrementer(incrementSize, startValue);
        // the selector under test...
        selector = new AlexaInspectionDeviceSelector(incrementer);
        // ...has been called once already
        selector.getSampleDevicePosition();

        // WHEN
        int result = selector.getSampleDevicePosition();

        // THEN
        assertEquals(
                startValue + 2 * incrementSize,
                result,
                String.format(
                        "Expected the first sample position to be 2x incrementSize (%d) past start value (%d), but wasn't",
                        incrementSize,
                        startValue)
        );
    }

    @Test
    void getSampleDevicePosition_firstCallWithRandomIncrementer_returnsFirstIncrement() {
        // GIVEN
        // a random incrementer with known seed/first output, start value, max increment
        final int maxIncrement = 31;
        final int startValue = 0;
        final Random random = new Random(0L);
        final int expectedFirstIncrement = 3;
        final Incrementable incrementer = new RandomSequentialIncrementer(maxIncrement, startValue, random);
        // the selector under test
        selector = new AlexaInspectionDeviceSelector(incrementer);

        // WHEN - call get SampleDevicePosition
        int result = selector.getSampleDevicePosition();

        // THEN - returns same position as the fixed increment value (position started at 0)
        assertEquals(expectedFirstIncrement,
                result,
                "Expected first increment to result in position equal to first random increment");
    }

    @Test
    public void getSampleDevicePosition_secondCallWithRandomIncrementerWithStartValue_returnsTwoIncrementsPastStart() {
        // GIVEN
        // a random incrementer with known seed/first output, start value, max increment
        final int maxIncrement = 100;
        final int startValue = 37;
        final Random random = new Random(0L);
        final int expectedFirstIncrement = 61;
        final int expectedSecondIncrement = 49;
        final Incrementable incrementer = new RandomSequentialIncrementer(maxIncrement, startValue, random);
        // the selector under test...
        selector = new AlexaInspectionDeviceSelector(incrementer);
        // ...has been called once already
        selector.getSampleDevicePosition();

        // WHEN - call getSampleDevicePosition
        int result = selector.getSampleDevicePosition();

        // THEN - returns (2 * increment) more than the starting value
        assertEquals(startValue + expectedFirstIncrement + expectedSecondIncrement,
                result,
                String.format("Expected the second call to incremental device selector to return " +
                                "two increments (%d) more than the start value (%d)",
                        expectedFirstIncrement + expectedSecondIncrement,
                        startValue));
    }
}
