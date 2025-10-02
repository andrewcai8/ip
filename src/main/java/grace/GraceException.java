package grace;

/**
 * Represents exceptions specific to the Grace application.
 * Used to indicate invalid commands or errors when handling tasks.
 */
public class GraceException extends Exception {

    /**
     * Creates a new GraceException with the specified message
     *
     * @param message the message for this exception
     */
    public GraceException(String message) {
        super(message);
    }
}
