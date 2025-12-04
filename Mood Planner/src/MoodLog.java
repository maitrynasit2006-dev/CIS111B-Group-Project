/**
 * Stores a single mood entry with a timestamp.
 * Used by MoodManager to track historical moods.
 */
import java.time.LocalDateTime;

public class MoodLog {

    private final int id;
    private final MoodType moodType;
    private final LocalDateTime timestamp;

    /**
     * Creates a new mood log entry.
     *
     * @param id       unique identifier for this log
     * @param moodType the mood that was logged (cannot be null)
     */
    public MoodLog(int id, MoodType moodType) {
        if (moodType == null) {
            throw new IllegalArgumentException("MoodType cannot be null");
        }
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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "MoodLog{id=" + id +
                ", moodType=" + moodType +
                ", timestamp=" + timestamp + "}";
    }
}