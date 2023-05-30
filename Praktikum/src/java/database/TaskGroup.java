package database;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
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
    public void addToDatabase() {
        Connection conn = Database.getDBConnection();
        try {
            Statement statement = conn.createStatement();
            this.id = statement.executeUpdate(
                    "INSERT INTO taskgroup (title, description) " +
                            "VALUES ('" + title + "', '" + description + "') " +
                            "RETURNING id");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void deleteFromDatabase() {
        Connection conn = Database.getDBConnection();
        try {
            conn.createStatement().executeUpdate(
                    "DELETE FROM taskgroup WHERE id = " + this.id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void updateInDatabase() {
        Connection conn = Database.getDBConnection();
        try {
            conn.createStatement().executeUpdate(
                    "UPDATE taskgroup SET title = '" + title + "', description = '" + description + "' WHERE id = " + this.id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<TaskGroup> getList(){
        ArrayList<TaskGroup> list = new ArrayList<>();
        Connection conn = Database.getDBConnection();
        try {
            ResultSet rs = conn.createStatement().executeQuery(
                    "SELECT * FROM taskgroup");
            while (rs.next()) {
                list.add(RSToTaskGroup(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public static TaskGroup getTaskGroupById(int id) {
        Connection conn = Database.getDBConnection();
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM taskgroup WHERE id = " + id);
            if (rs.next()) {
                return RSToTaskGroup(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static TaskGroup getTaskGroupByTitle(String title) {
        Connection conn = Database.getDBConnection();
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM taskgroup WHERE title = '" + title + "'");
            if (rs.next()) {
                return RSToTaskGroup(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private static TaskGroup RSToTaskGroup(ResultSet rs) {
        TaskGroup taskGroup = new TaskGroup();
        try {
            taskGroup.setId(rs.getInt("id"));
            taskGroup.setTitle(rs.getString("title"));
            taskGroup.setDescription(rs.getString("description"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return taskGroup;
    }
    
}