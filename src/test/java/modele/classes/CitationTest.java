package modele.classes;

import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Ulysse Blaineau on 22/02/18.
 */
public class CitationTest {
    @Test
    public void estCitationJour() throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date ajd = new Date();
        Citation citation = new Citation("Le chemin le plus court est souvent le plus moche", ajd);
        assertTrue("Citation du jour", citation.estCitationJour());
    }


}