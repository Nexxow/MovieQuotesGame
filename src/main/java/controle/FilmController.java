package controle;

import modele.classes.Film;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;

import static modele.bd.FilmMongo.ajoutFilmBD;
import static modele.bd.FilmMongo.getFilmBD;
import static modele.bd.FilmMongo.getFilmsBD;

/**
 * Classe permettant de faire l'interface api pour les films
 * Created by Ulysse Blaineau on 27/02/18.
 */
@RestController
public class FilmController {

    /**
     * Prends un film en entrée et le rajoute dans la base de données
     * @param titre
     * @return film en json
     */
    @RequestMapping("/newFilm")
    public Film newFilm(@RequestParam(value="titre") String titre){
        Film film = new Film(titre, new Date(), "resume", "image lien");

        ajoutFilmBD(film);

        return film;
    }

    /**
     * Prends un titre en entrée et retourne le film
     * @param titre
     * @return film en json
     */
    @RequestMapping("/getFilmTitre")
    public Film getFilmTitre(@RequestParam(value="titre") String titre){
        return getFilmBD(titre);
    }

    /**
     * Prends un id en entrée et retourne le film
     * @param id
     * @return film en json
     */
    @RequestMapping("/getFilmId")
    public Film getFilmId(@RequestParam(value="id") int id){
        return getFilmBD(id);
    }

    /**
     * Retourne la liste de tous les films
     * @return les films en json
     */
    @RequestMapping("/getFilms")

    public ArrayList<Film> getFilms(){

        return getFilmsBD();
    }

}
