package controle;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Ulysse Blaineau on 21/02/18.
 */

@RestController
public class TmdbApiController {

    String apiKey;
    String urlToRead = "https://api.themoviedb.org/3/movie/550?api_key=f426d1cd57c76ce8189d04c7d7656164";

    @RequestMapping("/getmovie")
    public String getMovie(){

        try {
            return getFromUrl("https://api.themoviedb.org/3/movie/550?api_key=f426d1cd57c76ce8189d04c7d7656164");
        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    public String getFromUrl(String urlToRead) throws IOException {

        StringBuilder result = new StringBuilder();

        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();

        return result.toString();
    }

}
