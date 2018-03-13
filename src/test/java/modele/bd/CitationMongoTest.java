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
import static org.junit.Assert.assertTrue;

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
        co = new Connexion();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        ajd = new Date();
        citation = new Citation("Le chemin le plus court est souvent le plus moche", ajd);
        doc = new Document();

    }

    @Test
    public void TestajoutCitationBD() throws Exception {
        citation = new Citation("Le chemin le plus court est souvent le plus moche", ajd);
        co.ajoutCitationBD(citation);
        assertTrue("citation dans BD",  co.getCitationBD(ajd)!= null);
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
        assertTrue("citation dans BD",co.getCitationsBD()!= null);
    }
    @Test
    public void TestjavaToMongo() throws Exception {
        assertTrue("Test récup doc depuis citation", co.javaToMongo(citation)!= null);
    }

    @Test
    public void TestgetCitationsBD(){
        Citation citation2 = new Citation("Test", ajd);
        co.ajoutCitationBD(citation);
        co.ajoutCitationBD(citation2);
        assertTrue("Récupérations de toutes les citations",co.getCitationsBD()!=null);
    }

    @Test
    public void TestmongoToJava() throws Exception {
        assertTrue("Test récup citation depuis doc",co.mongoToJavaCitation(doc)!= null);
    }

}