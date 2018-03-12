package modele.classes;

import org.junit.Before;
import org.junit.Test;

import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Nicolas Bourges on 12/03/18.
 */
public class CompteTest {
  Compte compte;
  Date ajd;
  Film filmVote;
  @Before
  public void initialize() {
    compte = new Compte("pseudo", "test", "comédie", "mdp", "urlavatar");
    DateFormat dateFormat = new SimpleDateFormat("yyyy");
    ajd = new Date();
    filmVote = new Film("title",ajd,"resume","url");
  }

  @Test
  public void generateToken() throws Exception {
    compte.generateToken();
    assertTrue("Token généré",compte != null);
  }

  @Test
  public void voter() throws Exception {
    assertTrue("vote pour le film",compte.voter(filmVote));
  }

  @Test
  public void reinitialiseVote() throws Exception {
    compte.reinitialiseVote();
assertTrue("film vote = null",compte.getFilmVote()==null);  }

}