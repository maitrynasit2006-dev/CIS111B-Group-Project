/**
 * Manages all tasks in an ArrayList.
 * Supports adding tasks, retrieving tasks,
 * and suggesting tasks based on the user's mood.
 */

import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskManager {

    private ArrayList<Task> tasks = new ArrayList<>();
    private int nextId = 1;

    public void addTask(Task task) {
        tasks.add(task);
    }

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Suggests tasks based on user's current mood.
     * TIRED -> LOW effort
     * NEUTRAL -> MEDIUM effort
     * ENERGETIC -> HIGH effort
     */
    public ArrayList<Task> suggestTasksByMood(MoodType mood) {

        LevelMood targetEffort;

        switch (mood) {
            case TIRED -> targetEffort = LevelMood.LOW;
            case NEUTRAL -> targetEffort = LevelMood.MEDIUM;
            default -> targetEffort = LevelMood.HIGH;
        }

        ArrayList<Task> result = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getEffortLevel() == targetEffort) {
                result.add(task);
            }
        }

        return result;
    }

    // ------------------------------------------------------
    // ⭐ FILE I/O SECTION — Saving and Loading Tasks
    // ------------------------------------------------------

    /**
     * Saves all tasks to a text file.
     * Each line = one task
     */
    public void saveTasksToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(filename)) {

            for (Task t : tasks) {
                writer.println(
                        t.getId() + "," +
                                t.getTitle() + "," +
                                t.getDescription() + "," +
                                t.getEffortLevel() + "," +
                                t.getTypeLabel()      // School or Personal
                );
            }

            System.out.println("Tasks saved to " + filename);

        } catch (Exception e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from a text file.
     * Every line in the file must match: id,title,desc,effort,type
     * Example:
     * 1,Do homework,Chapter 8,MEDIUM,School
     */
    public void loadTasksFromFile(String filename) {
        try (Scanner sc = new Scanner(new File(filename))) {

            while (sc.hasNextLine()) {
                String[] parts = sc.nextLine().split(",");

                int id = Integer.parseInt(parts[0]);
                String title = parts[1];
                String desc = parts[2];
                LevelMood level = LevelMood.valueOf(parts[3]);
                String type = parts[4];

                // Build the correct task subclass
                if (type.equalsIgnoreCase("School")) {
                    tasks.add(new SchoolTask(id, title, desc, level, LocalDate.now(), "Unknown"));
                } else {
                    tasks.add(new PersonalTask(id, title, desc, level, LocalDate.now()));
                }
            }

            System.out.println("Tasks loaded from " + filename);

        } catch (Exception e) {
            System.out.println(" Error loading tasks: " + e.getMessage());
        }
    }
}
