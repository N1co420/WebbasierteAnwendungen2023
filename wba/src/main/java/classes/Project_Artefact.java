package classes;

/**
 * This is a Java class that represents a project artifact.
 */
public class Project_Artefact {

    // Private instance variables to store the artifact's ID and work time.
    private int id;
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
    public int getId() {
        return id;
    }

    /**
     * Sets the artifact's ID.
     *
     * @param id The new ID of the artifact.
     */
    public void setId(int id) {
        this.id = id;
    }
}
