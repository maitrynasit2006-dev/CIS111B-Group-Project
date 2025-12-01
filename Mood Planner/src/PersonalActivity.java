/**
 * Represents a personal task created by the user.
 * Extends the abstract Task class with no extra fields.
 */
import java.time.LocalDate;

public class PersonalActivity extends Activity {

    public PersonalActivity(int id, String title, String description,
                            LevelMood effortLevel, LocalDate dueDate) {
        super(id, title, description, effortLevel, dueDate);
    }

    @Override
    public String getTypeLabel() {
        return "Personal";
    }

    @Override
    public String toString() {
        return super.toString() + " [Personal]";
    }
}
