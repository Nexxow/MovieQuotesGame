package metier;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import modele.bd.Connexion;
import modele.classes.Citation;
import modele.classes.Citations;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by Lalatahiana Christophe on 12/03/18.
 * Classe permettant de récupérer la citation du jour et de l'ajouter dans la BD
 */
public class RecupCitation {

    private Connexion co = new Connexion();
    String urlToRead = "http://quotes.rest/qod.json";

    /**
     * Méthode qui retourne la citation du jour
     * @return
     */
    public Citation getQuote(){

        try {
            return getFromUrl(urlToRead);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Prends une url en entrée pour retourner une citation
     * @param urlToRead
     * @return
     * @throws IOException
     */
    public Citation getFromUrl(String urlToRead) throws IOException {

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

            return citations.getCitation();
        } else {

            return null;
        }
    }

    public void ajouterCitationMongo() {
        co.ajoutCitationBD(getQuote());
    }

}
