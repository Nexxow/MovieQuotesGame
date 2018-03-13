package metier;

import com.google.gson.Gson;
import modele.bd.Connexion;
import modele.classes.Film;
import modele.classes.ListeFilms;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Ulysse Blaineau on 21/02/18.
 */

public class RecupFilms {

    private Connexion co = new Connexion();

    private String apiKey = "f426d1cd57c76ce8189d04c7d7656164";
    private String urlToRead = "https://api.themoviedb.org/3/discover/movie?api_key="+apiKey+"&sort_by=popularity.desc";

    public ArrayList<Film> getMovies(){

        try {
            return getFromUrl(urlToRead);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Film> getFromUrl(String urlToRead) throws IOException {

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

            ListeFilms films = new Gson().fromJson(response.toString(), ListeFilms.class);

            return films.getFilms();
        } else {

            return null;
        }
    }

    public void ajouterFilmsMongo() {
        for(Film film : getMovies()) {
            co.ajoutFilmBD(film);
        }
    }



}
