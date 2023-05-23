package database;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
/**
 * Represents an artefact, which has an ID, title, description and planned work time.
 */
@XmlRootElement
@Entity
@Table(name = "artefact")
@NamedQueries({
        @NamedQuery(name = "artefact.getList", query = "SELECT a FROM Artefact a"),
        @NamedQuery(name = "artefact.getById", query = "SELECT a FROM Artefact a WHERE a.id = :id"),
        @NamedQuery(name = "artefact.getByTitle", query = "SELECT a FROM Artefact a WHERE a.title = :title")

})
public class Artefact implements Serializable  {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "planned_work_time")
    private String plannedWorkTime;

    /**
     * Returns the title of the artefact.
     *
     * @return The title of the artefact
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the artefact.
     *
     * @param title The title of the artefact
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the description of the artefact.
     *
     * @return The description of the artefact
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the artefact.
     *
     * @param description The description of the artefact
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the planned work time for the artefact.
     *
     * @return The planned work time for the artefact
     */
    public String getPlannedWorkTime() {
        return plannedWorkTime;
    }

    /**
     * Sets the planned work time for the artefact.
     *
     * @param plannedWorkTime The planned work time for the artefact
     */
    public void setPlannedWorkTime(String plannedWorkTime) {
        this.plannedWorkTime = plannedWorkTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
