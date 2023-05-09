package com.kenzie.ata.reversedstring;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReversedStringTest {

    @Test
    public void charAt_firstIndex_returnsFirstChar() {
        // GIVEN - "nozama"
        CharSequence sequence = new String("amazon");

        // WHEN - call charAt for first index
        char c = sequence.charAt(0);

        // THEN - return the last character of the original String
        assertEquals('n', c, "Expected the first index of the reversed string to be the last " +
            "character of the original string.");
    }

    @Test
    public void charAt_lastIndex_returnsLastChar() {
        // GIVEN - "nozama"
        CharSequence sequence = new String("amazon");

        // WHEN - call charAt for last index
        char c = sequence.charAt(sequence.length() - 1);

        // THEN - return the first character of the original String
        assertEquals('a', c, "Expected the last index of the reversed string to be the first " +
            "character of the original string.");
    }

    @Test
    public void charAt_middleIndex_returnsMiddleChar() {
        // GIVEN - "noz*ama"
        CharSequence sequence = new String("ama*zon");

        // WHEN - call charAt for middle index
        char c = sequence.charAt(3);

        // THEN - middle character of the reversed & original String are the same
        assertEquals('*', c, "Expected the middle index of the reversed string to be the same " +
            "character from the original string.");
    }

    @Test
    public void charAt_invalidIndex_expectIndexOutOfBoundsException() {
        // GIVEN - "nozama"
        CharSequence sequence = new String("amazon");

        // WHEN & THEN - calling charAt with an index beyond its length results in an exception
        assertThrows(IndexOutOfBoundsException.class, () -> sequence.charAt(sequence.length()),
                     "Expected a IndexOutOfBoundsException to be thrown when accessing an invalid index of " +
                         "the string.");
    }

    @Test
    public void length_emptyString_returnsZero() {
        // GIVEN - ""
        CharSequence sequence = new String("");

        // WHEN - call to get the length
        int length = sequence.length();

        //THEN - the empty string has length 0
        assertEquals(0, length, "Expected an empty string to have length zero.");
    }

    @Test
    public void length_nonemptyString_returnsCorrectLength() {
        // GIVEN - "nozama"
        CharSequence sequence = new String("amazon");

        // WHEN - call to get the length
        int length = sequence.length();

        //THEN - the string has length 6
        assertEquals(6, length, "Expected a non empty string to have non zero length.");
    }

    @Test
    public void length_nonemptyString_returnsSameLengthAsNonReversedString() {
        // GIVEN - "nozama"
        CharSequence sequence = new String("amazon");

        // WHEN - call to get the length
        int length = sequence.length();

        //THEN - the original string, and reversed string have the same length
        assertEquals("amazon".length(), length, "Expected a non empty string to have non zero length.");
    }

    @Test
    public void subSequence_validSubSequence_returnsCorrectSubsequence() {
        // GIVEN - "nozama"
        CharSequence sequence = new String("amazon");

        //WHEN - ask for the first 3 letters
        CharSequence subsequence = sequence.subSequence(0, 3);

        //THEN - the first three letters should be the last three letters of the original in reverse order
        assertEquals("noz", subsequence.toString(), "Expected the first three letters to be the last three" +
            "letters of the original in reverse order");
    }

    @Test
    public void subSequence_entireStringAsSubSequence_returnsCorrectSubsequence() {
        // GIVEN - "nozama"
        CharSequence sequence = new String("amazon");

        //WHEN - ask for the entire sequence
        CharSequence subsequence = sequence.subSequence(0, sequence.length());

        //THEN - the first three letters should be the last three letters of the original in reverse order
        assertEquals(sequence.toString(), subsequence.toString(),
                     "Expected the subsequence to be equal to the original sequence.");
    }

    @Test
    public void subSequence_invalidStartIndex_throwsIndexOutOfBounds() {
        // GIVEN - "nozama"
        CharSequence sequence = new String("amazon");

        // WHEN & THEN - calling subsequence with an invalid start index results in an exception
        assertThrows(IndexOutOfBoundsException.class, () -> sequence.subSequence(-1, 3),
                     "Expected an IndexOutOfBoundsException to be thrown when accessing a negative index of " +
                         "the string.");
    }

    @Test
    public void subSequence_invalidEndIndex_throwsIndexOutOfBounds() {
        // GIVEN - "nozama"
        CharSequence sequence = new String("amazon");

        // WHEN & THEN - calling subsequence with an invalid start index results in an exception
        assertThrows(IndexOutOfBoundsException.class, () -> sequence.subSequence(0, sequence.length() + 1),
                     "Expected an IndexOutOfBoundsException to be thrown when accessing an index larger than " +
                         "the length of the string.");
    }

    @Test
    public void toString_emptyString_returnsEmptyString() {
        // GIVEN - ""
        CharSequence sequence = new String("");

        // WHEN - call toString to get the string value
        String stringValue = sequence.toString();

        // THEN - the returned string should be empty
        assertEquals("", stringValue, "Expected toString to return an empty string.");
    }

    @Test
    public void toString_nonemptyString_returnsReversedString() {
        // GIVEN - "nozama"
        CharSequence sequence = new String("amazon");

        // WHEN - call toString to get the string value
        String stringValue = sequence.toString();

        // THEN - the returned string should be the reverse of what was passed to the object
        assertEquals("nozama", stringValue, "Expected toString to return the original string in " +
            "reverse order.");
    }
}
