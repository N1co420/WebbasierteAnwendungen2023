package rest;


import classes.Project;
import java.time.LocalDateTime;

/**
 * Adapter solveing the issue
 * - Date is not directly parseable from json useing JAXB
 *
 * @author Florian Fehring
 */
public class ProjectAdapter {
    private String titel;
    private String startdate;
    private int id;

    public void setTitle(String title) {
        this.titel = title;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public Project toProject() {
        Project proj = new Project();
        proj.setId(this.id);
        proj.setTitle(this.titel);
        proj.setProjectStartDate(LocalDateTime.parse(this.startdate));  //ISO-Datumsformat yyyy-mm-ddT10:15:30
        return proj;
    }
}

