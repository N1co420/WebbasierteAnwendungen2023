package classes;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Java class to create a TaskGroup
 */
@XmlRootElement
public class TaskGroup{

    /**
     * // Private instance variables to store the TaskGroups title
     */
    private String title;
    /**
     * // Private instance variables to store the TaskGroups descrition
     */
    private String shortDescription;
    /**
     * // Private instance variables to store the TaskGroups id
     */
    private int id;

    /**
     * define the constructor with two parameters for the private class Strings
     */
    public TaskGroup(String title, String shortDescription){
        this.title=title;
        this.shortDescription=shortDescription;
    }

    /**
     * returns the TaskGroups title
     * @return title of taskgroup
     */
    public String getTitle() {
        return title;
    }

    /**
     * change title of this Taskgroup
     * @param title new title for this TaskGroup
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * returns the short descrition of this TaskGroup
     * @return descrition of this taskGroup
     */
    public String getShortDescription() {
        return shortDescription;
    }
    /**
     * changes the descrition of this TaskGroup
     * @param shortDescription new description of this TaskGroup
     */
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    /**
     *  returns the id from this TaskGroup
     * @return id of this TaskGroup
     */
    public int getId() {
        return id;
    }

    /**
     * change the id of this TaskGroup
     * @param id the id of this TaskGroup
     */
    public void setId(int id) {
        this.id = id;
    }
}