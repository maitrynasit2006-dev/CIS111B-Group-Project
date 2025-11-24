import java.time.LocalDate;

public class SchoolTask extends Task {

    private String courseName;

    public SchoolTask(int id, String title, String description,
                      LevelMood effortLevel, LocalDate dueDate,
                      String courseName) {
        super(id, title, description, effortLevel, dueDate);
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }

    @Override
    public String getTypeLabel() {
        return "School";
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" [School Course=%s]", courseName);
    }
}
