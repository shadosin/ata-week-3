package com.kenzie.ata.recommender.movie;

import com.kenzie.ata.recommender.ReadOnlyDao;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.List;

/**
 * Lookup Prime Video movies by id.
 */
public class PrimeVideoDao implements ReadOnlyDao<Long, PrimeVideo> {

    private File videoFile;

    /**
     * Instantiates a new Prime video service client.
     *
     * @param videoFile the video file
     */
    public PrimeVideoDao(File videoFile) {
        this.videoFile = videoFile;
    }

    /**
     * Get a specific PrimeVideo by its id.
     *
     * @param key The unique key to lookup a PrimeVideo based on.
     * @return The PrimeVideo with id, key.
     */
    @Override
    public PrimeVideo get(Long key) {
        String[] lines = readVideoFiles();
        for (String movieLine : lines) {
            String[] movieData = movieLine.split(",");

            long id = Long.parseLong(movieData[0].trim());
            if (key.equals(id)) {

                String title = movieData[1].trim();
                Duration duration = Duration.ofMinutes(Long.parseLong(movieData[2].trim()));
                int yearReleased = Integer.parseInt(movieData[3].trim());
                Long mostSimilarPrimeVideo = null;
                if (movieData.length > 4) {
                    mostSimilarPrimeVideo = Long.parseLong(movieData[4].trim());
                }

                return PrimeVideo.builder()
                                 .withId(id)
                                 .withTitle(title)
                                 .withDuration(duration)
                                 .withYearReleased(yearReleased)
                                 .withMostSimilarPrimeVideo(mostSimilarPrimeVideo)
                                 .build();
            }
        }

        return null;
    }

    private String[] readVideoFiles() {
        try {
            List<String> lines = FileUtils.readLines(videoFile, Charset.defaultCharset());
            return lines.toArray(new String[lines.size()]);
        } catch (IOException e) {
            throw new StorageException("Unable to access movie data.", e);
        }
    }
}
