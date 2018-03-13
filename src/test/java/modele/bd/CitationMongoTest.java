package modele.bd;

import modele.classes.Citation;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by Nicolas Bourges on 12/03/18.
 */
public class CitationMongoTest {

    Connexion co;
    Document doc;
    Citation citation;
    Date ajd;


    @Before
    public void initialize(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        ajd = new Date();
        citation = new Citation("Le chemin le plus court est souvent le plus moche", ajd);
        doc = new Document("citation", citation.getQuote()).append("date", citation.getDate().toString());

    }

    @Test
    public void TestajoutCitationBD() throws Exception {
        co.ajoutCitationBD(citation);
        assertEquals("citation dans BD", citation, co.getCitationBD(ajd));
    }

    @Test
    public void TestmajCitationBD() throws Exception {
        co.ajoutCitationBD(citation);
        citation.setQuote("nouvelle citation");
        co.majCitationBD(citation);
        assertEquals("MAJ citation",citation, co.getCitationBD(ajd));
    }

    @Test
    public void  TestgetCitationBD() throws Exception {
        Citation citation2 = new Citation("Test", ajd);
        co.ajoutCitationBD(citation2);
        assertEquals("citation dans BD", citation2, co.getCitationBD(ajd));
    }
    @Test
    public void TestjavaToMongo() throws Exception {
        assertEquals("Test récup doc depuis citation",doc, co.javaToMongo(citation));
    }

    @Test
    public void getCitationsBD(){
        Citation citation2 = new Citation("Test", ajd);
        ArrayList<Citation> citations = new ArrayList<>();
        citations.add(citation);
        citations.add(citation2);
        co.ajoutCitationBD(citation);
        co.ajoutCitationBD(citation2);
        assertEquals("Récupérations de toutes les citations",citations,co.getCitationsBD());
    }

    @Test
    public void TestmongoToJava() throws Exception {
        assertEquals("Test récup citation depuis doc",citation,co.mongoToJavaCitation(doc));
    }

}