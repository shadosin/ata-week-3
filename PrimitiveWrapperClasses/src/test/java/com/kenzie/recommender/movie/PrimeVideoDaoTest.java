package com.kenzie.recommender.movie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PrimeVideoDaoTest {

    private File kidsMovies =new File(ClassLoader.getSystemResource("kidsmovies.csv").getPath());
    private PrimeVideoDao primeVideoDao;

    @BeforeEach
    public void setUp() {
        primeVideoDao = new PrimeVideoDao(kidsMovies);
    }

    @Test
    public void get_nonExistentPrimeVideo_returnsNull() {
        // GIVEN
        long nonExistentMovieId = 89;

        // WHEN
        PrimeVideo primeVideo = primeVideoDao.get(nonExistentMovieId);

        // THEN
        assertNull(primeVideo, "Null should be returned when movieId does not exist");
    }

    @Test
    public void get_movieWithoutRelated_returnsDeserializedPrimeVideo() {
        // GIVEN
        long movieId = 4;
        String expectedTitle = "Alvin and the Chipmunks";
        Duration expectedDuration = Duration.ofMinutes(92);
        int expectedYearReleased = 2007;

        // WHEN
        PrimeVideo primeVideo = primeVideoDao.get(movieId);

        // THEN
        assertEquals(movieId, primeVideo.getId(), "Expected movieId " + movieId);
        assertEquals(expectedTitle, primeVideo.getTitle(), "Expected title " + expectedTitle);
        assertEquals(expectedDuration, primeVideo.getDuration(), "Expected duration " + expectedDuration);
        assertEquals(expectedYearReleased, primeVideo.getYearReleased(),
                     "Expected year released " + expectedYearReleased);
        assertNull(primeVideo.getMostSimilarId(), "No primeVideo is similar to this primeVideo.");
    }

    @Test
    public void get_movieWithRelated_returnsDeserializedPrimeVideo() {
        // GIVEN
        long movieId = 11;
        String expectedTitle = "102 Dalmations";
        Duration expectedDuration = Duration.ofMinutes(100);
        int expectedYearReleased = 2000;
        long expectedMostSimilarId = 6;

        // WHEN
        PrimeVideo primeVideo = primeVideoDao.get(movieId);

        // THEN
        assertEquals(movieId, primeVideo.getId(), "Expected movieId " + movieId);
        assertEquals(expectedTitle, primeVideo.getTitle(), "Expected title " + expectedTitle);
        assertEquals(expectedDuration, primeVideo.getDuration(), "Expected duration " + expectedDuration);
        assertEquals(expectedYearReleased, primeVideo.getYearReleased(),
                     "Expected year released " + expectedYearReleased);
        assertEquals(expectedMostSimilarId, primeVideo.getMostSimilarId(),
                     "Expected most similar ID " + expectedMostSimilarId);
    }

}
