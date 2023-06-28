package com.WBA_Projekt.api;

import com.WBA_Projekt.classes.Artefakt;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
@Path("/artefakt")
@TransactionManagement(TransactionManagementType.BEAN)
public class ArtefaktAPI {

    EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("JPA_ExamplePU");
    EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

    @Resource
    private UserTransaction utx;

    // Gets all the artefakte or if a specific artefakt is requested returns that artefakt
    @GET
    @Produces("application/json")
    public Response getArtefakte(@QueryParam("id") String id) {
        if (id == null) {
            List<Artefakt> artefakte = em.createNamedQuery("Artefakt.findAll", Artefakt.class).getResultList();
            if (artefakte.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "*").build();
            }
            return Response.ok(artefakte).header("Access-Control-Allow-Origin", "*").build();
        }
        try {
            Artefakt artefakt = em.find(Artefakt.class, Integer.parseInt(id));
            if (artefakt == null) {
                return Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "*").build();
            }
            return Response.ok(artefakt).header("Access-Control-Allow-Origin", "*").build();
        } catch (NumberFormatException e) {
            return Response.status(Response.Status.BAD_REQUEST).header("Access-Control-Allow-Origin", "*").build();
        }
    }

    // Adds a new artefakt
    @POST
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addArtefakt(Artefakt artefakt) throws SystemException {
        // Check if the artefakt already exists
        Artefakt existingArtefakt = em.find(Artefakt.class, artefakt.getArtefaktID());
        if (existingArtefakt != null) {
            return Response.status(Response.Status.CONFLICT).header("Access-Control-Allow-Origin", "*").build();
        }
        // Check if the artefakt is valid
        if (artefakt.getName() == null || artefakt.getBeschreibung() == null ||
                artefakt.getBeschreibung().length() > 255 || artefakt.getGeplanteZeit() < 0) {
            return Response.status(Response.Status.BAD_REQUEST).header("Access-Control-Allow-Origin", "*").build();
        }
        // Add the artefakt
        try {
            utx.begin();
            em.persist(artefakt);
            utx.commit();
        } catch (Exception e) {
            utx.rollback();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header("Access-Control-Allow-Origin", "*").build();
        }
        return Response.status(Response.Status.NO_CONTENT).header("Access-Control-Allow-Origin", "*").build();
    }

    // Updates a artefakt

    @PATCH
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateArtefakt(Artefakt artefakt) throws SystemException {
        // Check if the artefakt already exists
        Artefakt existingArtefakt = em.find(Artefakt.class, artefakt.getArtefaktID());
        if (existingArtefakt == null) {
            return Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "*").build();
        }
        // Check if the artefakt is valid
        if (artefakt.getBeschreibung().length() > 255 || artefakt.getGeplanteZeit() < 0) {
            return Response.status(Response.Status.BAD_REQUEST).header("Access-Control-Allow-Origin", "*").build();
        }
        // Update the artefakt
        try {
            utx.begin();
            em.merge(artefakt);
            utx.commit();
        } catch (Exception e) {
            utx.rollback();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header("Access-Control-Allow-Origin", "*").build();
        }
        // Get the artefakt
        Artefakt newArtefakt = em.find(Artefakt.class, artefakt.getArtefaktID());
        if (newArtefakt == null) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header("Access-Control-Allow-Origin", "*").build();
        }
        return Response.ok(newArtefakt).header("Access-Control-Allow-Origin", "*").build();
    }

    // Deletes a artefakt
    @DELETE
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response deleteArtefakt(@PathParam("id") String id) throws SystemException {
        // Check if the artefakt already exists
        System.out.println(id);
        try {
            Artefakt existingArtefakt = em.find(Artefakt.class, Integer.parseInt(id));
            if (existingArtefakt == null) {
                return Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "*").build();
            }
            // Delete the artefakt
            try {
                utx.begin();
                em.remove(existingArtefakt);
                utx.commit();
            } catch (Exception e) {
                utx.rollback();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header("Access-Control-Allow-Origin", "*").build();
            }
            return Response.status(Response.Status.NO_CONTENT).header("Access-Control-Allow-Origin", "*").build();
        } catch (NumberFormatException e) {
            return Response.status(Response.Status.BAD_REQUEST).header("Access-Control-Allow-Origin", "*").build();
        }
    }
}