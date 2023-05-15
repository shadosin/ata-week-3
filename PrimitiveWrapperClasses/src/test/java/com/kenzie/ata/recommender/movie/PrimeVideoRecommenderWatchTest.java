package com.kenzie.ata.recommender.movie;

import com.kenzie.ata.recommender.MostRecentlyUsed;
import com.kenzie.ata.recommender.ReadOnlyDao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class PrimeVideoRecommenderWatchTest {
    private File kidsMovies = new File(ClassLoader.getSystemResource("kidsmovies.csv").getPath());

    // PARTICIPANT -- Update the generic types in PrimeVideoRecommender
    private MostRecentlyUsed<PrimeVideo> mostRecentlyViewed;
    private ReadOnlyDao<Long, PrimeVideo> readOnlyDAO;
    private Random random;

    private PrimeVideoRecommender primeVideoRecommender;

    @BeforeEach
    public void setUp() {
        mostRecentlyViewed = new MostRecentlyUsed<>(3);
        readOnlyDAO = new PrimeVideoDao(kidsMovies);
        // Using a seed guarantees us the results of a sequence of calls to nextInt
        random = new Random(1);

        primeVideoRecommender = new PrimeVideoRecommender((MostRecentlyUsed<PrimeVideo>) mostRecentlyViewed, (ReadOnlyDao<Long, PrimeVideo>) readOnlyDAO, random);
    }

    @Test
    public void watch_nonExistentPrimeVideo_throwIllegalArgumentsException() {
        // GIVEN
        long nonExistentMovie = 29;

        // WHEN + THEN
        assertThrows(IllegalArgumentException.class, () -> primeVideoRecommender.watch(nonExistentMovie));
    }

    // PARTICIPANTS - Finish writing this unit test.
    @Test
    public void watch_watchPrimeVideo_addToMostRecentlyViewed() {
        // GIVEN
        long movieId = 9;
        PrimeVideo expectedVideo = readOnlyDAO.get(movieId);

        primeVideoRecommender.watch(movieId);

        assertEquals(expectedVideo.getId(), mostRecentlyViewed.get(0).getId());

    }
}
