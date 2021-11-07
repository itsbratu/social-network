package socialnetwork.domain.validators;

/**
 * Custom class for catching Domain fields exceptions
 */
public class ValidationException extends RuntimeException {
    public ValidationException() {
    }

    public ValidationException(String message) {
        super(message);
    }
}
