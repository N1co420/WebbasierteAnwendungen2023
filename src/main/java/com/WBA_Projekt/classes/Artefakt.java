package com.WBA_Projekt.classes;

import com.sun.istack.NotNull;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "artefakt")
@NamedQueries({
        @NamedQuery(name = "Artefakt.findAll", query = "SELECT a FROM Artefakt a")
})
public class Artefakt implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @NotNull
    private int artefaktID;

    @Column(name = "artefaktname")
    @NotNull
    private String name;

    @Column(name = "artefaktbeschreibung")
    @NotNull
    @Max(255)
    private String beschreibung;

    @Column(name = "geplanteZeit")
    @Min(0)
    private float geplanteZeit;

    // Konstruktor
    public Artefakt(String name, String beschreibung, float geplanteZeit) {
        this.name = name;
        this.beschreibung = beschreibung;
        this.geplanteZeit = geplanteZeit;
    }

    public Artefakt() {
    }

    // Getter und Setter
    public int getArtefaktID() {
        return artefaktID;
    }

    public void setArtefaktID(int artefaktID) {
        this.artefaktID = artefaktID;
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
        // Max 255 Zeichen
        if (beschreibung.length() > 255) return;
        this.beschreibung = beschreibung;
    }

    public float getGeplanteZeit() {
        return geplanteZeit;
    }

    public void setGeplanteZeit(float geplanteZeit) {
        this.geplanteZeit = geplanteZeit;
    }

    // toString
    @Override
    public String toString() {
        return "Artefakt{" +
                "artefaktID" + artefaktID + '\'' +
                ", name='" + name + '\'' +
                ", beschreibung='" + beschreibung + '\'' +
                ", geplanteZeit=" + geplanteZeit +
                '}';
    }
}
