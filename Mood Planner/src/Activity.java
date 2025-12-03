/**
 * Abstract base class representing a general task.
 * Supports encapsulation, validation, inheritance, and polymorphism.
 * Subclasses (SchoolTask, PersonalTask) extend this class.
 */
import java.time.LocalDate;

public abstract class Activity {

    /** Unique identifier for the activity */
    private int id;
    
    /** Title of the activity */
    private String title;
    
    /** Detailed description of the activity */
    private String description;
    
    /** Effort level required for the activity (LOW, MEDIUM, HIGH) */
    private LevelMood effortLevel;
    
    /** Due date for the activity */
    private LocalDate dueDate;
    
    /** Completion status of the activity */
    private boolean isCompleted;

    /**
     * Constructs a new Activity with the specified details.
     *
     * @param id           The unique identifier for the activity
     * @param title        The title of the activity (cannot be empty or null)
     * @param description  The detailed description of the activity
     * @param effortLevel  The effort level required (LOW, MEDIUM, HIGH)
     * @param dueDate      The due date for the activity
     * @throws IllegalArgumentException if title is null or empty
     */
    protected Activity(int id, String title, String description,
                     LevelMood effortLevel, LocalDate dueDate) {

        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty.");
        }

        this.id = id;
        this.title = title.trim();
        this.description = description != null ? description : "";
        this.effortLevel = effortLevel;
        this.dueDate = dueDate;
        this.isCompleted = false;
    }

    /**
     * Returns the unique identifier of the activity.
     *
     * @return the activity ID
     */
    public int getId() { return id; }
    /**
     * Sets the unique identifier of the activity.
     *
     * @param id the new ID to set
     */
    public void setId(int id) { this.id = id; }

    /**
     * Returns the title of the activity.
     *
     * @return the activity title
     */
    public String getTitle() { return title; }
    /**
     * Returns the description of the activity.
     *
     * @return the activity description
     */
    public String getDescription() { return description; }
    /**
     * Returns the effort level required for the activity.
     *
     * @return the effort level (LOW, MEDIUM, or HIGH)
     */
    public LevelMood getEffortLevel() { return effortLevel; }
    /**
     * Returns the due date of the activity.
     *
     * @return the due date
     */
    public LocalDate getDueDate() { return dueDate; }

    /**
     * Returns a label representing the type of activity.
     * This method must be implemented by subclasses.
     *
     * @return a string representing the activity type (e.g., "School", "Personal")
     */
    public abstract String getTypeLabel();

    /**
     * Returns a string representation of the activity.
     *
     * @return a formatted string containing the activity's details
     */
    @Override
    public String toString() {
        return String.format("Activity{id=%d, title='%s', effort=%s, due=%s}",
                id, title, effortLevel, dueDate);
    }
}
