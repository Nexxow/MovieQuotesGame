package metier;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Nicolas Bourges on 13/03/18.
 */
public class RecupCitationTest {

  String urlToRead = "http://quotes.rest/qod.json";
  RecupCitation recupCitation;

  @Test
  public void TestgetQuote() throws Exception {
    assertTrue("Récupération d'une citation", recupCitation.getQuote() != null);
  }

  @Test
  public void TestgetFromUrl() throws Exception {
    assertTrue("Récupération d'une citation depuis une url", recupCitation.getFromUrl(urlToRead) != null);
  }

}