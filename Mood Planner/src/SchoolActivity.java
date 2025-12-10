/**
 * Represents a school-related activity in the task management system.
 * This class extends the abstract {@link Activity} class and adds a course name field
 * to associate the activity with a specific academic course.
 *
 * <p>School activities are distinguished from other activity types by their type label
 * and are typically used for assignments, projects, or study sessions related to academic courses.</p>
 *
 * @see Activity
 * @see PersonalActivity
 * @see ActivityManager
 */
import java.time.LocalDate;

public class SchoolActivity extends Activity {

    /** The name of the course associated with this school activity. */
    private final String courseName;

    /**
     * Creates a new school activity with the specified details.
     *
     * @param id           the unique identifier for this activity
     * @param title        the title of the activity (cannot be null or empty)
     * @param description  a description of the activity (can be empty)
     * @param effortLevel  the effort level required for this activity (LOW, MEDIUM, or HIGH)
     * @param dueDate      the due date for this activity
     * @param courseName   the name of the course this activity is associated with (cannot be null)
     * @throws IllegalArgumentException if title is null or empty, or if courseName is null
     * @see LevelMood
     */
    public SchoolActivity(int id, String title, String description,
                          LevelMood effortLevel, LocalDate dueDate, String courseName) {
        super(id, title, description, effortLevel, dueDate);
        this.courseName = courseName;
    }

    /**
     * Returns the type label for this activity, which is always "School".
     *
     * @return the string "School"
     */
    @Override
    public String getTypeLabel() {
        return "School";
    }

    /**
     * Returns a string representation of this school activity.
     * The string includes the activity details from the parent class
     * followed by the course name in the format "[Course=courseName]".
     *
     * @return a string representation of this school activity
     */
    @Override
    public String toString() {
        return super.toString() + " [Course=" + courseName + "]";
    }
}
