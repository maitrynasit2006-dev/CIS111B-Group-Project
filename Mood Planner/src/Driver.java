/**
 * Driver class to demonstrate the functionality of the Task-Mood Planner application.
 * This class contains the main method to test the task management system.
 */
public class Driver {

    /**
     * The main method that serves as the entry point of the application.
     * It demonstrates the creation of tasks and basic mood-based task planning.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {

        System.out.println("Task–Mood Planner Test Run");

        // create one school task just to test
        SchoolTask t1 = new SchoolTask(
                1,
                "Finish biology homework",
                "Chapter 8 review questions",
                LevelMood.MEDIUM,
                null,
                "BIO-121"
        );

        // print task
        System.out.println("Created Task:");
        System.out.println(t1);

        // test mood enum
        MoodType todayMood = MoodType.TIRED;
        System.out.println("Today's mood: " + todayMood);

        // suggestion logic (VERY basic)
        if (todayMood == MoodType.TIRED) {
            System.out.println("Suggested effort: LOW");
        } else if (todayMood == MoodType.NEUTRAL) {
            System.out.println("Suggested effort: MEDIUM");
        } else {
            System.out.println("Suggested effort: HIGH");
        }

        System.out.println("Basic driver completed.");
    }
}
