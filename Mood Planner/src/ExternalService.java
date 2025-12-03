import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ExternalService {

    public String getMotivationalQuote() {

        String apiUrl = "https://zenquotes.io/api/random";

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream())
            );

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = in.readLine()) != null) {
                response.append(line);
            }

            in.close();

            // Example response: [{"q":"Quote here","a":"Author"}]
            String json = response.toString();

            // Extract quote text manually (simple parsing)
            String quote = json.split("\"q\":\"")[1].split("\",")[0];
            String author = json.split("\"a\":\"")[1].split("\"")[0];

            return quote + " — " + author;

        } catch (Exception e) {
            return "Stay positive and keep moving! (offline mode)";
        }
    }
}
