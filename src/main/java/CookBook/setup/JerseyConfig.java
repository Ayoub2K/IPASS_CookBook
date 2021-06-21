package CookBook.setup;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("restservices/")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {packages("CookBook");
//        register(MultiPartFeature.class);
//        register(RolesAllowedDynamicFeature.class);
    }
}
