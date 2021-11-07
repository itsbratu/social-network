package socialnetwork.domain.validators;

/**
 * Interface for Validator
 * @param <T> - generic entity
 */
public interface Validator<T> {
    void validate(T entity) throws ValidationException;
}