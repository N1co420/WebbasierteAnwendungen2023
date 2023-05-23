package database;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
/**
 * This is a Java class that represents a project artifact.
 */
@XmlRootElement
@Entity
@Table(name = "project_artefact")
public class Project_Artefact implements Serializable{

    // Private instance variables to store the artifact's ID and work time.
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "work_time")
    private String work_time;

    /**
     * Returns the artifact's work time.
     *
     * @return The work time of the artifact.
     */
    public String getWork_time() {
        return work_time;
    }

    /**
     * Sets the artifact's work time.
     *
     * @param work_time The new work time of the artifact.
     */
    public void setWork_time(String work_time){
        this.work_time = work_time;
    }

    /**
     * Returns the artifact's ID.
     *
     * @return The ID of the artifact.
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the artifact's ID.
     *
     * @param id The new ID of the artifact.
     */
    public void setId(long id) {
        this.id = id;
    }
}
