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
 * Created by Ulysse Blaineau on 27/02/18.
 */
@RestController
public class FilmController {

    // Prends un film en entrée et le rajoute dans la base de donnée
    @RequestMapping("/newFilm")
    public Film newFilm(@RequestParam(value="titre") String titre){
        Film film = new Film(titre, new Date(), "resume", "image lien");

        ajoutFilmBD(film);

        return film;
    }

    // Prends un titre en entrée et retourne le film
    @RequestMapping("/getFilm")
    public Film getFilm(@RequestParam(value="titre") String titre){
        return getFilmBD(titre);
    }

    // Retourne la liste de tous les films
    @RequestMapping("/getFilms")
    public ArrayList<Film> getFilms(){

        return getFilmsBD();
    }

}
