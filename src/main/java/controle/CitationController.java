package controle;

import modele.classes.Citation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;

import static metier.Vote.lienFilmCitation;
import static modele.bd.CitationMongo.*;

/**
 * Created by Ulysse Blaineau on 22/02/18.
 * Classe permettant de faire l'interface api pour les citations
 */
@RestController
public class CitationController {

    /**
     * Prends une citation en entrée, lui met la date du jour et l'insère dans la base de données
     * @param citationStr
     *          correspond à la citation que l'on veut insérer
     * @return rien
     */
    @RequestMapping("/newCitation")
    public Citation newCitation(@RequestParam(value="citationStr") String citationStr){
        Citation citation = new Citation(citationStr, new Date());

        ajoutCitationBD(citation);

        return citation;
    }

    /**
     * Prends une date en entrée pour retourner la citation du jour
     * @param date
     *          la date de l'on veut la citation
     * @return
     */
    @RequestMapping("/getCitation")
    public Citation getCitation(@RequestParam(value="date") Date date){
        return getCitationBD(date);
    }

    /**
     * Méthode qui retourne la liste de toutes les citations
     * @return citations json
     */
    @RequestMapping("/getCitations")
    public ArrayList<Citation> getCitations(){
        for (Citation citation : getCitationsBD()){
            majCitationBD(lienFilmCitation(citation));
        }
        return getCitationsBD();
    }
}
