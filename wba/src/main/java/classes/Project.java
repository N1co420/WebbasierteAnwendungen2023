package classes;

// Import the necessary classes
import java.time.LocalDateTime;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
/**
 * This is a simple Java class that represents a project.
 */
@XmlRootElement
public class Project implements Serializable {

    // Private instance variables to store the project's title, short description, logo path, and start date.
    private static final long serialVersionUID = 1L;
    private Long id;
    private String title;
    private String shortDescription;
    private String projectLogoPath;
    private LocalDateTime projectStartDate;

    /**
     * Sets the id of the project.
     *
     * @param id The new id of the project.
     */
    public void setId(Long id)
    {
        this.id  = id;
    }

    /**
     * Returns the project's id.
     *
     * @return The id of the project.
     */
    public Long getId() {return id;}
    /**
     * Returns the project's title.
     *
     * @return The title of the project.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the project's title.
     *
     * @param title The new title of the project.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the project's short description.
     *
     * @return The short description of the project.
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * Sets the project's short description.
     *
     * @param shortDescription The new short description of the project.
     */
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    /**
     * Returns the project's logo path.
     *
     * @return The path to the project's logo.
     */
    public String getProjectLogoPath() {
        return projectLogoPath;
    }

    /**
     * Sets the project's logo path.
     *
     * @param projectLogoPath The new path to the project's logo.
     */
    public void setProjectLogoPath(String projectLogoPath) {
        this.projectLogoPath = projectLogoPath;
    }

    /**
     * Returns the project's start date.
     *
     * @return The start date of the project.
     */
    public LocalDateTime getProjectStartDate() {
        return projectStartDate;
    }

    /**
     * Sets the project's start date.
     *
     * @param projectStartDate The new start date of the project.
     */
    public void setProjectStartDate(LocalDateTime projectStartDate) {
        this.projectStartDate = projectStartDate;
    }
}

