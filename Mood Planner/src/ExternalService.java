import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Handles calls to an external web API to retrieve motivational quotes.
 * Demonstrates use of Java networking classes and external services.
 */
public class ExternalService {

    /**
     * Calls the ZenQuotes API and returns a motivational quote.
     * If anything goes wrong (no internet, bad response, etc.), a local
     * fallback quote is returned instead.
     *
     * @return a quote in the format: "Quote text — Author"
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
     * Returns a motivational quote decorated for a specific mood.
     * Still calls the external API, but wraps the quote with a short,
     * mood-specific header.
     *
     * @param mood the user's current mood (can be null)
     * @return a decorated quote string for that mood
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