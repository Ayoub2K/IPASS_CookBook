package CookBook.security;

import CookBook.webservices.LoginResource;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.crypto.MacProvider;

import javax.annotation.Priority;
import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.security.Key;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class LoginFilter implements ContainerRequestFilter {
    public static final Key key = MacProvider.generateKey();

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        String authHeader = containerRequestContext.getHeaderString("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring("Bearer ".length()).trim();
            JwtParser parser = Jwts.parser().setSigningKey(key);
            String username = parser.parseClaimsJws(token).getBody().getSubject();
            containerRequestContext.setSecurityContext(new mySecurityContext(new User(username)));
        }
    }
}
