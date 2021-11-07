package socialnetwork.domain.validators;

import socialnetwork.domain.Utilizator;

import java.util.regex.Pattern;

/**
 * Class for validating fields for an 'Utilizator' entity
 */
public class UtilizatorValidator implements Validator<Utilizator> {
    @Override
    /**
     * Function that validates 'Utilizator' entity
     */
    public void validate(Utilizator entity) throws ValidationException {
        String mail = entity.getMail();
        String firstName = entity.getFirstName();
        String lastName = entity.getLastName();
        //We save errors msg in this variable
        String errors = "";

        //Regex for mail and name validation
        String regexMail = "^[A-Za-z0-9+_.-]+@(.+)$";
        String regexName = "[a-zA-Z]+";

        if (!mail.matches(regexMail)) {
            errors += "Invalid mail format!\n";
        }
        if (firstName.equals("")) {
            errors += "First name should not be null!\n";
        }else{
            if (!firstName.matches(regexName)) {
                errors += "First name should contain only letters , digits or -`_ !\n";
            }
        }
        if (lastName.equals("")) {
            errors += "Last name should not be null!\n";
        }else{
            if (!lastName.matches(regexName)) {
                errors += "Last name should contain only letters , digits or -_`!\n";
            }
        }
        if (!errors.equals("")) {
            throw new ValidationException(errors);
        }
        return;
    }
}
