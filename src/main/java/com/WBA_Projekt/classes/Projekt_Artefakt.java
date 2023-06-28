package com.WBA_Projekt.classes;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Projekt_Artefakt")
@NamedQueries({
        @NamedQuery(name = "Projekt_Artefakt.findByProjektIdAndArtefaktId",
                query = "SELECT p FROM Projekt_Artefakt p WHERE p.projektId = :projektId AND p.artefaktId = :artefaktId"),
        @NamedQuery(name = "Projekt_Artefakt.findByProjektId",
                query = "SELECT p FROM Projekt_Artefakt p WHERE p.projektId = :projektId"),
        @NamedQuery(name = "Projekt_Artefakt.findByArtefaktId",
                query = "SELECT p FROM Projekt_Artefakt p WHERE p.artefaktId = :artefaktId"),
        @NamedQuery(name = "Projekt_Artefakt.findAll",
                query = "SELECT p FROM Projekt_Artefakt p")
})
public class Projekt_Artefakt implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Projekt_ID")
    @NotNull
    private int projektId;

    @Column(name = "Artefakt_ID")
    @NotNull
    private int artefaktId;

    @Column(name = "arbeitszeit")
    @Min(0)
    private int arbeitszeit;

    // Konstruktor
    public Projekt_Artefakt(int projektId, int artefaktId, int arbeitszeit) {
        this.projektId = projektId;
        this.artefaktId = artefaktId;
        this.arbeitszeit = arbeitszeit;
    }

    public Projekt_Artefakt() {
    }

    // Getter und Setter
    public int getProjektId() {
        return projektId;
    }

    public void setProjektId(int projektId) {
        this.projektId = projektId;
    }

    public int getArtefaktId() {
        return artefaktId;
    }

    public void setArtefaktId(int artefaktId) {
        this.artefaktId = artefaktId;
    }

    public int getArbeitszeit() {
        return arbeitszeit;
    }

    public void setArbeitszeit(int arbeitszeit) {
        this.arbeitszeit = arbeitszeit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // toString
    @Override
    public String toString() {
        return "Projekt_Artefakt{" + "projektId=" + projektId + ", artefaktId=" + artefaktId + ", arbeitszeit=" + arbeitszeit + '}';
    }
}