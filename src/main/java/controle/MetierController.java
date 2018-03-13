package controle;

import metier.Score;
import metier.Vote;
import modele.classes.Compte;
import modele.classes.Film;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;

import static metier.Vote.addScoreFilm;
import static modele.bd.CompteMongo.getCompteBD;
import static modele.bd.CompteMongo.majCompteBD;
import static modele.bd.FilmMongo.getFilmBD;
import static modele.bd.FilmMongo.majFilmBD;

/**
 * Classe permettant de faire l'interface api pour le coté metier de l'application
 * Created by Ulysse Blaineau on 28/02/18.
 */
@RestController
public class MetierController {

    /**
     * Prends un token et un id en entrée pour rajouter un vote de film au compte
     * @param token de l'utilisateur
     * @param id du film
     * @return le compte si le vote fonctionne, null sinon
     */
    @RequestMapping("/voteFilm")
    public Compte voteFilm(@RequestParam(value="token") String token, @RequestParam int id){

        Compte compte = getCompteBD(token);
        Film film = getFilmBD(id);
        boolean err = compte.voter(film);

        if (err) {
            // On met à jour le score du film
            addScoreFilm(film);
            majFilmBD(film);
            // On met à jour la base de données
            majCompteBD(compte);
            return compte;
        }
        // return null si l'utilisateur a déjà voté
        else {
            return null;
        }
    }

    @RequestMapping("/getScoreFilm")
    public Object getScoreFilm(@RequestParam String titre){
        Film film = getFilmBD(titre);
        if (film != null){
            return film.getScore();
        }
        else {
            return null;
        }

    }

    @RequestMapping("/getPremierFilm")
    public Object getPremierFilm(){
        Film film =  Vote.getPremierFilm();
        if (film == null) {
            return "Erreur, pas de votes encore";
        }
        else {
            return film;
        }
    }

    @RequestMapping("/getClassement")
    public ArrayList<Compte> getClassement(){
        return Score.getClassement();
    }

}
