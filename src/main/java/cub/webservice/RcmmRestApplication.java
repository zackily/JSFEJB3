package cub.webservice;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/rest")
public class RcmmRestApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(com.wordnik.swagger.jaxrs.listing.ApiListingResource.class);
        classes.add(com.wordnik.swagger.jaxrs.listing.ApiDeclarationProvider.class);
        classes.add(com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON.class);
        classes.add(com.wordnik.swagger.jaxrs.listing.ResourceListingProvider.class);
        classes.add(RcmmWebService.class);
        return classes;
    }

}
