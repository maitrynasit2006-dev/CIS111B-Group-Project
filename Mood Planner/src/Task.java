/**
 * Abstract base class representing a general task.
 * Supports encapsulation, validation, inheritance, and polymorphism.
 * Subclasses (SchoolTask, PersonalTask) extend this class.
 */
import java.time.LocalDate;

/**
 * Base class representing a generic task.
 */
public abstract class Task {
    private final int id;
    private String title;
    private String description;
    private LevelMood effortLevel;
    private LocalDate dueDate;
    private boolean isCompleted;

    /**
     * Constructs a new Task with the specified parameters.
     *
     * @param id the unique identifier for the task (must be non-negative)
     * @param title the title of the task (cannot be null or empty)
     * @param description the description of the task (can be empty)
     * @param effortLevel the effort level required for the task (cannot be null)
     * @param dueDate the due date of the task
     * @throws IllegalArgumentException if id is negative, title is null/empty, or effortLevel is null
     */
    protected Task(int id, String title, String description, 
                  LevelMood effortLevel, LocalDate dueDate) {
        if (id < 0) {
            throw new IllegalArgumentException("ID cannot be negative");
        }
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        if (effortLevel == null) {
            throw new IllegalArgumentException("Effort level cannot be null");
        }
        
        this.id = id;
        this.title = title.trim();
        this.description = description != null ? description : "";
        this.effortLevel = effortLevel;
        this.dueDate = dueDate;
        this.isCompleted = false;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        this.title = title.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description != null ? description : "";
    }

    public LevelMood getEffortLevel() {
        return effortLevel;
    }

    public void setEffortLevel(LevelMood effortLevel) {
        if (effortLevel == null) {
            throw new IllegalArgumentException("Effort level cannot be null");
        }
        this.effortLevel = effortLevel;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    /**
     * Returns the type label for the task.
     * This method must be implemented by all subclasses.
     *
     * @return the string representing the task type
     */
    public abstract String getTypeLabel();

    @Override
    public String toString() {
        return String.format("Task{id=%d, title='%s', description='%s', effortLevel=%s, dueDate=%s, isCompleted=%s}",
                id, title, description, effortLevel, dueDate, isCompleted);
    }
}
