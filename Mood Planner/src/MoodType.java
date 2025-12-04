/**
 * Enumerates the high-level mood states a user can log in the Mood Planner system.
 * Each mood constant includes a display label, detailed description, and a recommended
 * effort level that's used to suggest appropriate activities.
 *
 * @see LevelMood
 * @see ActivityManager#suggestActivitiesByMood(MoodType)
 */
public enum MoodType {

    /**
     * Represents a state of low energy or mental fatigue.
     * Activities suggested for this mood should be low-effort and relaxing.
     * Example: "Go for a short walk" or "Listen to calming music".
     */
    TIRED("Tired", "Low energy; suggests light activities.", LevelMood.LOW),

    /**
     * Represents a balanced, neutral emotional state.
     * Activities suggested for this mood should be of moderate effort.
     * Example: "Work on a personal project" or "Do some light exercise".
     */
    NEUTRAL("Neutral", "Balanced mood; suggests moderate activities.", LevelMood.MEDIUM),

    /**
     * Represents a state of high energy and motivation.
     * Activities suggested for this mood can be more demanding and challenging.
     * Example: "Go for a run" or "Tackle a difficult task".
     */
    ENERGETIC("Energetic", "High energy; suggests demanding activities.", LevelMood.HIGH);

    /**
     * The display-friendly name of the mood, suitable for UI elements.
     * This is a short, user-friendly label (e.g., "Tired", "Energetic").
     */
    private final String displayLabel;

    /**
     * A more detailed description of what this mood represents.
     * This provides context about the mood and its characteristics.
     */
    private final String description;

    /**
     * The recommended effort level for activities when in this mood.
     * Used by the suggestion system to match activities to the user's current state.
     *
     * @see LevelMood
     */
    private final LevelMood recommendedEffortLevel;

    /**
     * Constructs a new MoodType with the specified properties.
     * This constructor is private as enum constructors are implicitly private.
     *
     * @param displayLabel the display name of the mood
     * @param description a detailed description of the mood
     * @param recommendedEffortLevel the suggested effort level for this mood
     */
    MoodType(String displayLabel, String description, LevelMood recommendedEffortLevel) {
        this.displayLabel = displayLabel;
        this.description = description;
        this.recommendedEffortLevel = recommendedEffortLevel;
    }

    /**
     * Returns the display-friendly name of this mood.
     *
     * @return the display label of the mood
     */
    public String getDisplayLabel() {
        return displayLabel;
    }

    /**
     * Returns the detailed description of this mood.
     *
     * @return a string describing the mood in more detail
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the recommended effort level for activities when in this mood.
     * This is used by the suggestion system to match activities to the user's mood.
     *
     * @return the recommended effort level for this mood
     * @see LevelMood
     */
    public LevelMood getRecommendedEffortLevel() {
        return recommendedEffortLevel;
    }

    /**
     * Parses a user-provided string into a MoodType.
     * Accepts values like "tired", "NEUTRAL", "Energetic".
     *
     * @param value text representing the mood
     * @return the matching MoodType
     * @throws IllegalArgumentException if the value does not match a known mood
     */
    public static MoodType fromUserInput(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Mood text cannot be null.");
        }

        String normalized = value.trim().toUpperCase();

        return switch (normalized) {
            case "TIRED" -> TIRED;
            case "NEUTRAL" -> NEUTRAL;
            case "ENERGETIC" -> ENERGETIC;
            default -> throw new IllegalArgumentException("Unknown mood: " + value);
        };
    }

    /**
     * Returns a string representation of this mood.
     * By default, this returns the display label of the mood.
     *
     * @return the display label of the mood
     */
    @Override
    public String toString() {
        return displayLabel;
    }
}
