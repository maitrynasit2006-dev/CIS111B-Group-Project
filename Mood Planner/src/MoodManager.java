/**
 * Manages all MoodLog objects in an ArrayList.
 * Supports adding mood logs and retrieving history.
 */
import java.util.ArrayList;

/**
 * Manages all MoodLog objects in an ArrayList.
 * Supports adding mood logs and retrieving mood history.
 * This class is responsible for tracking and managing user mood entries.
 */
public class MoodManager {

    /** List to store all mood log entries */
    private ArrayList<MoodLog> moodLogs = new ArrayList<>();
    
    /** Counter for generating unique mood log IDs */
    private int nextId = 1;

    /**
     * Logs a new mood entry with the current timestamp.
     * Automatically assigns the next available ID to the mood log.
     *
     * @param mood the mood to log (TIRED, NEUTRAL, or ENERGETIC)
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
     * @return a list containing all mood logs, ordered by creation time (oldest first)
     */
    public ArrayList<MoodLog> getAllMoodLogs() {
        return moodLogs;
    }
}
