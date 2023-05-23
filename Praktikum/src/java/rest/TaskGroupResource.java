package rest;
import database.TaskGroup;
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
import jakarta.persistence.*;

@Path("taskgroup")
public class TaskGroupResource implements Serializable{

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(TaskGroupAdapter ta){
        TaskGroup taskGroup = ta.toTaskGroup();
        URI location = URI.create("/taskGroup?id=" + taskGroup.getId());
        ResponseBuilder rb = Response.created(location);
        URI delLocLink = URI.create("/taskGroup/delete?id=" + taskGroup.getId());
        rb.link(delLocLink, "delete");

        return rb.build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@QueryParam("id") Long id){
        TaskGroup taskGroup = new TaskGroup();
        taskGroup.setId(1l);
        taskGroup.setTitle("exampleTitleTaskGroup");
        taskGroup.setDescription("exampleShortDescrition");

        ResponseBuilder rb = Response.ok(taskGroup);
        return rb.build();
    }
    
    @Path("/id/{id}")
    @GET
    @Produces("application/json")
    public Response getSpecific(@QueryParam("id") int id) {
        Response.ResponseBuilder builder = Response.ok(TaskGroup.getTaskGroupById(id));
        return builder.build();
    }

    @Path("/title/{title}")
    @GET
    @Produces("application/json")
    public Response getName(@QueryParam("title") String title) {
        Response.ResponseBuilder builder = Response.ok(TaskGroup.getTaskGroupByTitle(title));
        return builder.build();
    }

}
