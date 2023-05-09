package com.kenzie.ata.datalogger;

import java.time.ZonedDateTime;

/**
 * Generic class that represents any data type, with an associated timestamp for when the data entry was measured.
 *
 * @param <T> The data captured by the {@code TimestampedData} object. Can be any Object.
 */
public class TimestampedData<T> {
    private T data;
    private ZonedDateTime timestamp;

    /**
     * Creates a {@code TimestampedData} from a data value and a timestamp.
     * <p>
     * Throws {@code IllegalArgumentException} if timestamp is {@code null}.
     *
     * @param data      The data to be stored
     * @param timestamp The timestamp corresponding to this data value, represented as a {@code ZonedDateTime}
     */
    public TimestampedData(T data, ZonedDateTime timestamp) {
        if (null == timestamp) {
            throw new IllegalArgumentException("timestamp cannot be null");
        }

        this.data = data;
        this.timestamp = timestamp;
    }

    /**
     * Creates a {@code TimestampedData} from a data value alone, setting the timestamp equal to the current time.
     *
     * @param data The data to be stored
     */
    public TimestampedData(T data) {
        this(data, ZonedDateTime.now());
    }

    // PARTICIPANTS: implement getter for data here
    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return String.format("{data: %s | timestamp: %s}", data, timestamp);
    }
}
