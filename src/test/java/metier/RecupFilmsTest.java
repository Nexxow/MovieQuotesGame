package metier;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Nicolas Bourges on 13/03/18.
 */
public class RecupFilmsTest {
  RecupFilms recupfilms;
  String apiKey;
  String urlToRead = "https://api.themoviedb.org/3/discover/movie?api_key=f426d1cd57c76ce8189d04c7d7656164&sort_by=popularity.desc";

  @Test
  public void TestgetMovies() throws Exception {
    assertTrue("récupération des films", recupfilms.getMovies() != null);
  }

  @Test
  public void TestgetFromUrl() throws Exception {
    assertTrue("récupération depuis l'url à lire", recupfilms.getFromUrl(urlToRead) != null);
  }

}