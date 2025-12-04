import javax.swing.*;
import java.time.LocalDate;

public class Driver {

    private static ActivityManager activityManager = new ActivityManager();
    private static MoodManager moodManager = new MoodManager();
    private static ExternalService externalService = new ExternalService();

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
                    9. Export Activity to Google Calendar
                    10. Exit
                    
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
                case "9" -> exportGUI();
                case "10" -> { return; }
                default -> JOptionPane.showMessageDialog(null, "Invalid option.");
            }
        }
    }

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

        String type = JOptionPane.showInputDialog("Activity type (SCHOOL or PERSONAL):");
        if (type == null) return;

        if (type.equalsIgnoreCase("SCHOOL")) {
            String course = JOptionPane.showInputDialog("Course name:");
            if (course == null || course.trim().isEmpty()) {
                course = "Unknown";
            }
            activityManager.addActivity(
                    new SchoolActivity(0, title, desc, level, LocalDate.now(), course)
            );
        } else {
            activityManager.addActivity(
                    new PersonalActivity(0, title, desc, level, LocalDate.now())
            );
        }

        JOptionPane.showMessageDialog(null, "Activity added.");
    }

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
                        .append("\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString());
        }
    }

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

    private static void saveGUI() {
        boolean ok = activityManager.saveActivitiesToFile("activities.txt");
        JOptionPane.showMessageDialog(null, ok ? "Saved!" : "Error saving.");
    }

    private static void loadGUI() {
        boolean ok = activityManager.loadActivitiesFromFile("activities.txt");
        JOptionPane.showMessageDialog(null, ok ? "Loaded!" : "Error loading.");
    }

    private static void quoteGUI() {
        JOptionPane.showMessageDialog(null, externalService.getMotivationalQuote());
    }

    private static void exportGUI() {
        String idText = JOptionPane.showInputDialog("Enter activity ID:");

        if (idText == null) return;

        try {
            int id = Integer.parseInt(idText.trim());
            boolean ok = activityManager.exportActivityToGoogleCalendar(id);
            JOptionPane.showMessageDialog(null, ok ? "Exported." : "Activity not found or error.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid ID.");
        }
    }
}
