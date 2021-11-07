package socialnetwork.domain.validators;

/**
 * Custom class for catching Repo exceptions
 */
public class RepoException extends RuntimeException{
    public RepoException() {
    }

    public RepoException(String message) {
        super(message);
    }
}