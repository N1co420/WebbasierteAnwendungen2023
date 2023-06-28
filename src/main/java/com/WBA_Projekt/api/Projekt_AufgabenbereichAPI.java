package com.WBA_Projekt.api;

import com.WBA_Projekt.classes.Projekt_Aufgabenbereich;

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
@Path("/projekt_aufgabenbereich")
@TransactionManagement(TransactionManagementType.BEAN)
public class Projekt_AufgabenbereichAPI {

    EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("JPA_ExamplePU");
    EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

    @Resource
    private UserTransaction utx;

    // Gets Relationships can be filtered by query parameters
    @GET
    @Produces("application/json")
    public Response getProjekt_Aufgabenbereich(@QueryParam("projektId") String projektId, @QueryParam("aufgabenbereichId") String aufgabenbereichId) {
        if (projektId != null && aufgabenbereichId != null) {
            try {
                Projekt_Aufgabenbereich projekt_aufgabenbereich = em.createNamedQuery("Projekt_Aufgabenbereich.findByProjektIdAndAufgabenbereichId",
                                Projekt_Aufgabenbereich.class)
                        .setParameter("projektId", projektId)
                        .setParameter("aufgabenbereichId", aufgabenbereichId).getSingleResult();
                return projekt_aufgabenbereich != null ? Response.ok(projekt_aufgabenbereich).header("Access-Control-Allow-Origin", "*").build() : Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "*").build();
            } catch (NumberFormatException e) {
                return Response.status(Response.Status.BAD_REQUEST).header("Access-Control-Allow-Origin", "*").build();
            }
        } else if (projektId != null) {
            try {
                List<Projekt_Aufgabenbereich> projekt_aufgabenbereich = em.createNamedQuery("Projekt_Aufgabenbereich.findByProjektId",
                                Projekt_Aufgabenbereich.class)
                        .setParameter("projektId", projektId).getResultList();
                return projekt_aufgabenbereich != null ? Response.ok(projekt_aufgabenbereich).header("Access-Control-Allow-Origin", "*").build() : Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "*").build();
            } catch (NumberFormatException e) {
                return Response.status(Response.Status.BAD_REQUEST).header("Access-Control-Allow-Origin", "*").build();
            }
        } else if (aufgabenbereichId != null) {
            try {
                List<Projekt_Aufgabenbereich> projekt_aufgabenbereich = em.createNamedQuery("Projekt_Aufgabenbereich.findByAufgabenbereichId",
                                Projekt_Aufgabenbereich.class)
                        .setParameter("aufgabenbereichId", aufgabenbereichId).getResultList();
                return projekt_aufgabenbereich != null ? Response.ok(projekt_aufgabenbereich).header("Access-Control-Allow-Origin", "*").build() : Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "*").build();
            } catch (NumberFormatException e) {
                return Response.status(Response.Status.BAD_REQUEST).header("Access-Control-Allow-Origin", "*").build();
            }
        } else {
            List<Projekt_Aufgabenbereich> projekt_aufgabenbereich = em.createNamedQuery("Projekt_Aufgabenbereich.findAll",
                    Projekt_Aufgabenbereich.class).getResultList();
            return projekt_aufgabenbereich != null ? Response.ok(projekt_aufgabenbereich).header("Access-Control-Allow-Origin", "*").build() : Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "*").build();
        }
    }
}