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
     * Prends un film en entrée et le rajoute dans la base de données
     * @param titre le titre du film
     * @return  le film en json
     */
    @RequestMapping("/newFilm")
    public Film newFilm(@RequestParam(value="titre") String titre){
        Film film = new Film(titre, new Date(), "resume", "image lien");

        co.ajoutFilmBD(film);

        return film;
    }

    /**
     * Prends un titre en entrée et retourne le film
     * @param titre le titre du film
     * @return le film en json
     */
    @RequestMapping("/getFilmTitre")
    public Film getFilmTitre(@RequestParam(value="titre") String titre){
        return co.getFilmBD(titre);
    }

    /**
     * Prends un id en entrée et retourne le film
     * @param id l'identifiant du film
     * @return le film en json
     */
    @RequestMapping("/getFilmId")
    public Film getFilmId(@RequestParam(value="id") int id){
        return co.getFilmBD(id);
    }

    /**
     * Retourne la liste de tous les films
     * @return la liste des films en json
     */
    @RequestMapping("/getFilms")

    public ArrayList<Film> getFilms(){

        return co.getFilmsBD();
    }

}
