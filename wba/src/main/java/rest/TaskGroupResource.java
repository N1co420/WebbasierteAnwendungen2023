package rest;
import classes.TaskGroup;
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
@Path("taskgroup")
public class TaskGroupResource {

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
        taskGroup.setId(10L);
        taskGroup.setTitle("exampleTitleTaskGroup");
        taskGroup.setShortDescription("exampleShortDescrition");

        ResponseBuilder rb = Response.ok(taskGroup);
        return rb.build();
    }

}
