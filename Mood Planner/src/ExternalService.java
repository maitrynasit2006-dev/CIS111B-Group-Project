import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Provides access to external web services, primarily for fetching motivational quotes.
 * This class handles all external API communications and provides fallback mechanisms
 * when external services are unavailable.
 *
 * <p>The current implementation uses the ZenQuotes API to fetch random motivational quotes
 * and includes a local fallback mechanism. It demonstrates proper error handling and
 * network request management in Java.</p>
 *
 * <p>This class is thread-safe and can be used across multiple threads.</p>
 *
 * @see MoodType
 */
public class ExternalService {

    /**
     * Fetches a random motivational quote from the ZenQuotes API.
     * This method performs a synchronous HTTP GET request to the external API.
     *
     * <p><b>Implementation Details:</b></p>
     * <ul>
     *   <li>Makes a request to "https://zenquotes.io/api/random"</li>
     *   <li>Sets a connection timeout of 5 seconds</li>
     *   <li>Parses the JSON response to extract quote and author</li>
     *   <li>Returns a fallback quote if any error occurs</li>
     * </ul>
     *
     * @return a formatted string containing the quote and author in the format:
     *         "Quote text — Author"
     * @throws RuntimeException if there's an error processing the API response
     *         (note: most errors are caught and result in a fallback quote)
     */
    public String getMotivationalQuote() {

        String apiUrl = "https://zenquotes.io/api/random";

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);

            StringBuilder response = new StringBuilder();
            try (BufferedReader reader =
                         new BufferedReader(new InputStreamReader(conn.getInputStream()))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            }

            // Example response: [{"q":"Quote here","a":"Author"}]
            String json = response.toString();

            // Extract quote text and author (very simple parsing)
            String quote = json.split("\"q\":\"")[1].split("\",")[0];
            String author = json.split("\"a\":\"")[1].split("\"")[0];

            return quote + " — " + author;

        } catch (Exception e) {
            // Fallback if the API is not available
            return "Stay positive and keep moving! — Offline mode";
        }
    }

    /**
     * Retrieves a motivational quote tailored to the user's current mood.
     * This method wraps the quote with a mood-appropriate header to provide
     * more contextual motivation.
     *
     * <p>If the provided mood is null, the base quote is returned without
     * any additional decoration.</p>
     *
     * <p><b>Mood-specific decorations:</b></p>
     * <ul>
     *   <li><b>TIRED:</b> Adds "Gentle encouragement for when you're TIRED"</li>
     *   <li><b>NEUTRAL:</b> Adds "A little boost for your NEUTRAL day"</li>
     *   <li><b>ENERGETIC:</b> Adds "Fuel for your ENERGETIC mood"</li>
     * </ul>
     *
     * @param mood the user's current mood, or null for no mood-specific decoration
     * @return a formatted string containing a mood-appropriate header followed by the quote
     * @see #getMotivationalQuote()
     * @see MoodType
     */
    public String getMotivationalQuoteForMood(MoodType mood) {
        String baseQuote = getMotivationalQuote();

        if (mood == null) {
            return baseQuote;
        }

        return switch (mood) {
            case TIRED -> "Gentle encouragement for when you're TIRED:\n\n" + baseQuote;
            case NEUTRAL -> "A little boost for your NEUTRAL day:\n\n" + baseQuote;
            case ENERGETIC -> "Fuel for your ENERGETIC mood:\n\n" + baseQuote;
        };
    }
}
