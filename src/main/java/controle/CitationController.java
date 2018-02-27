package controle;

import modele.classes.Citation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;

import static modele.bd.CitationMongo.ajoutCitationBD;
import static modele.bd.CitationMongo.getCitationBD;
import static modele.bd.CitationMongo.getCitationsBD;

/**
 * Created by Ulysse Blaineau on 22/02/18.
 */
@RestController
public class CitationController {

    // Prends une citation en entrée, lui met la date du jour et l'insère dans la base de donnée
    @RequestMapping("/newCitation")
    public Citation newCitation(@RequestParam(value="citationStr") String citationStr){
        Citation citation = new Citation(citationStr, new Date());

        ajoutCitationBD(citation);

        return citation;
    }

    // Prends une date en entrée pour retourner la citation du jour
    @RequestMapping("/getCitation")
    public Citation getCitation(@RequestParam(value="date") Date date){
        return getCitationBD(date);
    }

    // Retourne la liste de toutes les citations
    @RequestMapping("/getCitations")
    public ArrayList<Citation> getCitations(){

        return getCitationsBD();
    }
}
