
import java.time.LocalDate;

/**
 * Base class for all tasks.
 */
public abstract class Task {
    private int id;
    private String title;
    private String description;
    private EffortLevel effortLevel; // LOW, MEDIUM, HIGH
    private boolean completed;
    private LocalDate dueDate;

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
    public Task(int id, String title, String description, EffortLevel effortLevel, LocalDate dueDate) {
        setId(id);
        setTitle(title);
        setDescription(description);
        setEffortLevel(effortLevel);
        this.completed = false;
        this.dueDate = dueDate;
    }

    // Encapsulation with validation
    /**
     * Returns the unique identifier of the task.
     *
     * @return the task's id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the task.
     *
     * @param id the new id (must be non-negative)
     * @throws IllegalArgumentException if id is negative
     */
    public void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("Task id must be non-negative.");
        }
        this.id = id;
    }

    /**
     * Returns the title of the task.
     *
     * @return the task's title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the task.
     *
     * @param title the new title (cannot be null or empty)
     * @throws IllegalArgumentException if title is null or empty
     */
    public void setTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Task title cannot be empty.");
        }
        this.title = title.trim();
    }

    /**
     * Returns the description of the task.
     *
     * @return the task's description (empty string if not set)
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the task.
     *
     * @param description the new description (can be null, will be converted to empty string)
     */
    public void setDescription(String description) {
        this.description = (description == null) ? "" : description.trim();
    }

    /**
     * Returns the effort level required for the task.
     *
     * @return the task's effort level
     */
    public EffortLevel getEffortLevel() {
        return effortLevel;
    }

    /**
     * Sets the effort level required for the task.
     *
     * @param effortLevel the new effort level (cannot be null)
     * @throws IllegalArgumentException if effortLevel is null
     */
    public void setEffortLevel(EffortLevel effortLevel) {
        if (effortLevel == null) {
            throw new IllegalArgumentException("Effort level cannot be null.");
        }
        this.effortLevel = effortLevel;
    }

    /**
     * Checks if the task is marked as completed.
     *
     * @return true if the task is completed, false otherwise
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param completed true to mark as completed, false otherwise
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    /**
     * Returns the due date of the task.
     *
     * @return the task's due date
     */
    public LocalDate getDueDate() {
        return dueDate;
    }

    /**
     * Sets the due date of the task.
     *
     * @param dueDate the new due date
     */
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Each specific task type can give a different summary.
     */
    public abstract String getTypeLabel();

    /**
     * Returns a string representation of the task.
     *
     * @return a formatted string containing task details
     */
    @Override
    public String toString() {
        return "[" + getTypeLabel() + "] " + title +
                " (Effort: " + effortLevel +
                ", Completed: " + completed +
                ", Due: " + dueDate + ")";
    }
}
