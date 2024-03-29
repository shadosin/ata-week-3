package com.kenzie.ata.recommender.movie;

import com.kenzie.ata.recommender.MostRecentlyUsed;
import com.kenzie.ata.recommender.ReadOnlyDao;

import java.util.Random;

/**
 * Recommends a movie based on the most recently viewed movies in Prime Video.
 *
 * PARTICIPANTS: Replace the placeholders in the generics used in the class members and constructor with their proper
 * types.
 */
public class PrimeVideoRecommender {
    // PARTICIPANT -- Update the generic types in PrimeVideoRecommender
    private MostRecentlyUsed<PrimeVideo> mostRecentlyViewed;
    private ReadOnlyDao<Long, PrimeVideo> primeVideoDao;
    private Random random;

    /**
     * Instantiates a new Prime video recommender.
     *
     * @param mostRecentlyViewed the most recently viewed
     * @param primeVideoDao      the prime video dao
     * @param random             the random
     */
    // PARTICIPANT -- Update the generic types in PrimeVideoRecommender
    public PrimeVideoRecommender(MostRecentlyUsed<PrimeVideo> mostRecentlyViewed,
                                 ReadOnlyDao<Long, PrimeVideo> primeVideoDao, Random random) {
        this.mostRecentlyViewed = mostRecentlyViewed;
        this.primeVideoDao = primeVideoDao;
        this.random = random;
    }


    /**
     * Add a newly watched video to the mostRecentlyViewed. If the video doesn't exist throw an
     * IllegalArgumentException.
     *
     * @param videoId ID of the video that was watched on Prime Video
     */
    public void watch(long videoId) {
        // PARTICIPANT -- Implement watch()
        PrimeVideo video = primeVideoDao.get(videoId);
        if (video == null){
            throw new IllegalArgumentException("Invalid video ID");
        }
        mostRecentlyViewed.add(video);
    }

    /**
     * Selects a random video from the mostRecentlyViewed videos, and that video's most similar PrimeVideo
     * is returned as the recommendation. If the randomly selected video does not have a most similar
     * PrimeVideo, return null. If there are not most recently viewed movies, null will be returned.
     *
     * @return PrimeVideo to recommend watching.
     */
    public PrimeVideo getRecommendation() {
        // PARTICIPANT -- Implement getRecommendation()
        if(mostRecentlyViewed.getSize() == 0){
            return null;
        }

        PrimeVideo movie = mostRecentlyViewed.get(random.nextInt(mostRecentlyViewed.getSize()));
        Long mostSimilar = movie.getMostSimilarId();

        if(mostSimilar == null){
            return null;
        }
        PrimeVideo recommendation = primeVideoDao.get(mostSimilar);
        return recommendation;

    }
}
