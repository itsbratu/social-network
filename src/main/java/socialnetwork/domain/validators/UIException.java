package socialnetwork.domain.validators;

/**
 * Custom class for catching UI exceptions
 */
public class UIException extends RuntimeException{
    public UIException() {
    }

    public UIException(String message) {
        super(message);
    }
}
