package modele.bd;

import com.mongodb.client.MongoDatabase;
import modele.classes.Compte;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static modele.bd.CompteMongo.*;
import static org.junit.Assert.*;

/**
 * Created by Nicolas Bourges on 12/03/18.
 */
public class CompteMongoTest {

  Connexion co;
  MongoDatabase database;
  Compte compte;
  Document doc;

  @Before
  public void initialize(){
    co = new Connexion();
    database = co.Connexion();
    compte = new Compte("pseudo", "test", "comédie", "mdp", "urlavatar");

    Document doc = new Document("token", compte.getToken()).append("pseudo", compte.getPseudo()).append("mdp", compte.getMdp()).append("mail", compte.getMail()).append("lienAvatar",
            compte.getLienAvatar()).append("genrePrefere", compte.getGenrePrefere()).append("citationFav", compte.getCitationFav()).append("score", compte.getScore()).append("film", compte.getFilmVote().getTitle());
  }

  @Test
  public void TestajoutCompteBD() throws Exception {
    ajoutCompteBD(compte);
    assertEquals("compte dans BD", true, getCompteBD(compte.getToken()));
  }

  @Test
  public void TestmajCompteBD() throws Exception {
    ajoutCompteBD(compte);
    compte.setScore(compte.getScore()+1);
    majCompteBD(compte);
    assertEquals("MAJ compte",compte,getCompteBD(compte.getToken()));
  }

  @Test
  public void TestgetCompteBD() throws Exception {
    ajoutCompteBD(compte);
    assertEquals("compte dans BD", compte, getCompteBD(compte.getToken()));
  }

  @Test
  public void TestgetComptesBD() throws Exception {
    Compte compte2 = new Compte("toto","test","sf","mdp","lienAvatar");
    ArrayList<Compte> comptes = new ArrayList<>();
    comptes.add(compte);
    comptes.add(compte2);
    ajoutCompteBD(compte);
    ajoutCompteBD(compte2);
    assertEquals("Récupérations de tous les comptes",comptes,CitationMongo.getCitationsBD());
  }

  @Test
  public void TestjavaToMongo() throws Exception {
    assertEquals("Test envoi d'un compte java vers mongo",doc,javaToMongo(compte));
  }

  @Test
  public void TestmongoToJava() throws Exception {
    assertEquals("Test récupération d'un compte depuis Mongo vers java",compte,mongoToJava(doc));

  }

}