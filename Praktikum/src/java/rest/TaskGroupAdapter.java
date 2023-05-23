package rest;

import database.TaskGroup;
public class TaskGroupAdapter {
    private String title;
    private String shortDescritpion;
    private Long id;

    public void setTitle(String title){
        this.title = title;
    }

    public void setShortDescritpion(String shortDescritpion){
        this.shortDescritpion = shortDescritpion;
    }

    public  void setId(Long id){
        this.id = id;
    }

    public TaskGroup toTaskGroup(){
        TaskGroup taskGroup = new TaskGroup();
        taskGroup.setId(this.id);
        taskGroup.setTitle(this.title);
        taskGroup.setDescription(this.shortDescritpion);
        return taskGroup;
    }
}

