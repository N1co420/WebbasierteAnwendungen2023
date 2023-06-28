package com.WBA_Projekt.classes;

import jakarta.validation.constraints.NotNull;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Projekt_Aufgabenbereicht")
@NamedQueries({
        @NamedQuery(name = "Projekt_Aufgabenbereich.findAll", query = "SELECT p FROM Projekt_Aufgabenbereich p"),
        @NamedQuery(name = "Projekt_Aufgabenbereich.findByProjektId", query = "SELECT p FROM Projekt_Aufgabenbereich p WHERE p.projektId = :projektId"),
        @NamedQuery(name = "Projekt_Aufgabenbereich.findByAufgabenbereichId", query = "SELECT p FROM Projekt_Aufgabenbereich p WHERE p.aufgabenbereichId = :aufgabenbereichId"),
        @NamedQuery(name = "Projekt_Aufgabenbereich.findByProjektIdAndAufgabenbereichId", query = "SELECT p FROM Projekt_Aufgabenbereich p WHERE p.projektId = :projektId AND p.aufgabenbereichId = :aufgabenbereichId")
})
public class Projekt_Aufgabenbereich implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private int projektId;

    @NotNull
    private int aufgabenbereichId;


    // Konstruktor
    public Projekt_Aufgabenbereich(int projektId, int aufgabenbereichId) {
        this.projektId = projektId;
        this.aufgabenbereichId = aufgabenbereichId;
    }

    public Projekt_Aufgabenbereich() {
    }

    // Getter und Setter
    public int getProjektId() {
        return projektId;
    }

    public void setProjektId(int projektId) {
        this.projektId = projektId;
    }

    public int getAufgabenbereichId() {
        return aufgabenbereichId;
    }

    public void setAufgabenbereichId(int aufgabenbereichId) {
        this.aufgabenbereichId = aufgabenbereichId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Projekt_Aufgabenbereich{" + "projektId=" + projektId + ", aufgabenbereichId=" + aufgabenbereichId + '}';
    }

}
