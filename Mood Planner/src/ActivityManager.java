import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class ActivityManager {

    private ArrayList<Activity> activities = new ArrayList<>();
    private int nextId = 1;

    public void addActivity(Activity activity) {
        activity.setId(nextId++);
        activities.add(activity);
    }

    public ArrayList<Activity> getAllActivities() {
        return activities;
    }

    public ArrayList<Activity> suggestActivitiesByMood(MoodType mood) {

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
