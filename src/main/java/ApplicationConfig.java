import Resource.BrandstofResource;
import Resource.UserResource;
import Socket.TimeSocket;

import javax.ws.rs.core.Application;
import java.util.Set;

@javax.ws.rs.ApplicationPath("resources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(UserResource.class);
        resources.add(BrandstofResource.class);
        resources.add(TimeSocket.class);
    }
}