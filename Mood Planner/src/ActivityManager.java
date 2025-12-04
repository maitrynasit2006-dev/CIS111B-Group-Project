import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages a collection of activities and provides operations to manipulate them.
 * This includes adding, retrieving, suggesting, and persisting activities.
 */
public class ActivityManager {

    /** List to store all activities */
    private ArrayList<Activity> activities = new ArrayList<>();

    /** Counter for generating unique activity IDs */
    private int nextId = 1;

    /**
     * Adds a new activity to the manager.
     * Automatically assigns the next available ID to the activity.
     *
     * @param activity the activity to add (cannot be null)
     * @throws IllegalArgumentException if the activity is null
     */
    public void addActivity(Activity activity) {
        if (activity == null) {
            throw new IllegalArgumentException("Activity cannot be null");
        }
        activity.setId(nextId++);
        activities.add(activity);
    }

    /**
     * Returns a list of all activities in the manager.
     *
     * @return a new ArrayList containing all activities
     */
    public ArrayList<Activity> getAllActivities() {
        return new ArrayList<>(activities);
    }

    /**
     * Suggests activities based on the user's current mood.
     * Uses the recommended effort level defined in the MoodType enum.
     *
     * @param mood the user's current mood
     * @return a list of activities matching the suggested effort level
     * @throws IllegalArgumentException if mood is null
     */
    public ArrayList<Activity> suggestActivitiesByMood(MoodType mood) {
        if (mood == null) {
            throw new IllegalArgumentException("Mood cannot be null");
        }

        LevelMood target = mood.getRecommendedEffortLevel();

        ArrayList<Activity> result = new ArrayList<>();

        for (Activity a : activities) {
            if (a.getEffortLevel() == target) {
                result.add(a);
            }
        }

        return result;
    }

    /**
     * Saves all activities to a text file in CSV format.
     * Each line represents one activity with fields separated by commas.
     * Format: id,title,description,effortLevel,type
     *
     * @param filename the name of the file to save to
     * @return true if the save succeeded, false otherwise
     */
    public boolean saveActivitiesToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(filename)) {

            for (Activity a : activities) {
                writer.println(a.getId() + "," +
                        a.getTitle() + "," +
                        a.getDescription() + "," +
                        a.getEffortLevel() + "," +
                        a.getTypeLabel());
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Loads activities from a text file in CSV format.
     * Expected format per line: id,title,description,effortLevel,type
     * For school activities, the course name is not preserved in this implementation.
     *
     * @param filename the name of the file to load from
     * @return true if the load succeeded, false otherwise
     */
    public boolean loadActivitiesFromFile(String filename) {
        try (Scanner sc = new Scanner(new File(filename))) {

            while (sc.hasNextLine()) {
                String[] p = sc.nextLine().split(",");

                if (p.length < 5) {
                    // Skip malformed lines
                    continue;
                }

                int id = Integer.parseInt(p[0]);
                String title = p[1];
                String desc = p[2];
                LevelMood level = LevelMood.valueOf(p[3]);
                String type = p[4];

                Activity activity;

                if (type.equalsIgnoreCase("School")) {
                    activity = new SchoolActivity(id, title, desc, level, LocalDate.now(), "Unknown");
                } else {
                    activity = new PersonalActivity(id, title, desc, level, LocalDate.now());
                }

                activities.add(activity);

                // Ensure nextId remains ahead of any loaded IDs
                if (id >= nextId) {
                    nextId = id + 1;
                }
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Exports a specific activity to an iCalendar (.ics) file that can be imported
     * into Google Calendar or other calendar applications.
     *
     * @param activityId the ID of the activity to export
     * @return true if the export succeeded, false if activity is not found or an error occurs
     */
    public boolean exportActivityToGoogleCalendar(int activityId) {

        Activity selected = null;

        for (Activity a : activities) {
            if (a.getId() == activityId) {
                selected = a;
                break;
            }
        }

        if (selected == null) {
            return false;
        }

        String fileName = "activity_" + selected.getId() + ".ics";

        try (PrintWriter pw = new PrintWriter(fileName)) {

            pw.println("BEGIN:VCALENDAR");
            pw.println("VERSION:2.0");
            pw.println("BEGIN:VEVENT");
            pw.println("SUMMARY:" + selected.getTitle());
            pw.println("DESCRIPTION:" + selected.getDescription());
            pw.println("DTSTART:" +
                    selected.getDueDate().toString().replace("-", "") +
                    "T090000");
            pw.println("END:VEVENT");
            pw.println("END:VCALENDAR");

            return true;

        } catch (Exception e) {
            return false;
        }
    }
}