package controle;

import modele.classes.Citation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Ulysse Blaineau on 22/02/18.
 */
public class CitationController {

    private Citation[] listeCitations;

    public CitationController() {
        listeCitations = null;
    }

    // Recuperation de la citation du jour
    public Citation recuperationCitationJour() {
        
        Citation citationAjd = null;
        
        for (Citation citation : listeCitations){
            if (citation.estCitationJour()){ 
                citationAjd = citation;
            }
        }
        
        return citationAjd;
    }
}
