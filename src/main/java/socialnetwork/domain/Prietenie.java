package socialnetwork.domain;

import java.time.LocalDateTime;

/**
 * Class that represents the connection between two network users
 */
public class Prietenie extends Entity<Tuple<String,String>> {

    public String user_mail1;
    public String user_mail2;

    /**
     * Constructor with parameters for 'Prietenie' class
     * @param user_mail1 - String (first user email address)
     * @param user_mail2 - String (second user email address)
     */
    public Prietenie(String user_mail1 , String user_mail2) {
        this.user_mail1 = user_mail1;
        this.user_mail2 = user_mail2;
    }
}
