package rest;

import classes.Project;
import java.io.Serializable;
import java.net.URI;
import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

@Path("project")
public class ProjectResource implements Serializable {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(ProjectAdapter pa) {


        //adapter in Objekt umwandeln - Adapater zur Umwandlung des Datums von JSON und für Referenzen notwendig
        Project proj= pa.toProject();
        URI location = URI.create("/projekt?id=" + proj.getId());  // hier ann man das angelegte abrufen
        ResponseBuilder rb = Response.created(location);
        // Example for createing a HATEOAS link
        URI delLocLink = URI.create("/projekt/delete?id=" + proj.getId()); //hier kann man das angelegte löschen
        rb.link(delLocLink, "delete");
        return rb.build();

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@QueryParam("id") int id) {
        Project proj = new Project();
        proj.setId(1);
        proj.setTitle("exampleTitel");
        ResponseBuilder rb = Response.ok(proj);

        return rb.build();
    }
}
