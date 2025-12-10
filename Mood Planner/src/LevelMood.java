/**
 * Enumerates the possible effort levels that can be associated with activities.
 * This enum is used to categorize activities based on the amount of effort they require,
 * which helps in suggesting appropriate activities based on the user's current mood.
 *
 * <p>The effort levels are used in conjunction with {@link MoodType} to provide
 * personalized activity suggestions through the {@link ActivityManager}.</p>
 *
 * @see Activity
 * @see ActivityManager
 * @see MoodType
 */
public enum LevelMood {
    /**
     * Represents activities that require minimal physical or mental effort.
     * These are suitable when the user is feeling tired or low on energy.
     * Examples: Taking a short walk, reading a book, or meditating.
     */
    LOW,
    /**
     * Represents activities that require a moderate amount of effort.
     * These are suitable when the user is feeling neutral or balanced.
     * Examples: Going for a run, working on a hobby, or running errands.
     */
    MEDIUM,
    /**
     * Represents activities that require significant physical or mental effort.
     * These are suitable when the user is feeling energetic and motivated.
     * Examples: Intense workout, starting a new project, or tackling challenging tasks.
     */
    HIGH
}
