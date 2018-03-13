package controle;

import com.google.gson.Gson;
import metier.Score;
import modele.bd.Connexion;
import modele.classes.Compte;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * Classe permettant de faire l'interface api pour pour les comptes
 * Created by Ulysse Blaineau on 23/02/18.
 */

@CrossOrigin
@RequestMapping("/")
@RestController
public class CompteController {

    Score score = new Score();
    Connexion co = new Connexion();

    /**
     * Prends un login en entrée, créer un compte puis retourne son token
     * @param json
     *            Le login du compte que l'on veut créer
     * @return Token du compte
     */
    @RequestMapping(value="/newUser", method = RequestMethod.POST)
    public Compte newUser(@RequestBody String json){
        Compte temp = new Gson().fromJson(json, Compte.class);
        Compte compte = new Compte(temp.getPseudo(), temp.getMail(),
                temp.getGenrePrefere(), temp.getMdp(), temp.getLienAvatar());
        co.ajoutCompteBD(compte);

        return co.getCompteBD(compte.getToken());
    }

    /**
     * Méthode permettant de mettre à jour un compte
     * @param json
     * @return le compte mis à jour
     */
    @RequestMapping(value="/majCompte", method = RequestMethod.POST)
    public Compte majCompte(@RequestBody String json){
        Compte temp = new Gson().fromJson(json, Compte.class);
        Compte compte = new Compte(temp.getPseudo(), temp.getMail(),
                temp.getGenrePrefere(), temp.getMdp(), temp.getLienAvatar());
        co.majCompteBD(compte);

        return co.getCompteBD(compte.getToken());
    }

    /**
     * Méthode permettant de retrouver le classement des comptes
     * @return les comptes, classés
     */
    @RequestMapping("/getClassementComptes")
    public ArrayList<Compte> getClassementComptes(){
        return score.getClassement();
    }

}
