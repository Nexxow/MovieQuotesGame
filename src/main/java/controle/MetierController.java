package controle;

import metier.Score;
import metier.Vote;
import modele.bd.Connexion;
import modele.classes.Compte;
import modele.classes.Film;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Classe permettant de faire l'interface api pour le coté metier de l'application
 * Created by Ulysse Blaineau on 28/02/18.
 */
@CrossOrigin
@RequestMapping("/")
@RestController
public class MetierController {

    Score score = new Score();
    Vote vote = new Vote();
    Connexion co = new Connexion();

    /**
     * Prends un token et un id en entrée pour rajouter un vote de film au compte
     * @param token de l'utilisateur
     * @param id du film
     * @return le compte si le vote fonctionne, null sinon
     */
    @RequestMapping("/voteFilm")
    public Compte voteFilm(@RequestParam(value="token") String token, @RequestParam int id){

        Compte compte = co.getCompteBD(token);
        Film film = co.getFilmBD(id);
        boolean err = compte.voter(film);

        if (err) {
            // On met à jour le score du film
            vote.addScoreFilm(film);
            co.majFilmBD(film);
            // On met à jour la base de données
            co.majCompteBD(compte.getToken(), compte);
            return compte;
        }
        // return null si l'utilisateur a déjà voté
        else {
            return null;
        }
    }

    /**
     * Retourne le classement des comptes
     * @return le classement des comptes
     */
    @RequestMapping("/getClassement")
    public ArrayList<Compte> getClassement(){
        return score.getClassement();
    }

}
