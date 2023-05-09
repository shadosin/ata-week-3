package main.java.datalogger;

import java.time.ZonedDateTime;

/**
 * Class storing String data with an associated timestamp for when that data was collected.
 *
 * NOTE: This is a design approach that only solves the timestamp requirement for a single
 * data type per class. If we had 30 different data types we wanted to associate with a timestamp,
 * we'd have to create 30 separate Timestamped[Type] classes, which sounds exhausting.
 *
 * This is a reference class to help us figure out what the generic {@code TimestampedData<T>} class
 * would look like, which can handle all 30 of those hypothetical data types with a single class!
 */
public class TimestampedString {
    private String data;
    private ZonedDateTime timestamp;

    /**
     * Creates a {@code TimestampedString} from a String value and a timestamp.
     * <p>
     * Throws {@code IllegalArgumentException} if timestamp is {@code null}.
     *
     * @param data      The String data to be stored
     * @param timestamp The timestamp corresponding to this data value, represented as a {@code ZonedDateTime}
     */
    public TimestampedString(String data, ZonedDateTime timestamp) {
        if (null == timestamp) {
            throw new IllegalArgumentException("timestamp cannot be null");
        }

        this.data = data;
        this.timestamp = timestamp;
    }

    /**
     * Creates a {@code TimestampedString} from a data value alone, setting the timestamp equal to the current time.
     *
     * @param data The data to be stored
     */
    public TimestampedString(String data) {
        this(data, ZonedDateTime.now());
    }

    public String getData() {
        return data;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return String.format("{data: %s | timestamp: %s}", data, timestamp);
    }
}
