package controle;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import modele.classes.Citations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by Lalatahiana Christophe on 12/03/18.
 */

@RestController
public class TheySaidSoApiController {
    String urlToRead = "http://quotes.rest/qod.json";

    @RequestMapping("/getquote")
    public Citations getQuote(){

        try {
            return getFromUrl(urlToRead);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Citations getFromUrl(String urlToRead) throws IOException {

        StringBuilder result = new StringBuilder();

        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        int responseCode = conn.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(response.toString());

            // récupération de l'objet json { "quotes" : [...] }
            String resQuote = element.getAsJsonObject().getAsJsonObject("contents").toString();

            // conversion en objet
            Citations citations = new Gson().fromJson(resQuote, Citations.class);

            return citations;
        } else {

            return null;
        }
    }

}
