package  classes;

/**
 * Java class to present a Project TaskGroup
 */
public class Project_TaskGroup{

    //private int class varibale to store the id of a project
    private int idProject;

    //private int class variable to store the id of a Task Group
    private int idTaskGroup;

    /**
     * to create a new objekt of this class with ids of a Project and task Group
     * @param idProject int id from Project object
     * @param idTaskGroup int id from TaskGroup object
     */
    public Project_TaskGroup(int idProject, int idTaskGroup){
        this.idProject=idProject;
        this.idTaskGroup=idTaskGroup;
    }

    /**
     * to return the id of the used project
     * @return the id of the used Project
     */
    public int getIdProject() {
        return idProject;
    }
    /**
     * to change the id of the id of the project
     * @param idProject new int id for a project
     */
    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }


    /**
     * to return the id of the TaskGroup
     * @return id of the used taskGroup
     */
    public int getIdTaskGroup() {
        return idTaskGroup;
    }

    /**
     * change the id for taskGroup
     * @param idTaskGroup int id for the new TaskGroup
     */
    public void setIdTaskGroup(int idTaskGroup) {
        this.idTaskGroup = idTaskGroup;
    }
}