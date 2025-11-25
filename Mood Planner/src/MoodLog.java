/**
 * Stores a single mood entry with a timestamp.
 * Used by MoodManager to track historical moods.
 */
import java.time.LocalDateTime;

public class MoodLog {
    private int id;
    private MoodType moodType;
    private LocalDateTime timestamp;

    public MoodLog(int id, MoodType moodType) {
        this.id = id;
        this.moodType = moodType;
        this.timestamp = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public MoodType getMoodType() {
        return moodType;
    }

    @Override
    public String toString() {
        return "MoodLog{id=" + id + ", moodType=" + moodType +
                ", timestamp=" + timestamp + "}";
    }
}
