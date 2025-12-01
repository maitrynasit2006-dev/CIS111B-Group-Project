/**
 * Abstract base class representing a general task.
 * Supports encapsulation, validation, inheritance, and polymorphism.
 * Subclasses (SchoolTask, PersonalTask) extend this class.
 */
import java.time.LocalDate;

public abstract class Activity {

    private int id;
    private String title;
    private String description;
    private LevelMood effortLevel;
    private LocalDate dueDate;
    private boolean isCompleted;

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

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public LevelMood getEffortLevel() { return effortLevel; }
    public LocalDate getDueDate() { return dueDate; }

    public abstract String getTypeLabel();

    @Override
    public String toString() {
        return String.format("Activity{id=%d, title='%s', effort=%s, due=%s}",
                id, title, effortLevel, dueDate);
    }
}
