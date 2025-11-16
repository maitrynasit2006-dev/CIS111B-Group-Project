import java.time.LocalDate;

/**
 * Task related to school work.
 */
public class SchoolTask extends Task {

    private String courseName;

    public SchoolTask(int id, String title, String description,
                      EffortLevel effortLevel, LocalDate dueDate,
                      String courseName) {
        super(id, title, description, effortLevel, dueDate);
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = (courseName == null) ? "" : courseName.trim();
    }

    @Override
    public String getTypeLabel() {
        return "School";
    }

    @Override
    public String toString() {
        return super.toString() + " (Course: " + courseName + ")";
    }
}