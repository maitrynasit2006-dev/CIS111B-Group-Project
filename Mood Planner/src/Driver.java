public class Driver {

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