package database;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
/**
 * Java class to create a TaskGroup
 */
@XmlRootElement
@Entity
@Table(name = "Task_group")
@NamedQueries({
        @NamedQuery(name = "Task_group.getAll", query = "SELECT t FROM TaskGroup t")
})
public class TaskGroup implements Serializable{
    private static final long serialVersionUID = 1L;
    /**
     * // Private instance variables to store the TaskGroups title
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}