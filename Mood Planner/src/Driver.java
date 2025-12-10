import javax.swing.*;
import java.time.LocalDate;

/**
 * The main application class for the Mood Planner system.
 * This class provides a text-based user interface for managing moods and activities.
 * It coordinates between the {@link MoodManager}, {@link ActivityManager}, and {@link ExternalService}
 * to provide a complete mood tracking and activity planning solution.
 */
public class Driver {

    /** Manages all activity-related operations. */
    private static ActivityManager activityManager = new ActivityManager();

    /** Manages all mood logging and retrieval operations. */
    private static MoodManager moodManager = new MoodManager();

    /** Handles external service calls, such as fetching motivational quotes. */
    private static ExternalService externalService = new ExternalService();

    /**
     * The main entry point for the Mood Planner application.
     * Displays a text-based menu and processes user input in a loop until the user chooses to exit.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

        while (true) {
            String choice = JOptionPane.showInputDialog("""
                            Mood Planner 
                    
                    1. Log Mood
                    2. Add Activity
                    3. Show Suggested Activities
                    4. Show All Activities
                    5. Show Mood History
                    6. Save Activities
                    7. Load Activities
                    8. Motivational Quote (API)
                    9. Exit
                    
                    Enter option:
                    """);

            if (choice == null) {
                return; // User closed window
            }

            switch (choice.trim()) {
                case "1" -> logMoodGUI();
                case "2" -> addActivityGUI();
                case "3" -> suggestGUI();
                case "4" -> showAllGUI();
                case "5" -> showHistoryGUI();
                case "6" -> saveGUI();
                case "7" -> loadGUI();
                case "8" -> quoteGUI();
                case "9" -> { return; }
                default -> JOptionPane.showMessageDialog(null, "Invalid option.");
            }
        }
    }

    /**
     * Displays a dialog to log the user's current mood.
     * Prompts the user to select from available mood types and records the selection.
     */
    private static void logMoodGUI() {
        String mood = JOptionPane.showInputDialog("Enter mood (TIRED / NEUTRAL / ENERGETIC):");

        try {
            MoodType m = MoodType.fromUserInput(mood);
            moodManager.logMood(m);
            JOptionPane.showMessageDialog(null, "Mood logged: " + m);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid mood.");
        }
    }

    /**
     * Displays a dialog to add a new activity.
     * Collects activity details including name, description, and effort level.
     */
    private static void addActivityGUI() {
        String title = JOptionPane.showInputDialog("Activity title:");
        if (title == null || title.trim().isEmpty()) {
            return;
        }

        String desc = JOptionPane.showInputDialog("Description:");
        if (desc == null) {
            desc = "";
        }

        String lvl = JOptionPane.showInputDialog("Effort (LOW/MEDIUM/HIGH):");
        if (lvl == null) return;

        LevelMood level;

        try {
            level = LevelMood.valueOf(lvl.trim().toUpperCase());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid effort level.");
            return;
        }
        String dueDateStr = JOptionPane.showInputDialog("Enter due date (YYYY-MM-DD):");
        LocalDate dueDate;

        try {
            dueDate = LocalDate.parse(dueDateStr.trim());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid date. Using today's date.");
            dueDate = LocalDate.now();
        }


        String type = JOptionPane.showInputDialog("Activity type (SCHOOL or PERSONAL):");
        if (type == null) return;

        if (type.equalsIgnoreCase("SCHOOL")) {
            String course = JOptionPane.showInputDialog("Course name:");
            if (course == null || course.trim().isEmpty()) {
                course = "Unknown";
            }
            activityManager.addActivity(
                    new SchoolActivity(0, title, desc, level, dueDate, course)
            );
        } else {
            activityManager.addActivity(
                    new PersonalActivity(0, title, desc, level, dueDate)

            );
        }

        JOptionPane.showMessageDialog(null, "Activity added.");
    }

    /**
     * Displays suggested activities based on the user's current mood.
     * If no mood has been logged, prompts the user to log a mood first.
     */
    private static void suggestGUI() {
        String moodText = JOptionPane.showInputDialog("Enter mood (TIRED / NEUTRAL / ENERGETIC):");
        if (moodText == null) return;

        try {
            MoodType mood = MoodType.fromUserInput(moodText);
            var list = activityManager.suggestActivitiesByMood(mood);

            if (list.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No suggestions for this mood yet.");
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Suggested activities for mood: ")
                        .append(mood)
                        .append("\n\n");

                for (var a : list) {
                    // Format: Activity:    ID = 01, title = Homework, description = ..., effort = HIGH
                    String idFormatted = String.format("%02d", a.getId());
                    sb.append("Activity:    ID = ")
                            .append(idFormatted)
                            .append(", Title = ")
                            .append(a.getTitle())
                            .append(", Description = ")
                            .append(a.getDescription())
                            .append(", Effort = ")
                            .append(a.getEffortLevel())
                            .append(", Due Date = ")
                            .append(a.getDueDate())
                            .append("\n");
                }

                // Add a motivational quote from the API
                String quote = externalService.getMotivationalQuote();
                sb.append("\nMotivational quote:\n")
                        .append(quote);

                JOptionPane.showMessageDialog(null, sb.toString());
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid mood.");
        }
    }

    /**
     * Displays all activities in the system.
     * Shows a list of all activities with their details in a dialog.
     */
    private static void showAllGUI() {
        var all = activityManager.getAllActivities();
        if (all.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No activities.");
        } else {
            StringBuilder sb = new StringBuilder("All activities:\n\n");
            for (var a : all) {
                String idFormatted = String.format("%02d", a.getId());
                sb.append("Activity:    ID = ")
                        .append(idFormatted)
                        .append(", Title = ")
                        .append(a.getTitle())
                        .append(", Description = ")
                        .append(a.getDescription())
                        .append(", Effort = ")
                        .append(a.getEffortLevel())
                        .append(", Due Date = ")
                        .append(a.getDueDate())
                        .append("\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString());
        }
    }

    /**
     * Displays the mood history.
     * Shows a list of all logged moods with their timestamps.
     */
    private static void showHistoryGUI() {
        var logs = moodManager.getAllMoodLogs();
        if (logs.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No mood history.");
        } else {
            StringBuilder sb = new StringBuilder("Mood history:\n\n");
            for (var log : logs) {
                sb.append(log).append("\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString());
        }
    }

    /**
     * Saves the current state of activities to a file.
     * Prompts the user for a filename and handles any file I/O errors.
     */
    private static void saveGUI() {
        boolean ok = activityManager.saveActivitiesToFile("activities.txt");
        JOptionPane.showMessageDialog(null, ok ? "Saved!" : "Error saving.");
    }

    /**
     * Loads activities from a file.
     * Prompts the user for a filename and handles any file I/O errors.
     */
    private static void loadGUI() {
        boolean ok = activityManager.loadActivitiesFromFile("activities.txt");
        JOptionPane.showMessageDialog(null, ok ? "Loaded!" : "Error loading.");
    }

    /**
     * Fetches and displays a random motivational quote using the external service.
     * The quote is retrieved from an external API and displayed in a dialog box.
     * Handles any potential network or service errors by showing a friendly message.
     * 
     * @see ExternalService#getMotivationalQuote()
     */
    private static void quoteGUI() {
        JOptionPane.showMessageDialog(null, externalService.getMotivationalQuote());
    }
}
