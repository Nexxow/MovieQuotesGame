package modele.bd;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import modele.classes.Citation;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.mongodb.client.model.Filters.eq;
import static modele.bd.CitationMongo.*;
import static org.junit.Assert.*;

/**
 * Created by Nicolas Bourges on 12/03/18.
 */
public class CitationMongoTest {

  Connexion co;
  MongoDatabase database;
  Document doc;
  Citation citation;
  Date ajd;

  @Before
  public void initialize(){
     co = new Connexion();
     database = co.Connexion();
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    ajd = new Date();
    citation = new Citation("Le chemin le plus court est souvent le plus moche", ajd);
    doc = new Document("citation", citation.getQuote()).append("date", citation.getDate().toString());

  }

  @Test
  public void TestajoutCitationBD() throws Exception {
    ajoutCitationBD(citation);
    assertEquals("citation dans BD", citation, getCitationBD(ajd));
  }

  @Test
  public void  TestgetCitationBD() throws Exception {
    Citation citation2 = new Citation("Test", ajd);
    ajoutCitationBD(citation2);
    assertEquals("citation dans BD", citation2, getCitationBD(ajd));
  }
    @Test
  public void TestjavaToMongo() throws Exception {
    assertEquals("Test récup doc depuis citation",doc,javaToMongo(citation));
  }

  @Test
  public void getCitationsBD(){
    Citation citation2 = new Citation("Test", ajd);
    ArrayList<Citation> citations = new ArrayList<>();
    citations.add(citation);
    citations.add(citation2);
    ajoutCitationBD(citation);
    ajoutCitationBD(citation2);
   assertEquals("Récupérations de toutes les citations",citations,CitationMongo.getCitationsBD());
  }

  @Test
  public void TestmongoToJava() throws Exception {
assertEquals("Test récup citation depuis doc",citation,mongoToJava(doc));
  }

}