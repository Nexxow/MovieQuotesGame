package metier;

import modele.classes.Compte;
import modele.classes.Film;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static metier.Score.getClassement;
import static modele.bd.CompteMongo.ajoutCompteBD;
import static org.junit.Assert.*;

/**
 * Created by Nicolas Bourges on 13/03/18.
 */
public class ScoreTest {

Score score;
  @Test
  public void TestgetClassement() throws Exception {
    Compte compte = new Compte("pseudo", "test", "comédie", "mdp", "urlavatar");
    Compte compte1 = new Compte("toto", "mail", "science fiction", "mdp", "urlavatar1");
    ArrayList<Compte> comptes = new ArrayList<>();
    comptes.add(compte);
    comptes.add(compte1);
    compte.setScore(5);
    compte1.setScore(7);
    ajoutCompteBD(compte);
    ajoutCompteBD(compte1);
    assertNotEquals("Classement des comptes",comptes,getClassement());

  }

  @Test
  public void TestcompterScore() throws Exception {
    Compte compte = new Compte("pseudo", "test", "comédie", "mdp", "urlavatar");
    compte.setScore(1);
    DateFormat dateFormat = new SimpleDateFormat("yyyy");
    Date ajd = new Date();
    Film film = new Film(42, "titre", ajd, "resume", "lienImage", 5);
    compte.setFilmVote(film);
    ajoutCompteBD(compte);
    score.compterScore();
    assertEquals("Classement du joueur si bonne réponse",2,compte.getScore());

  }

}