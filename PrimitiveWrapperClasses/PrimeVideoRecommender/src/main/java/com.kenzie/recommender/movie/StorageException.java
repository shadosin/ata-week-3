package main.java.recommender.movie;

/**
 * Exception thrown when unable to properly interact with a storage resource.
 */
public class StorageException extends RuntimeException {

    // TODO: Use serialver to generate this
    private static final long serialVersionUID = 4L;

    /**
     * Creates a {@code StorageException} with specified message.
     *
     * @param message The error message
     */
    public StorageException(String message) {
        super(message);
    }

    /**
     * Creates a {@code StorageException} with specified message and caused-by exception.
     *
     * @param message The error message
     * @param t       The original cause of the exception
     */
    public StorageException(String message, Throwable t) {
        super(message, t);
    }
}
