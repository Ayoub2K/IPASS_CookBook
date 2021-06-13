package CookBook.security;

import java.security.Principal;

public class User implements Principal {
    private String naam;

    public User(String naam) {
        this.naam = naam;
    }

    public String getName() {
        return naam;
    }

}
