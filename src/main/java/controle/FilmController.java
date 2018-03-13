package controle;

import modele.bd.Connexion;
import modele.classes.Film;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;

/**
 * Classe permettant de faire l'interface api pour les films
 * Created by Ulysse Blaineau on 27/02/18.
 */
@CrossOrigin
@RequestMapping("/")
@RestController
public class FilmController {

    Connexion co = new Connexion();
    /**
     * Retourne la liste de tous les films
     * @return la liste des films en json
     */
    @RequestMapping("/getFilms")

    public ArrayList<Film> getFilms(){

        return co.getFilmsBD();
    }

}
