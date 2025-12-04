/**
 * Manages all MoodLog objects in an ArrayList.
 * Supports adding mood logs and retrieving mood history.
 */
import java.util.ArrayList;

public class MoodManager {

    /** List to store all mood log entries. */
    private final ArrayList<MoodLog> moodLogs = new ArrayList<>();

    /** Counter to generate unique IDs for each mood log entry. */
    private int nextId = 1;

    /**
     * Logs a new mood entry.
     *
     * @param mood the mood to log (cannot be null)
     * @throws IllegalArgumentException if mood is null
     */
    public void logMood(MoodType mood) {
        if (mood == null) {
            throw new IllegalArgumentException("Mood cannot be null");
        }
        moodLogs.add(new MoodLog(nextId++, mood));
    }

    /**
     * Retrieves all mood log entries in the system.
     *
     * @return a new list containing all mood logs, ordered by creation time
     */
    public ArrayList<MoodLog> getAllMoodLogs() {
        return new ArrayList<>(moodLogs);
    }
}