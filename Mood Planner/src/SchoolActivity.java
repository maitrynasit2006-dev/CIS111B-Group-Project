/**
 * Represents a school-related task.
 * Extends the abstract Task class and adds a courseName field.
 */
import java.time.LocalDate;

public class SchoolActivity extends Activity {

    private String courseName;

    public SchoolActivity(int id, String title, String description,
                          LevelMood effortLevel, LocalDate dueDate, String courseName) {
        super(id, title, description, effortLevel, dueDate);
        this.courseName = courseName;
    }

    @Override
    public String getTypeLabel() {
        return "School";
    }

    @Override
    public String toString() {
        return super.toString() + " [Course=" + courseName + "]";
    }
}
