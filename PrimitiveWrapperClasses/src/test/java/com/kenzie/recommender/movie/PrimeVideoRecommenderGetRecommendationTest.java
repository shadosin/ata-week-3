package com.kenzie.recommender.movie;

import com.kenzie.recommender.MostRecentlyUsed;
import com.kenzie.recommender.ReadOnlyDao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PrimeVideoRecommenderGetRecommendationTest {
    private File kidsMovies = new File(ClassLoader.getSystemResource("kidsmovies.csv").getPath());

    // PARTICIPANT -- Update the generic types in PrimeVideoRecommender
    private MostRecentlyUsed<?> mostRecentlyViewed;
    private ReadOnlyDao<?, ?> readOnlyDAO;
    private Random random;

    private PrimeVideoRecommender primeVideoRecommender;

    @BeforeEach
    public void setUp() {
        mostRecentlyViewed = new MostRecentlyUsed<>(3);
        readOnlyDAO = new PrimeVideoDao(kidsMovies);
        // Using a seed guarantees us the results of a sequence of calls to nextInt
        random = new Random(1);

        primeVideoRecommender = new PrimeVideoRecommender(mostRecentlyViewed, readOnlyDAO, random);
    }

    @Test
    public void getRecommendation_noPrimeVideosWatched_returnNull() {
        // WHEN
        PrimeVideo recommended = primeVideoRecommender.getRecommendation();

        // THEN
        assertNull(recommended, "No videos watched, returns a null recommendation.");
    }

    // PARTICIPANTS: Complete this unit test
    @Test
    public void getRecommendation_singleVideoWatched_returnsRecommendation() {
        // GIVEN
        long[] moviesWatched = {1};
        long expectedRecommendation = 2;

        assertTrue(false, "Not yet implemented.");

    }

    @Test
    public void getRecommendation_videoSelectedHasNoSimilarVideo_returnsNull() {
        // GIVEN
        long[] moviesWatched = {5, 6, 8};
        watchPrimeVideos(moviesWatched);

        // WHEN
        PrimeVideo recommended = primeVideoRecommender.getRecommendation();

        assertNull(recommended, "Expected null to be returned when the video selected for recommendation " +
            "has no similar video.");
    }

    @Test
    public void getRecommendation_videoSelectedHasSimilarVideo_returnsSimilarVideo() {
        // GIVEN
        long[] moviesWatched = {2, 3, 6};
        watchPrimeVideos(moviesWatched);
        long expectedRecommendation = 1;

        // WHEN
        PrimeVideo recommended = primeVideoRecommender.getRecommendation();

        // THEN
        assertEquals(expectedRecommendation, recommended.getId(),
                     "Expected to recommend video with ID " + expectedRecommendation);
    }

    private void watchPrimeVideos(long[] videosWatched) {
        for (long id : videosWatched) {
            primeVideoRecommender.watch(id);
        }
    }
}
