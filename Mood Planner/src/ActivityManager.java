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
     * @throws IllegalArgumentException if activity is null
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
        return activities;
    }

    /**
     * Suggests activities based on the user's current mood.
     * Maps mood to effort levels: TIRED → LOW, NEUTRAL → MEDIUM, ENERGETIC → HIGH.
     *
     * @param mood the user's current mood (TIRED, NEUTRAL, or ENERGETIC)
     * @return a list of activities matching the suggested effort level
     * @throws IllegalArgumentException if mood is null
     */
    public ArrayList<Activity> suggestActivitiesByMood(MoodType mood) {
        if (mood == null) {
            throw new IllegalArgumentException("Mood cannot be null");
        }

        LevelMood target = switch (mood) {
            case TIRED -> LevelMood.LOW;
            case NEUTRAL -> LevelMood.MEDIUM;
            default -> LevelMood.HIGH;
        };

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
     */
    public void saveActivitiesToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(filename)) {

            for (Activity a : activities) {
                writer.println(a.getId() + "," +
                        a.getTitle() + "," +
                        a.getDescription() + "," +
                        a.getEffortLevel() + "," +
                        a.getTypeLabel());
            }

            System.out.println("Activities saved.");

        } catch (Exception e) {
            System.out.println("Error saving activities.");
        }
    }

    /**
     * Loads activities from a text file in CSV format.
     * Expected format per line: id,title,description,effortLevel,type
     * For school activities, the course name is not preserved in this implementation.
     *
     * @param filename the name of the file to load from
     */
    public void loadActivitiesFromFile(String filename) {
        try (Scanner sc = new Scanner(new File(filename))) {

            while (sc.hasNextLine()) {
                String[] p = sc.nextLine().split(",");

                int id = Integer.parseInt(p[0]);
                String title = p[1];
                String desc = p[2];
                LevelMood level = LevelMood.valueOf(p[3]);
                String type = p[4];

                if (type.equalsIgnoreCase("School")) {
                    activities.add(new SchoolActivity(id, title, desc, level, LocalDate.now(), "Unknown"));
                } else {
                    activities.add(new PersonalActivity(id, title, desc, level, LocalDate.now()));
                }
            }

            System.out.println("Activities loaded.");

        } catch (Exception e) {
            System.out.println("Error loading activities.");
        }
    }

    /**
     * Exports a specific activity to an iCalendar (.ics) file that can be imported
     * into Google Calendar or other calendar applications.
     *
     * @param activityId the ID of the activity to export
     */
    public void exportActivityToGoogleCalendar(int activityId) {

        Activity selected = null;

        for (Activity a : activities) {
            if (a.getId() == activityId) {
                selected = a;
                break;
            }
        }

        if (selected == null) {
            System.out.println("Activity not found.");
            return;
        }

        try (PrintWriter pw = new PrintWriter("activity_" + selected.getId() + ".ics")) {

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

            System.out.println("Calendar export created.");

        } catch (Exception e) {
            System.out.println("Error exporting calendar.");
        }
    }
}
