package controle;

import metier.RecupCitation;
import metier.Vote;
import modele.bd.Connexion;
import modele.classes.Citation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Created by Ulysse Blaineau on 22/02/18.
 * Classe permettant de faire l'interface api pour les citations
 */
@RestController
@CrossOrigin
@RequestMapping("/")
public class CitationController {

    Connexion co = new Connexion();
    private Vote vote = new Vote();

    /**
     * Méthode qui retourne la citation du jour
     * @return la citation du jour
     */
    @RequestMapping("/getCitationJour")
    public Citation getCitationJour() {
        Citation laCitation = null;
        for (Citation citation : co.getCitationsBD()) {
            if (citation.estCitationJour()) {
                laCitation = citation;
            }
        }
        return laCitation;
    }

    /**
     * Méthode qui retourne la liste de toutes les citations
     * @return la liste de toutes les citations
     */
    @RequestMapping("/getCitations")
    public ArrayList<Citation> getCitations(){
        return co.getCitationsBD();
    }


    /**
     * Méthode qui fait une requête pour la citation du jour
     */
    @RequestMapping("/newCitation")
    public void newCitation(){
        new RecupCitation().ajouterCitationMongo();
    }
}
