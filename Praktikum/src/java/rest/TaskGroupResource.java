package rest;
import classes.TaskGroup;
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
        taskGroup.setShortDescription("exampleShortDescrition");

        ResponseBuilder rb = Response.ok(taskGroup);
        return rb.build();
    }

}
