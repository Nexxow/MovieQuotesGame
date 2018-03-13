package controle;

import metier.RecupCitation;
import modele.bd.Connexion;
import modele.classes.Citation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;

/**
 * Classe permettant de faire l'interface api pour les films
 * Created by Ulysse Blaineau on 27/02/18.
 */
@RestController
public class TestFilmController {

    Connexion co = new Connexion();

    /**
     * Retourne la liste de tous les films
     * @return les films en json
     */
    @RequestMapping("/testFilm")

    public ArrayList<Citation> getFilms(){
        new RecupCitation().ajouterCitationMongo();
        return co.getCitationsBD();
    }

}