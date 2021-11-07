package socialnetwork.domain;

/**
 * Class that represents 'User' entity for our app
 */
public class Utilizator extends Entity<String>{
    /**
     * firstName - String
     * lastName - String
     */
    private String firstName;
    private String lastName;

    /**
     *  Constructor with parameters for 'Utilizator' class
     * @param firstName - String
     * @param lastName - String
     */
    public Utilizator(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Getter for firstName
     * @return - firstName - String
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter for firstName
     * @param firstName - String
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter for lastName
     * @return lastName - String
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter for lastName
     * @param lastName - String
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}