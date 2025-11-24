import java.util.Scanner;
import java.time.LocalDate;

public class Driver {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();
        MoodManager moodManager = new MoodManager();

        while (true) {

            System.out.println("\n=== Task–Mood Planner ===");
            System.out.println("1. Log Mood");
            System.out.println("2. Add Task");
            System.out.println("3. Show Suggested Tasks");
            System.out.println("4. Show All Tasks");
            System.out.println("5. Show Mood History");
            System.out.println("6. Exit");
            System.out.print("Choose option: ");

            // FIX #1 — prevent crashing if user types letters
            if (!sc.hasNextInt()) {
                System.out.println(" Invalid input. Please enter a number 1–6.");
                sc.nextLine(); // clear bad input
                continue;      // restart loop
            }

            int choice = sc.nextInt();
            sc.nextLine(); // clear leftover newline

            switch (choice) {

                case 1 -> {
                    System.out.println("Enter mood (TIRED / NEUTRAL / ENERGETIC):");
                    String moodInput = sc.nextLine().trim().toUpperCase();

                    // FIX #2 — validate mood
                    try {
                        MoodType mood = MoodType.valueOf(moodInput);
                        moodManager.logMood(mood);
                        System.out.println("Mood logged.");
                    } catch (IllegalArgumentException e) {
                        System.out.println(" Invalid mood. Try again.");
                    }
                }

                case 2 -> {
                    System.out.println("Task title:");
                    String title = sc.nextLine();

                    System.out.println("Description:");
                    String desc = sc.nextLine();

                    System.out.println("Effort level (LOW / MEDIUM / HIGH):");
                    LevelMood level;
                    try {
                        level = LevelMood.valueOf(sc.nextLine().trim().toUpperCase());
                    } catch (Exception e) {
                        System.out.println(" Invalid level. Task canceled.");
                        break;
                    }

                    System.out.println("Is this a School Task? (yes/no):");
                    String isSchool = sc.nextLine().trim();

                    if (isSchool.equalsIgnoreCase("yes")) {
                        System.out.println("Course name:");
                        String course = sc.nextLine();
                        taskManager.addTask(
                                new SchoolTask(0, title, desc, level, LocalDate.now(), course)
                        );
                    } else {
                        taskManager.addTask(
                                new PersonalTask(0, title, desc, level, LocalDate.now())
                        );
                    }
                    System.out.println("Task added.");
                }

                case 3 -> {
                    System.out.println("Enter mood (TIRED/NEUTRAL/ENERGETIC):");
                    try {
                        MoodType mood = MoodType.valueOf(sc.nextLine().trim().toUpperCase());
                        var suggestions = taskManager.suggestTasksByMood(mood);

                        System.out.println("Suggested tasks:");
                        if (suggestions.isEmpty()) {
                            System.out.println("No tasks match this mood yet.");
                        } else {
                            for (Task t : suggestions) {
                                System.out.println(t);
                            }
                        }
                    } catch (Exception e) {
                        System.out.println(" Invalid mood.");
                    }
                }

                case 4 -> {
                    var all = taskManager.getAllTasks();
                    if (all.isEmpty()) {
                        System.out.println("No tasks added yet.");
                    } else {
                        for (Task t : all) {
                            System.out.println(t);
                        }
                    }
                }

                case 5 -> {
                    var logs = moodManager.getAllMoodLogs();
                    if (logs.isEmpty()) {
                        System.out.println("No mood logs found.");
                    } else {
                        for (MoodLog log : logs) {
                            System.out.println(log);
                        }
                    }
                }

                case 6 -> {
                    System.out.println("Goodbye!");
                    return;
                }

                default -> System.out.println("
