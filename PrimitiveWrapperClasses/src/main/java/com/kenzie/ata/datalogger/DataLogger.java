package com.kenzie.ata.datalogger;

import java.time.ZonedDateTime;

/**
 * A class that simulates logging a series of data capturing integers representing data points collected over
 * a period of ~a day. The planned implementation is for the logData() method implementation to be completed
 * using the TimestampedData class.
 *
 * The data being collected are ints.
 */
public class DataLogger {
    /**
     * Runs the data logging simulator. Run this in IntelliJ to see your data printed out.
     *
     * @param args ignored
     */
    public static void main(String[] args) {
        int[] datas = {1, 1, 2, 3, 5, 8, 13, 21};
        ZonedDateTime[] timestamps = {
            ZonedDateTime.parse("2019-08-03T12:31:09Z[UTC]"),
            ZonedDateTime.parse("2019-08-04T12:31:08Z[UTC]"),
            ZonedDateTime.parse("2019-08-05T12:31:00Z[UTC]"),
            ZonedDateTime.parse("2019-08-06T12:31:06Z[UTC]"),
            ZonedDateTime.parse("2019-08-08T12:31:05Z[UTC]"),
            ZonedDateTime.parse("2019-08-10T12:31:01Z[UTC]"),
            ZonedDateTime.parse("2019-08-13T12:31:11Z[UTC]"),
            ZonedDateTime.parse("2019-08-19T12:31:09Z[UTC]"),
        };

        DataLogger dataLogger = new DataLogger();
        dataLogger.logData(datas, timestamps);
    }

    /**
     * Prints the provided data / timestamps to the console. The two arrays must be of the same length, and the
     * int at the n-th index of the dataValues array corresponds to the timestamp at the n-th index of the
     * timestampValues array.
     *
     * PARTICIPANTS: finish the implementation of this method using instances of the TimestampedData class.
     *
     * @param dataValues      array of int data to be stored in TimestampedData
     * @param timestampValues array of timestamps corresponding to the same index data values in dataValues.
     */
    public void logData(int[] dataValues, ZonedDateTime[] timestampValues) {
        if (null == dataValues) {
            throw new IllegalArgumentException("data array cannot be null");
        }

        if (null == timestampValues) {
            throw new IllegalArgumentException("timestamp array cannot be null");
        }

        // PARTICIPANTS - implement logic to create a TimestampedData from each dataValues/timestampValues pair
        // and print each to the console (using System.out.println())

        // PARTICIPANTS - remove these lines once you've implemented the logic above
        System.out.println("NOTE: logData() isn't fully implemented yet.");
    }
}
