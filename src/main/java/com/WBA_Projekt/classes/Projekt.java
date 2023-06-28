package com.WBA_Projekt.classes;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Projekt")
@NamedQueries({
        @NamedQuery(name = "Projekt.findAll", query = "SELECT p FROM Projekt p"),
        @NamedQuery(name = "Projekt.findNewest", query = "SELECT p FROM Projekt p ORDER BY p.startDatum DESC")
})
public class Projekt implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private int projektID;
    @Column(name = "projektname")
    @NotNull
    private String name;
    @Column(name = "projektbeschreibung")
    @NotNull
    @Max(255)
    private String beschreibung;
    @Column(name = "logoPath")
    private String logoPath;
    @Column(name = "startDate")
    @NotNull
    private Date startDatum;

    // Konstruktor
    public Projekt(String name, String beschreibung, String logoPath, Date startDate) {
        this.name = name;
        this.beschreibung = beschreibung;
        this.logoPath = logoPath;
        this.startDatum = startDate;
    }

    public Projekt() {
    }

    // Getter und Setter
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
        if (beschreibung.length() > 255) return;
        this.beschreibung = beschreibung;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public Date getStartDate() {
        return startDatum;
    }

    public void setStartDate(Date startDate) {
        this.startDatum = startDate;
    }

    public int getProjektID() {
        return projektID;
    }

    public void setProjektID(int projektID) {
        this.projektID = projektID;
    }

    // toString
    @Override
    public String toString() {
        return "Projekt{" +
                "projektID" + projektID + '\'' +
                ", name='" + name + '\'' +
                ", beschreibung='" + beschreibung + '\'' +
                ", logoPath='" + logoPath + '\'' +
                ", startDate='" + startDatum.toString() + '\'' +
                '}';
    }
}
