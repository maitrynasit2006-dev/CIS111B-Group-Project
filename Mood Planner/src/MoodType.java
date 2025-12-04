/**
 * Enumerates the high-level mood states a user can log.
 * Each mood provides a label, description, and a recommended
 * effort level for activity suggestions.
 */
public enum MoodType {

    /**
     * The user feels low energy or mentally tired.
     * Recommends low-effort activities.
     */
    TIRED("Tired", "Low energy; suggests light activities.", LevelMood.LOW),

    /**
     * The user feels okay / neutral.
     * Recommends medium-effort activities.
     */
    NEUTRAL("Neutral", "Balanced mood; suggests moderate activities.", LevelMood.MEDIUM),

    /**
     * The user feels energetic and highly motivated.
     * Recommends high-effort activities.
     */
    ENERGETIC("Energetic", "High energy; suggests demanding activities.", LevelMood.HIGH);

    /** Short label that can be shown in the UI. */
    private final String displayLabel;

    /** Longer description explaining the mood state. */
    private final String description;

    /** Effort level that best matches this mood for suggestions. */
    private final LevelMood recommendedEffortLevel;

    MoodType(String displayLabel, String description, LevelMood recommendedEffortLevel) {
        this.displayLabel = displayLabel;
        this.description = description;
        this.recommendedEffortLevel = recommendedEffortLevel;
    }

    public String getDisplayLabel() {
        return displayLabel;
    }

    public String getDescription() {
        return description;
    }

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

    @Override
    public String toString() {
        return displayLabel;
    }
}