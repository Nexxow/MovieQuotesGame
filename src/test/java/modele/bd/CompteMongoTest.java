package modele.bd;

import com.mongodb.client.MongoDatabase;
import modele.classes.Compte;
import org.junit.Before;
import org.junit.Test;

import static modele.bd.CompteMongo.ajoutCompteBD;
import static modele.bd.CompteMongo.getCompteBD;
import static org.junit.Assert.*;

/**
 * Created by Nicolas Bourges on 12/03/18.
 */
public class CompteMongoTest {

  Connexion co;
  MongoDatabase database;
  Compte compte;

  @Before
  public void initialize(){
    co = new Connexion();
    database = co.Connexion();
    compte = new Compte("pseudo", "test", "comédie", "mdp", "urlavatar");
  }

  @Test
  public void TestajoutCompteBD() throws Exception {
    ajoutCompteBD(compte);
    assertEquals("compte dans BD", true, getCompteBD(compte.getToken()));
  }

  @Test
  public void TestmajCompteBD() throws Exception {

  }

  @Test
  public void TestgetCompteBD() throws Exception {
  }

  @Test
  public void TestgetComptesBD() throws Exception {
  }

  @Test
  public void TestjavaToMongo() throws Exception {
  }

  @Test
  public void TestmongoToJava() throws Exception {
  }

}