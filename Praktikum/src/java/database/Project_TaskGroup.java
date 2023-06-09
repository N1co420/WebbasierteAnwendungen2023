package  database;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Collection;
/**
 * Java class to present a Project TaskGroup
 */
@XmlRootElement
@Entity
@Table(name = "project_taskgroup")
public class Project_TaskGroup implements Serializable{

    //private int class varibale to store the id of a project
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "project_id")
    @OneToMany()
    private Collection<Projekt> ProjectId;
    @Column(name = "taskgroup_id")
    @OneToMany()
    private Collection<TaskGroup> TaskGroupId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}