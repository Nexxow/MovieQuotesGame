package controle;

import modele.classes.Compte;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import static modele.bd.CompteMongo.getCompteBD;
import static modele.bd.CompteMongo.majCompteBD;
import static modele.bd.FilmMongo.getFilmBD;

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
        boolean err = compte.voter(getFilmBD(id));

        if (err) {
            // On met à jour la base de données
            majCompteBD(compte);
            return compte;
        }
        // return null si l'utilisateur a déjà voté
        else {
            return null;
        }
    }
}
