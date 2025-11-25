/**
 * Manages all MoodLog objects in an ArrayList.
 * Supports adding mood logs and retrieving history.
 */
import java.util.ArrayList;

public class MoodManager {

    private ArrayList<MoodLog> moodLogs = new ArrayList<>();
    private int nextId = 1;

    public void logMood(MoodType mood) {
        moodLogs.add(new MoodLog(nextId++, mood));
    }

    public ArrayList<MoodLog> getAllMoodLogs() {
        return moodLogs;
    }
}
