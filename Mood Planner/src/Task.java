
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

    public Task(int id, String title, String description, EffortLevel effortLevel, LocalDate dueDate) {
        setId(id);
        setTitle(title);
        setDescription(description);
        setEffortLevel(effortLevel);
        this.completed = false;
        this.dueDate = dueDate;
    }

    // Encapsulation with validation
    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("Task id must be non-negative.");
        }
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Task title cannot be empty.");
        }
        this.title = title.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = (description == null) ? "" : description.trim();
    }

    public EffortLevel getEffortLevel() {
        return effortLevel;
    }

    public void setEffortLevel(EffortLevel effortLevel) {
        if (effortLevel == null) {
            throw new IllegalArgumentException("Effort level cannot be null.");
        }
        this.effortLevel = effortLevel;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Each specific task type can give a different summary.
     */
    public abstract String getTypeLabel();

    @Override
    public String toString() {
        return "[" + getTypeLabel() + "] " + title +
                " (Effort: " + effortLevel +
                ", Completed: " + completed +
                ", Due: " + dueDate + ")";
    }
}