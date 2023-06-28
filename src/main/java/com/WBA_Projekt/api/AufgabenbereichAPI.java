package com.WBA_Projekt.api;

import com.WBA_Projekt.classes.Aufgabenbereich;
import com.WBA_Projekt.classes.Projekt;
import com.WBA_Projekt.classes.Projekt_Aufgabenbereich;

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
@Path("/aufgabenbereich")
@TransactionManagement(TransactionManagementType.BEAN)
public class AufgabenbereichAPI {

    EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("JPA_ExamplePU");
    EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

    @Resource
    private UserTransaction utx;

    // Gets all the aufgabenbereiche or if a specific aufgabenbereich is requested returns that aufgabenbereich
    @GET
    @Produces("application/json")
    public Response getAufgabenbereiche(@QueryParam("id") String id) throws SystemException {
        if (id == null) {
            List<Aufgabenbereich> aufgabenbereiche = em.createNamedQuery("Aufgabenbereich.findAll", Aufgabenbereich.class).getResultList();
            if (aufgabenbereiche.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "*").build();
            }
            return Response.ok(aufgabenbereiche).header("Access-Control-Allow-Origin", "*").build();
        }

        try {
            Aufgabenbereich aufgabenbereich = em.find(Aufgabenbereich.class, Integer.parseInt(id));
            if (aufgabenbereich == null) {
                return Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "*").build();
            }
            return Response.ok(aufgabenbereich).header("Access-Control-Allow-Origin", "*").build();
        } catch (NumberFormatException e) {
            utx.rollback();
            return Response.status(Response.Status.BAD_REQUEST).header("Access-Control-Allow-Origin", "*").build();
        }
    }

    // Adds a new aufgabenbereich
    @POST
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAufgabenbereich(Aufgabenbereich aufgabenbereich) throws SystemException {
        // Check if the aufgabenbereich already exists
        Aufgabenbereich existingAufgabenbereich = em.find(Aufgabenbereich.class, aufgabenbereich.getAufgabenbereichID());
        if (existingAufgabenbereich != null) {
            return Response.status(Response.Status.CONFLICT).header("Access-Control-Allow-Origin", "*").build();
        }

        // Check if the aufgabenbereich is valid
        if (aufgabenbereich.getName() == null ||
                aufgabenbereich.getBeschreibung() == null || aufgabenbereich.getBeschreibung().length() > 255) {
            return Response.status(Response.Status.BAD_REQUEST).header("Access-Control-Allow-Origin", "*").build();
        }

        // Add the aufgabenbereich
        try {
            utx.begin();
            em.persist(aufgabenbereich);
            utx.commit();
        } catch (Exception e) {
            utx.rollback();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header("Access-Control-Allow-Origin", "*").build();
        }
        return Response.status(Response.Status.NO_CONTENT).header("Access-Control-Allow-Origin", "*").build();
    }

    // Updates a aufgabenbereich
    @PATCH
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAufgabenbereich(Aufgabenbereich aufgabenbereich) throws SystemException {
        // Check if the aufgabenbereich exists
        Aufgabenbereich existingAufgabenbereich = em.find(Aufgabenbereich.class, aufgabenbereich.getAufgabenbereichID());
        if (existingAufgabenbereich == null) {
            return Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "*").build();
        }

        // Check if the aufgabenbereich is valid
        if (aufgabenbereich.getBeschreibung().length() > 255) {
            return Response.status(Response.Status.BAD_REQUEST).header("Access-Control-Allow-Origin", "*").build();
        }

        // Update the aufgabenbereich
        try {
            utx.begin();
            em.merge(aufgabenbereich);
            utx.commit();
        } catch (Exception e) {
            utx.rollback();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header("Access-Control-Allow-Origin", "*").build();
        }
        // Get the updated aufgabenbereich
        Aufgabenbereich updatedAufgabenbereich = em.find(Aufgabenbereich.class, aufgabenbereich.getAufgabenbereichID());
        return Response.ok(updatedAufgabenbereich).header("Access-Control-Allow-Origin", "*").build();
    }

    // Deletes a aufgabenbereich
    @DELETE
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response deleteAufgabenbereich(@PathParam("id") String id) throws SystemException {
        try {
            // Check if the aufgabenbereich exists
            Aufgabenbereich existingAufgabenbereich = em.find(Aufgabenbereich.class, Integer.parseInt(id));
            if (existingAufgabenbereich == null) {
                return Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "*").build();
            }

            // Delete the aufgabenbereich
            try {
                utx.begin();
                em.remove(existingAufgabenbereich);
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

    // Add Projekt to Aufgabenbereich
    @POST
    @Path("/{id}/projekt")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProjekt(@PathParam("id") String id, Projekt_Aufgabenbereich projekt_aufgabenbereich) {
        try {
            // Check if the aufgabenbereich exists
            Aufgabenbereich existingAufgabenbereich = em.find(Aufgabenbereich.class, Integer.parseInt(id));
            if (existingAufgabenbereich == null) {
                return Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "*").build();
            }
            // Check if the projekt exists
            Projekt existingProjekt = em.find(Projekt.class, projekt_aufgabenbereich.getProjektId());
            if (existingProjekt == null) {
                return Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "*").build();
            }

            try {
                utx.begin();
                em.persist(projekt_aufgabenbereich);
                utx.commit();
            } catch (Exception e) {
                utx.rollback();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header("Access-Control-Allow-Origin", "*").build();
            }
            return Response.status(Response.Status.NO_CONTENT).header("Access-Control-Allow-Origin", "*").build();
        } catch (NumberFormatException | SystemException e) {
            return Response.status(Response.Status.BAD_REQUEST).header("Access-Control-Allow-Origin", "*").build();
        }
    }

    // Delete Projekt from Aufgabenbereich
    @DELETE
    @Path("/{id}/projekt/{aid}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProjekt(@PathParam("id") String id, @PathParam("aid") String aid) throws SystemException {
        try {
            // Check if the Projekt_Aufgabenbereich exists
            Projekt_Aufgabenbereich existingProjekt_Aufgabenbereich = em.find(Projekt_Aufgabenbereich.class, Integer.parseInt(aid));
            if (existingProjekt_Aufgabenbereich == null) {
                return Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "*").build();
            }
            // Delete the Projekt_Aufgabenbereich
            try {
                utx.begin();
                em.remove(existingProjekt_Aufgabenbereich);
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
