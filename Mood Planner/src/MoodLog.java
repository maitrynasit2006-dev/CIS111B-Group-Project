/**
 * Represents a single mood logging event in the Mood Planner system.
 * Each instance captures a user's mood at a specific point in time and is used by
 * {@link MoodManager} to maintain a history of the user's mood patterns.
 *
 * <p>This class is immutable and thread-safe, making it suitable for use in
 * concurrent environments.</p>
 *
 * @see MoodType
 * @see MoodManager
 */
import java.time.LocalDateTime;

public class MoodLog {

    /**
     * The unique identifier for this mood log entry.
     * Used for reference and retrieval purposes.
     */
    private final int id;
    
    /**
     * The type of mood that was logged.
     * Cannot be null and is set during construction.
     *
     * @see MoodType
     */
    private final MoodType moodType;
    
    /**
     * The exact date and time when the mood was logged.
     * Automatically set to the current date/time when the log is created.
     */
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

    /**
     * Returns the unique identifier of this mood log entry.
     *
     * @return the log entry's ID
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the type of mood that was logged.
     *
     * @return the mood type (never null)
     * @see MoodType
     */
    public MoodType getMoodType() {
        return moodType;
    }

    /**
     * Returns the date and time when this mood was logged.
     *
     * @return the timestamp of when the mood was recorded
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Returns a string representation of this mood log entry.
     * The string includes the ID, mood type, and timestamp.
     *
     * @return a string representation of this mood log
     */
    @Override
    public String toString() {
        return "MoodLog{id=" + id +
                ", moodType=" + moodType +
                ", timestamp=" + timestamp + "}";
    }
}
