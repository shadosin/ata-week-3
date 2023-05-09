package com.kenzie.ata.datalogger;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TimestampedStringTest {
    private static final ZonedDateTime SAMPLE_TIMESTAMP = ZonedDateTime.parse("2019-07-31T08:00:19Z[UTC]");


    @Test
    void dataAndTimestampConstructor_withNonNullValues_constructsInstance() {
        // GIVEN - valid data, timestamp
        String data = "My message";
        ZonedDateTime timestamp = SAMPLE_TIMESTAMP;

        // WHEN - construct TimestampedData
        TimestampedString timestampedString = new TimestampedString(data, timestamp);

        // THEN
        // data is correct
        assertEquals(data, timestampedString.getData(), "Expected stored string to match constructor argument");
        // timestamp is correct
        assertEquals(timestamp, timestampedString.getTimestamp(), "Expected timestamp to match");
    }

    @Test
    void dataAndTimestampConstructor_withNullData_constructsInstance() {
        // GIVEN - null data, valid timestamp
        String data = null;
        ZonedDateTime timestamp = SAMPLE_TIMESTAMP;

        // WHEN - construct TimestampedData
        TimestampedString timestampedString = new TimestampedString(data, timestamp);

        // THEN
        // data is null
        assertNull(timestampedString.getData(), "Expected string to be null");
        // timestamp is correct
        assertEquals(timestamp, timestampedString.getTimestamp(), "Expected timestamp to match");
    }

    @Test
    void dataAndTimestampConstructor_withNullTimestamp_throwsException() {
        // GIVEN - valid data, null timestamp
        String data = "some string";
        ZonedDateTime timestamp = null;

        // WHEN - construct TimestampedData
        // THEN - throw exception
        assertThrows(IllegalArgumentException.class,
                     () -> new TimestampedString(data, timestamp),
                     "Expected IllegalArgumentException when passing in null timestamp");
    }

    @Test
    void dataConstructor_withData_defaultsTimestampToNow() {
        // GIVEN - valid data
        String data = "Hello there!";

        // WHEN - construct TimestampedData
        TimestampedString timestampedString = new TimestampedString(data);

        // THEN
        // data is correct
        assertEquals(data, timestampedString.getData(), "Expected stored string to match constructor argument");
        // timestamp is close to now (within 5 seconds)
        Duration durationBetweenTimestampAndNow = Duration.between(timestampedString.getTimestamp(), ZonedDateTime.now());
        assertTrue(durationBetweenTimestampAndNow.toMillis() < 5_000);
    }

    @Test
    void dataConstructor_withNullData_constructsInstance() {
        // GIVEN - null data
        String data = null;

        // WHEN - construct Timestamped data
        TimestampedString timestampedString = new TimestampedString(data);

        // THEN
        // data is null
        assertNull(timestampedString.getData(), "Expected string to be null");
        // timestamp is close to now (within 5 seconds)
        Duration durationBetweenTimestampAndNow = Duration.between(timestampedString.getTimestamp(), ZonedDateTime.now());
        assertTrue(durationBetweenTimestampAndNow.toMillis() < 5_000);
    }
}
