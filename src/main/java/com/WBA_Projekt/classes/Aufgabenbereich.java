package com.WBA_Projekt.classes;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Aufgabenbereich")
@NamedQueries({
        @NamedQuery(name = "Aufgabenbereich.findAll", query = "SELECT a FROM Aufgabenbereich a")
})
public class Aufgabenbereich implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @NotNull
    private int aufgabenbereichID;
    @Column(name = "aufgabenbereichname")
    @NotNull
    private String name;
    @Column(name = "aufgabenbereichbeschreibung")
    @NotNull
    @Max(255)
    private String beschreibung;

    // Konstruktor
    public Aufgabenbereich(String name, String beschreibung) {
        this.name = name;
        this.beschreibung = beschreibung;
    }

    public Aufgabenbereich() {
    }

    // Getter und Setter
    public int getAufgabenbereichID() {
        return aufgabenbereichID;
    }

    public void setAufgabenbereichID(int id) {
        this.aufgabenbereichID = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        // Maximal 255 Zeichen
        if (beschreibung == null || beschreibung.length() > 255) return;
        this.beschreibung = beschreibung;
    }

    // toString
    @Override
    public String toString() {
        return "Aufgabenbereich{ id=" + aufgabenbereichID + ", name=" + name + ", beschreibung=" + beschreibung + '}';
    }

}
