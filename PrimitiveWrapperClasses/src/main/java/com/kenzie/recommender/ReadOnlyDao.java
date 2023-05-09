package com.kenzie.recommender;

/**
 * Access a single item of data via a unique key.
 *
 * @param <K> The type of the key.
 * @param <V> The type of the value or data.
 */
public interface ReadOnlyDao<K, V> {

    /**
     * Get a specific value of type V for a specific key of type K.
     *
     * @param key The unique key to lookup a value based on.
     * @return The value for the key.
     */
    V get(K key);
}
