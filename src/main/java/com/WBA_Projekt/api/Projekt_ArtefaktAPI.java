package com.WBA_Projekt.api;

import com.WBA_Projekt.classes.Projekt_Artefakt;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.UserTransaction;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
@Path("/projekt_artefakt")
@TransactionManagement(TransactionManagementType.BEAN)
public class Projekt_ArtefaktAPI {

    EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("JPA_ExamplePU");
    EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

    @Resource
    private UserTransaction utx;

    // Gets Relationships can be filtered by query parameters
    @GET
    @Produces("application/json")
    public Response getProjekt_Aufgabenbereich(@QueryParam("projektId") String projektId, @QueryParam("artefaktId") String artefaktId) {
        if (projektId != null && artefaktId != null) {
            try {
                Projekt_Artefakt projekt_artefakt = em.createNamedQuery("Projekt_Artefakt.findByProjektIdAndArtefaktId",
                                Projekt_Artefakt.class)
                        .setParameter("projektId", Integer.parseInt(projektId))
                        .setParameter("artefaktId", Integer.parseInt(artefaktId)).getSingleResult();
                return projekt_artefakt == null ? Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "*").build() : Response.ok(projekt_artefakt).header("Access-Control-Allow-Origin", "*").build();
            } catch (NumberFormatException e) {
                return Response.status(Response.Status.BAD_REQUEST).header("Access-Control-Allow-Origin", "*").build();
            }
        } else if (projektId != null) {
            try {
                List<Projekt_Artefakt> projekt_artefaktList = em.createNamedQuery("Projekt_Artefakt.findByProjektId",
                                Projekt_Artefakt.class)
                        .setParameter("projektId", Integer.parseInt(projektId)).getResultList();
                return projekt_artefaktList == null ? Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "*").build() : Response.ok(projekt_artefaktList).header("Access-Control-Allow-Origin", "*").build();
            } catch (NumberFormatException e) {
                return Response.status(Response.Status.BAD_REQUEST).header("Access-Control-Allow-Origin", "*").build();
            }
        } else if (artefaktId != null) {
            try {
                List<Projekt_Artefakt> projekt_artefaktList = em.createNamedQuery("Projekt_Artefakt.findByArtefaktId",
                                Projekt_Artefakt.class)
                        .setParameter("artefaktId", Integer.parseInt(artefaktId)).getResultList();
                return projekt_artefaktList == null ? Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "*").build() : Response.ok(projekt_artefaktList).header("Access-Control-Allow-Origin", "*").build();
            } catch (NumberFormatException e) {
                return Response.status(Response.Status.BAD_REQUEST).header("Access-Control-Allow-Origin", "*").build();
            }
        } else {
            List<Projekt_Artefakt> projekt_artefaktList = em.createNamedQuery("Projekt_Artefakt.findAll", Projekt_Artefakt.class).getResultList();
            return projekt_artefaktList == null ? Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "*").build() : Response.ok(projekt_artefaktList).header("Access-Control-Allow-Origin", "*").build();
        }
    }
}