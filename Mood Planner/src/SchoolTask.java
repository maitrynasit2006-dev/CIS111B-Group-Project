import java.time.LocalDate;

/**
 * Task related to school work.
 */
public class SchoolTask extends Task {

    private String courseName;

    /**
     * Constructs a new SchoolTask with the specified parameters.
     *
     * @param id the unique identifier for the task (must be non-negative)
     * @param title the title of the school task (cannot be null or empty)
     * @param description the description of the task (can be empty)
     * @param effortLevel the effort level required for the task (cannot be null)
     * @param dueDate the due date of the task
     * @param courseName the name of the course this task is associated with (can be empty)
     * @throws IllegalArgumentException if id is negative, title is null/empty, or effortLevel is null
     */
    public SchoolTask(int id, String title, String description,
                     EffortLevel effortLevel, LocalDate dueDate,
                     String courseName) {
        super(id, title, description, effortLevel, dueDate);
        this.courseName = courseName;
    }

    /**
     * Returns the name of the course this task is associated with.
     *
     * @return the course name (empty string if not set)
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Sets the name of the course this task is associated with.
     *
     * @param courseName the new course name (can be null, will be converted to empty string)
     */
    public void setCourseName(String courseName) {
        this.courseName = (courseName == null) ? "" : courseName.trim();
    }

    /**
     * Returns the type label for school tasks.
     *
     * @return the string "School" as the task type label
     */
    @Override
    public String getTypeLabel() {
        return "School";
    }

    /**
     * Returns a string representation of the school task.
     * Includes task details from the parent class and the course name.
     *
     * @return a formatted string containing task and course details
     */
    @Override
    public String toString() {
        return super.toString() + " (Course: " + courseName + ")";
    }
}
