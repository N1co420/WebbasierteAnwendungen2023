package rest;

import classes.Artefact;
public class ArtefactAdapter {

    private Long id;
    private String title;
    private String description;
    private String plannedWorkTime;

    public void setId(Long id){
        this.id = id;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setPlannedWorkTime(String plannedWorkTime){
        this.plannedWorkTime = plannedWorkTime;
    }

    public Artefact toArtefact(){
        Artefact artefact = new Artefact();
        artefact.setId(this.id);
        artefact.setTitle(this.title);
        artefact.setDescription(this.description);
        artefact.setPlannedWorkTime(this.plannedWorkTime);
        return artefact;
    }
}
