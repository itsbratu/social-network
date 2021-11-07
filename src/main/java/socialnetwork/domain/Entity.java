package socialnetwork.domain;

import java.io.Serializable;

public class Entity<MAIL>{

    private MAIL mail;
    public MAIL getMail() {
        return mail;
    }
    public void setMail(MAIL mail) {
        this.mail = mail;
    }
}