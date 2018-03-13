package metier;

import modele.classes.Compte;
import org.junit.Before;
import org.junit.Test;

import static modele.bd.CompteMongo.ajoutCompteBD;
import static org.junit.Assert.*;

/**
 * Created by Nicolas Bourges on 13/03/18.
 */
public class ScoreTest {


  @Test
  public void getClassement() throws Exception {
    Compte compte = new Compte("pseudo", "test", "comédie", "mdp", "urlavatar");
    Compte compte1 = new Compte("toto", "mail", "science fiction", "mdp", "urlavatar1");
    ajoutCompteBD(compte);
    ajoutCompteBD(compte1);

  }

  @Test
  public void compterScore() throws Exception {
  }

}