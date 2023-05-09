package com.kenzie.recommender.movie;

import java.time.Duration;

/**
 * PrimeVideo watched on Prime Video.
 */
public class PrimeVideo {

    /**
     * The unique identifying number of this movie.
     */
    private long id;
    /**
     * Title of the film.
     */
    private String title;
    /**
     * Length of the video including credits.
     */
    private Duration duration;
    /**
     * The year the film was first released publicly in any country.
     */
    private int yearReleased;
    /**
     * The identifier of the most similar movie on Prime Video to this movie.
     */
    private Long mostSimilarPrimeVideo;

    private PrimeVideo() {
    }

    /**
     * Builder builder.
     *
     * @return the builder
     */
    public static Builder builder() {
        return new Builder();
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Duration getDuration() {
        return duration;
    }

    public int getYearReleased() {
        return yearReleased;
    }

    public Long getMostSimilarId() {
        return mostSimilarPrimeVideo;
    }

    /**
     * Builder for constructing a PrimeVideo.
     */
    public static class Builder {
        private long id;
        private String title;
        private Duration duration;
        private int yearReleased;
        private Long mostSimilarPrimeVideo;

        /**
         * With id builder.
         *
         * @param pId the id
         * @return the builder
         */
        public Builder withId(long pId) {
            this.id = pId;
            return this;
        }

        /**
         * With title builder.
         *
         * @param pTitle the title
         * @return the builder
         */
        public Builder withTitle(String pTitle) {
            this.title = pTitle;
            return this;
        }

        /**
         * With duration builder.
         *
         * @param pDuration the duration
         * @return the builder
         */
        public Builder withDuration(Duration pDuration) {
            this.duration = pDuration;
            return this;
        }

        /**
         * With year released builder.
         *
         * @param pYearReleased the year released
         * @return the builder
         */
        public Builder withYearReleased(int pYearReleased) {
            this.yearReleased = pYearReleased;
            return this;
        }

        /**
         * With most similar prime video builder.
         *
         * @param pMostSimilarPrimeVideo the most similar prime video
         * @return the builder
         */
        public Builder withMostSimilarPrimeVideo(Long pMostSimilarPrimeVideo) {
            this.mostSimilarPrimeVideo = pMostSimilarPrimeVideo;
            return this;
        }

        /**
         * Build prime video.
         *
         * @return the prime video
         */
        public PrimeVideo build() {
            PrimeVideo primeVideo = new PrimeVideo();
            primeVideo.id = id;
            primeVideo.title = title;
            primeVideo.duration = duration;
            primeVideo.yearReleased = yearReleased;
            primeVideo.mostSimilarPrimeVideo = mostSimilarPrimeVideo;
            return primeVideo;
        }
    }
}
