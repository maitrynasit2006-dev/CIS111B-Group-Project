/**
 * Represents a personal activity in the task management system.
 * This class extends the abstract {@link Activity} class to represent personal tasks
 * that are not related to school or work.
 *
 * <p>Personal activities are distinguished from other activity types by their type label
 * and are typically used for personal development, hobbies, or leisure activities.</p>
 *
 * @see Activity
 * @see SchoolActivity
 * @see ActivityManager
 */
import java.time.LocalDate;

public class PersonalActivity extends Activity {

    /**
     * Creates a new personal activity with the specified details.
     *
     * @param id           the unique identifier for this activity
     * @param title        the title of the activity (cannot be null or empty)
     * @param description  a description of the activity (can be empty)
     * @param effortLevel  the effort level required for this activity (LOW, MEDIUM, or HIGH)
     * @param dueDate      the due date for this activity
     * @throws IllegalArgumentException if title is null or empty
     * @see LevelMood
     */
    public PersonalActivity(int id, String title, String description,
                            LevelMood effortLevel, LocalDate dueDate) {
        super(id, title, description, effortLevel, dueDate);
    }

    /**
     * Returns the type label for this activity, which is always "Personal".
     *
     * @return the string "Personal"
     */
    @Override
    public String getTypeLabel() {
        return "Personal";
    }

    /**
     * Returns a string representation of this personal activity.
     * The string includes the activity details from the parent class
     * followed by "[Personal]" to indicate the activity type.
     *
     * @return a string representation of this personal activity
     */
    @Override
    public String toString() {
        return super.toString() + " [Personal]";
    }
}
