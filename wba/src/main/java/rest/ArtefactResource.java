package rest;
import classes.Artefact;
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

@Path("artefact")
public class ArtefactResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(ArtefactAdapter aa) {

        Artefact artefact = aa.toArtefact();
        // perform database or other operations to save the artefact
        // ...

        URI location = URI.create("/artefact?id=" + artefact.getId());
        ResponseBuilder rb = Response.created(location);
        URI delLocLink = URI.create("/artefact/delete?id=" + artefact.getId());
        rb.link(delLocLink, "delete");

        return rb.build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@QueryParam("id") Long id) {

        Artefact artefact = new Artefact();
        artefact.setId(1L);
        artefact.setTitle("exampleTitle");
        artefact.setDescription("exampleDescription");
        artefact.setPlannedWorkTime("2 hours");

        ResponseBuilder rb = Response.ok(artefact);

        return rb.build();
    }
}
