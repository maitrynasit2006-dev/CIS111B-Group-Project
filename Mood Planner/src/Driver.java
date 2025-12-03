import java.util.Scanner;
import java.time.LocalDate;

/**
 * The main driver class for the Activity-Mood Planner application.
 * This class provides a command-line interface for users to interact with
 * the application, including logging moods, managing activities, and viewing
 * activity suggestions based on mood.
 */
public class Driver {

    /**
     * The main entry point for the Activity-Mood Planner application.
     * Initializes the necessary components and displays the main menu for user interaction.
     * 
     * @param args Command-line arguments (not used)
     */

    public static void main(String[] args) {
        // Initialize scanner for user input
        // Create instances of required managers and services

        Scanner sc = new Scanner(System.in);
        ActivityManager activityManager = new ActivityManager();
        MoodManager moodManager = new MoodManager();
        ExternalService externalService = new ExternalService();

        boolean running = true;

        while (running) {

            /**
             * Display the main menu options to the user.
             * The menu provides various options to interact with the Activity-Mood Planner.
             */
            System.out.println("\n=== Activity–Mood Planner ===");
            System.out.println("1. Log Mood");
            System.out.println("2. Add Activity");
            System.out.println("3. Show Suggested Activities");
            System.out.println("4. Show All Activities");
            System.out.println("5. Show Mood History");
            System.out.println("6. Save Activities to File");
            System.out.println("7. Load Activities from File");
            System.out.println("8. Show Motivational Quote (API)");
            System.out.println("9. Export Activity to Google Calendar");
            System.out.println("10. Exit");
            System.out.print("Choose option: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input.");
                sc.nextLine();
                continue;
            }

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1 -> {
                    System.out.print("Enter mood (TIRED / NEUTRAL / ENERGETIC): ");
                    String input = sc.nextLine().trim().toUpperCase();

                    try {
                        MoodType mood = MoodType.valueOf(input);
                        moodManager.logMood(mood);
                        System.out.println("Mood logged.");
                    } catch (Exception e) {
                        System.out.println("Invalid mood.");
                    }
                }

                case 2 -> {
                    System.out.print("Activity title: ");
                    String title = sc.nextLine();

                    System.out.print("Description: ");
                    String desc = sc.nextLine();

                    System.out.print("Effort (LOW / MEDIUM / HIGH): ");
                    LevelMood level;

                    try {
                        level = LevelMood.valueOf(sc.nextLine().trim().toUpperCase());
                    } catch (Exception e) {
                        System.out.println("Invalid effort level.");
                        break;
                    }

                    System.out.print("School activity? (yes/no): ");
                    String type = sc.nextLine().trim();

                    if (type.equalsIgnoreCase("yes")) {
                        System.out.print("Course name: ");
                        String course = sc.nextLine();
                        activityManager.addActivity(
                                new SchoolActivity(0, title, desc, level, LocalDate.now(), course)
                        );
                    } else {
                        activityManager.addActivity(
                                new PersonalActivity(0, title, desc, level, LocalDate.now())
                        );
                    }

                    System.out.println("Activity added.");
                }

                case 3 -> {
                    System.out.print("Enter mood (TIRED / NEUTRAL / ENERGETIC): ");

                    try {
                        MoodType mood = MoodType.valueOf(sc.nextLine().trim().toUpperCase());
                        var list = activityManager.suggestActivitiesByMood(mood);

                        if (list.isEmpty()) {
                            System.out.println("No matching activities.");
                        } else {
                            list.forEach(System.out::println);
                        }

                    } catch (Exception e) {
                        System.out.println("Invalid mood.");
                    }
                }

                case 4 -> {
                    var all = activityManager.getAllActivities();

                    if (all.isEmpty()) {
                        System.out.println("No activities available.");
                    } else {
                        all.forEach(System.out::println);
                    }
                }

                case 5 -> {
                    var logs = moodManager.getAllMoodLogs();

                    if (logs.isEmpty()) {
                        System.out.println("No mood history.");
                    } else {
                        logs.forEach(System.out::println);
                    }
                }

                case 6 -> activityManager.saveActivitiesToFile("activities.txt");

                case 7 -> activityManager.loadActivitiesFromFile("activities.txt");

                case 8 -> {
                    System.out.println("Motivational Quote:");
                    System.out.println(externalService.getMotivationalQuote());
                }

                case 9 -> {
                    System.out.print("Enter activity ID to export: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    activityManager.exportActivityToGoogleCalendar(id);
                }

                case 10 -> {
                    System.out.println("Goodbye!");
                    running = false;
                }

                default -> System.out.println("Choose between 1 and 10.");
            }
        }
    }
}
