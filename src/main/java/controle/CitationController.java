package controle;

import metier.Vote;
import modele.bd.Connexion;
import modele.classes.Citation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Ulysse Blaineau on 22/02/18.
 * Classe permettant de faire l'interface api pour les citations
 */
@RestController
public class CitationController {

    Connexion co = new Connexion();
    private Vote vote = new Vote();

    /**
     * Prends une citation en entrée, lui met la date du jour et l'insère dans la base de données
     * @param citationStr
     *          correspond à la citation que l'on veut insérer
     * @return rien
     */
    @RequestMapping("/newCitation")
    public Citation newCitation(@RequestParam(value="citationStr") String citationStr){
        Citation citation = new Citation(citationStr, new Date());

        co.ajoutCitationBD(citation);

        return citation;
    }

    /**
     * Prends une date en entrée pour retourner la citation du jour
     * @param date
     *          la date de l'on veut la citation
     * @return
     */
    @RequestMapping("/getQuote")
    public Citation getCitation(@RequestParam(value="date") Date date){
        return co.getCitationBD(date);
    }

    /**
     * Méthode qui retourne la liste de toutes les citations
     * @return citations json
     */
    @RequestMapping("/getCitations")
    public ArrayList<Citation> getCitations(){
        for (Citation citation : co.getCitationsBD()){
            co.majCitationBD(vote.lienFilmCitation(citation));
        }
        return co.getCitationsBD();
    }
}
