package classes;

import java.io.Serializable;
import java.time.LocalDateTime;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * Represents a project
 * 
 * @author ffehring
 */
@XmlRootElement
@Entity
@Table(name = "project")
@NamedQueries({
        @NamedQuery(name = "project.getList", query = "SELECT p FROM Project p"),
        @NamedQuery(name = "project.getSpecific", query = "SELECT p FROM Project p WHERE p.id = :id"),
        @NamedQuery(name = "project.getTitle", query = "SELECT p FROM Project p WHERE p.title = :title"),
})
public class Projekt implements Serializable {

    private static final long serialVersionUID = 1L;
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "logo_path")
    private String logoPath;
    @Column(name = "start_date")
    private LocalDateTime startDate;

    
 
    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getKurzbeschreibung() {
        return kurzbeschreibung;
    }

    public void setKurzbeschreibung(String kurzbeschreibung) {
        this.kurzbeschreibung = kurzbeschreibung;
    }

    public String getLogopath() {
        return logopath;
    }

    public void setLogopath(String logopath) {
        this.logopath = logopath;
    }

    public LocalDateTime getStartdatum() {
        return startdatum;
    }

    public void setStartdatum(LocalDateTime startdatum) {
        this.startdatum = startdatum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}
