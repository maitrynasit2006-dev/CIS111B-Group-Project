/**
 * Represents a single mood log entry with a timestamp.
 * This class is used by {@link MoodManager} to track and manage historical mood entries.
 * Each mood log contains a unique ID, a mood type, and a timestamp of when the mood was logged.
 *
 * <p>This class is immutable and thread-safe.</p>
 *
 * @see MoodManager
 * @see MoodType
 */
import java.time.LocalDateTime;

public class MoodLog {

    /** Unique identifier for this mood log entry. */
    private final int id;

    /** The type of mood that was logged. */
    private final MoodType moodType;

    /** The date and time when the mood was logged. */
    private final LocalDateTime timestamp;

    /**
     * Creates a new mood log entry.
     *
     * @param id       unique identifier for this log
     * @param moodType the mood that was logged (cannot be null)
     * @throws IllegalArgumentException if moodType is null
     */
    public MoodLog(int id, MoodType moodType) {
        if (moodType == null) {
            throw new IllegalArgumentException("MoodType cannot be null");
        }
        this.id = id;
        this.moodType = moodType;
        this.timestamp = LocalDateTime.now();
    }

    /**
     * Returns the unique identifier of this mood log entry.
     *
     * @return the mood log ID
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the type of mood that was logged.
     *
     * @return the mood type
     * @see MoodType
     */
    public MoodType getMoodType() {
        return moodType;
    }

    /**
     * Returns the date and time when this mood was logged.
     *
     * @return the timestamp of the mood log entry
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Returns a string representation of this mood log entry.
     * The string includes the ID, mood type, and timestamp.
     *
     * @return a string representation of the mood log
     */
    @Override
    public String toString() {
        return "MoodLog{id=" + id +
                ", moodType=" + moodType +
                ", timestamp=" + timestamp + "}";
    }
}
