package classes;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
/**
 * Represents an artefact, which has an ID, title, description and planned work time.
 */
@XmlRootElement
public class Artefact implements Serializable  {
    private static final long serialVersionUID = 1L;
    private long id; // The unique identifier of the artefact
    private String title; // The title of the artefact
    private String description; // A brief description of the artefact
    private String plannedWorkTime; // The amount of planned work time for the artefact

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

    /**
     * Returns the unique identifier of the artefact.
     *
     * @return The unique identifier of the artefact
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the artefact.
     *
     * @param id The unique identifier of the artefact
     */
    public void setId(Long id) {
        this.id = id;
    }
}
