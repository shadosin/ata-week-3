package com.kenzie.ata.reversedstring;

/**
 * Reverse a String and act like a <code>CharSequence</code>.
 * <p>
 * Change this class definition to implement CharSequence.
 */
public class ReversedString implements CharSequence {
    private final String chars;

    /**
     * Implement this constructor during the interfaces prework.
     *
     * @param chars The String of chars to be reversed
     */
    public ReversedString(final String chars) {
        StringBuilder sb = new StringBuilder(chars);
        this.chars = sb.toString();

    }

    @Override
    public int length() {
        return chars.length();
    }

    @Override
    public char charAt(int index) {
        return chars.charAt(length()-index-1);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        String substring = chars.substring(start, end);
        return new ReversedString(substring);
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder(chars);
        return sb.reverse().toString();
    }
}

