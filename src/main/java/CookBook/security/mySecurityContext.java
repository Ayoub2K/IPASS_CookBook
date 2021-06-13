package CookBook.security;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

public class mySecurityContext implements SecurityContext {
    private User user;

    public mySecurityContext(User user){
        this.user = user;
    }

    @Override
    public Principal getUserPrincipal() {
        return this.user;
    }

    @Override
    public boolean isUserInRole(String s) {
        if(s != null && s.equals("editor")){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean isSecure() {
        return true;
    }

    @Override
    public String getAuthenticationScheme() {
        return null;
    }
}
