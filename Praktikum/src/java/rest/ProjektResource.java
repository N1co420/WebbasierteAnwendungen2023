package rest;

import classes.Projekt;
import java.io.Serializable;
import java.net.URI;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;


/**
 * REST interface for executeing imports
 *
 * @author ffehring
 */
@Path("projekt")    //Pfad der Ressource wird gesetzt

public class ProjektResource implements Serializable {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(ProjektAdapter pa) {

       
             //adapter in Objekt umwandeln - Adapater zur Umwandlung des Datums von JSON und für Referenzen notwendig
            Projekt proj= pa.toProject();
            URI location = URI.create("/projekt?id=" + proj.getId());  // hier ann man das angelegte abrufen
            ResponseBuilder rb = Response.created(location);
            // Example for createing a HATEOAS link
            URI delLocLink = URI.create("/projekt/delete?id=" + proj.getId()); //hier kann man das angelegte löschen
            rb.link(delLocLink, "delete");
            return rb.build();


      
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@QueryParam("id") Long id) {
        Projekt proj = new Projekt();
        proj.setId(1l);
        proj.setTitel("exampleTitel");
        ResponseBuilder rb = Response.ok(proj);
        
        return rb.build();
    }
}
