package com.WBA_Projekt.api;

import com.WBA_Projekt.classes.Artefakt;
import com.WBA_Projekt.classes.Projekt;
import com.WBA_Projekt.classes.Projekt_Artefakt;

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
@Path("/projekt")
@TransactionManagement(TransactionManagementType.BEAN)
public class ProjektAPI {

    EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("JPA_ExamplePU");
    EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

    @Resource
    private UserTransaction utx;

    // Gets all the projects or if a specific project is requested returns that project
    @GET
    @Produces("application/json")
    public Response getProjects(@QueryParam("id") String id) {
        if (id == null) {
            List<Projekt> projekte = em.createNamedQuery("Projekt.findAll", Projekt.class).getResultList();
            return Response.ok(projekte).header("Access-Control-Allow-Origin", "*").build();
        }
        try {
            Projekt projekt = em.find(Projekt.class, Integer.parseInt(id));
            if (projekt == null) {
                return Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "*").build();
            }
            return Response.ok(projekt).header("Access-Control-Allow-Origin", "*").build();
        } catch (NumberFormatException e) {
            return Response.status(Response.Status.BAD_REQUEST).header("Access-Control-Allow-Origin", "*").build();
        }
    }

    // Get newest Projects, if optional limit parameter is set, returns that amount of projects
    @GET
    @Path("/newest")
    @Produces("application/json")
    public Response getNewestProjects(@QueryParam("limit") String limit) {
        if (limit == null) {
            List<Projekt> projekte = em.createNamedQuery("Projekt.findNewest", Projekt.class).getResultList();
            return Response.ok(projekte).header("Access-Control-Allow-Origin", "*").build();
        }
        try {
            List<Projekt> projekte = em.createNamedQuery("Projekt.findNewest", Projekt.class).setMaxResults(Integer.parseInt(limit)).getResultList();
            return Response.ok(projekte).header("Access-Control-Allow-Origin", "*").build();
        } catch (NumberFormatException e) {
            return Response.status(Response.Status.BAD_REQUEST).header("Access-Control-Allow-Origin", "*").build();
        }
    }

    // Adds a new project
    @POST
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProject(Projekt projekt) throws SystemException {
        // Check if the project already exists
        Projekt existingProject = em.find(Projekt.class, projekt.getProjektID());
        if (existingProject != null) {
            return Response.status(Response.Status.CONFLICT).header("Access-Control-Allow-Origin", "*").build();
        }
        // Check if the project is valid
        if (projekt.getName() == null || projekt.getBeschreibung() == null ||
                projekt.getBeschreibung().length() > 255 || projekt.getStartDate() == null ||
                projekt.getLogoPath() == null) {
            return Response.status(Response.Status.BAD_REQUEST).header("Access-Control-Allow-Origin", "*").build();
        }
        // Add the project
        try {
            utx.begin();
            em.persist(projekt);
            utx.commit();
        } catch (Exception e) {
            utx.rollback();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header("Access-Control-Allow-Origin", "*").build();
        }
        existingProject = em.find(Projekt.class, projekt.getProjektID());
        if (existingProject == null) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header("Access-Control-Allow-Origin", "*").build();
        }
        return Response.ok(existingProject).header("Access-Control-Allow-Origin", "*").build();
    }

    @OPTIONS
    public Response options() {
        return Response.ok().header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").header("Access-Control-Allow-Headers", "Content-Type").build();
    }

    // Updates a project
    @PATCH
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProject(Projekt projekt) throws SystemException {
        // Check if the project already exists
        Projekt existingProject = em.find(Projekt.class, projekt.getProjektID());
        if (existingProject == null) {
            return Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "*").build();
        }
        // Check if the projekt is valid
        if (projekt.getBeschreibung().length() > 255) {
            return Response.status(Response.Status.BAD_REQUEST).header("Access-Control-Allow-Origin", "*").build();
        }

        // Update the project
        try {
            utx.begin();
            em.merge(projekt);
            utx.commit();
        } catch (Exception e) {
            utx.rollback();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header("Access-Control-Allow-Origin", "*").build();
        }
        // Get the updated project
        Projekt updatedProject = em.find(Projekt.class, projekt.getProjektID());
        return Response.ok(updatedProject).header("Access-Control-Allow-Origin", "*").build();
    }

    // Deletes a project
    @DELETE
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response deleteProject(@PathParam("id") String id) throws SystemException {
        // Check if the project already exists
        try {
            Projekt existingProject = em.find(Projekt.class, Integer.parseInt(id));
            if (existingProject == null) {
                return Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "*").build();
            }
            // Delete the project
            try {
                utx.begin();
                em.remove(existingProject);
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

    // Add Artefact to Project
    @POST
    @Path("/{id}/artefact")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addArtefact(@PathParam("id") String id, Projekt_Artefakt projekt_Artefakt) {
        // Check if the project already exists
        try {
            Projekt existingProject = em.find(Projekt.class, Integer.parseInt(id));
            if (existingProject == null) {
                return Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "*").build();
            }
            // Check if the artefact already exists
            Artefakt existingArtefact = em.find(Artefakt.class, projekt_Artefakt.getArtefaktId());
            if (existingArtefact == null) {
                return Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "*").build();
            }
            // Check if the artefact is valid
            if (projekt_Artefakt.getArbeitszeit() < 0) {
                return Response.status(Response.Status.BAD_REQUEST).header("Access-Control-Allow-Origin", "*").build();
            }

            // Add the artefact
            try {
                utx.begin();
                em.persist(projekt_Artefakt);
                utx.commit();
            } catch (Exception e) {
                utx.rollback();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header("Access-Control-Allow-Origin", "*").build();
            }
            // Get the updated project
            return Response.status(Response.Status.NO_CONTENT).header("Access-Control-Allow-Origin", "*").build();
        } catch (NumberFormatException | SystemException e) {
            return Response.status(Response.Status.BAD_REQUEST).header("Access-Control-Allow-Origin", "*").build();
        }
    }

    // Delete Artefact from Project
    @DELETE
    @Path("/{id}/artefact/{aid}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteArtefact(@PathParam("id") String id, @PathParam("aid") String aid) throws SystemException {
        // Check if the Project_Artefakt exists
        try {
            Projekt_Artefakt existingRelation = em.find(Projekt_Artefakt.class, Integer.parseInt(aid));
            if (existingRelation == null) {
                return Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "*").build();
            }
            // Delete the Project_Artefakt
            try {
                utx.begin();
                em.remove(existingRelation);
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
