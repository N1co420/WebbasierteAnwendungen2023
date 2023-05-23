package database;


import jakarta.persistence.*;
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
 
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
