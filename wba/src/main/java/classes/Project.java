package classes;

// Import the necessary classes
import java.time.LocalDateTime;


// Define the Project class

public class Project {
    // Define the private fields
    private String title; // The title of the project
    private String shortDescription; // A short description of the project
    private String projectLogoPath; // The path to the project logo
    private LocalDateTime projectStartDate; // The date the project started

    // Define the getter methods for the fields
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getProjectLogoPath() {
        return projectLogoPath;
    }

    public void setProjectLogoPath(String projectLogoPath) {
        this.projectLogoPath = projectLogoPath;
    }

    public LocalDateTime getProjectStartDate() {
        return projectStartDate;
    }

    public void setProjectStartDate(LocalDateTime projectStartDate) {
        this.projectStartDate = projectStartDate;
    }
}
