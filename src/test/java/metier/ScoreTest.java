package metier;

import modele.bd.Connexion;
import modele.classes.Compte;
import modele.classes.Film;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Nicolas Bourges on 13/03/18.
 */
public class ScoreTest {
Connexion co = new Connexion();
Score score;

  @Test
  public void TestcompterScore() throws Exception {
    Compte compte = new Compte("pseudo", "test", "comédie", "mdp", "urlavatar");
    compte.setScore(1);
    DateFormat dateFormat = new SimpleDateFormat("yyyy");
    Date ajd = new Date();
    Film film = new Film(42, "titre", ajd, "resume", "lienImage", 5);
    compte.setFilmVote(film);
    co.ajoutCompteBD(compte);
    score.compterScore();
    assertEquals("Classement du joueur si bonne réponse",2,compte.getScore());

  }

}