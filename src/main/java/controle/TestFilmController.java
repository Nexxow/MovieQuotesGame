package controle;

import metier.RecupCitation;
import metier.RecupFilms;
import modele.classes.Citation;
import modele.classes.Film;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import static modele.bd.FilmMongo.getFilmsBD;

/**
 * Classe permettant de faire l'interface api pour les films
 * Created by Ulysse Blaineau on 27/02/18.
 */
@RestController
public class TestFilmController {

    /**
     * Retourne la liste de tous les films
     * @return les films en json
     */
    @RequestMapping("/testFilm")

    public ArrayList<Film> getFilms(){
        new RecupFilms().ajouterFilmsMongo();
        return getFilmsBD();
    }

}