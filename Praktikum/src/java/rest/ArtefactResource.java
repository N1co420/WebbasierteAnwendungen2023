package rest;

import classes.Artefact;
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
import java.io.Serializable;

@Path("artefact")
public class ArtefactResource implements Serializable{

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
        artefact.setId(1l);
        artefact.setTitle("exampleTitle");
        artefact.setDescription("exampleDescription");
        artefact.setPlannedWorkTime("2 hours");

        ResponseBuilder rb = Response.ok(artefact);

        return rb.build();
    }
}
