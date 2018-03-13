package controle;

import metier.Score;
import modele.bd.Connexion;
import modele.classes.Compte;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * Classe permettant de faire l'interface api pour pour les comptes
 * Created by Ulysse Blaineau on 23/02/18.
 */
@RestController
public class CompteController {

    Score score = new Score();
    Connexion co = new Connexion();

    /**
     * Prends un login en entrée, créer un compte puis retourne son token
     * @param compte
     *            Le login du compte que l'on veut créer
     * @return Token du compte
     */
    @RequestMapping(value="/newUser", method = RequestMethod.POST)
    public Compte newUser(@RequestBody Compte compte){

        co.ajoutCompteBD(compte);

        return co.getCompteBD(compte.getToken());
    }

    /**
     * Avoir un utilisateur selon son token
     * @param token
     * @return compte en json
     */
    // Prends un token en entrée pour retourner un compte
    @RequestMapping("/getUser")
    public Compte getUser(@RequestParam(value="token") String token){
        return co.getCompteBD(token);
    }

    /**
     * Méthode renvoyant la liste de toutes les citations existantes
     * @return liste de comptes en json
     */
    @RequestMapping("/getComptes")
    public ArrayList<Compte> getComptes(){
        return co.getComptesBD();
    }

    /**
     * Méthode permettant de mettre à jour un compte
     * @param compte
     * @return le compte mis à jour
     */
    @RequestMapping(value="/majCompte", method = RequestMethod.POST)
    public Compte majCompte(@RequestBody Compte compte){
        co.majCompteBD(compte);

        return co.getCompteBD(compte.getToken());
    }

    @RequestMapping("/getClassementComptes")
    public ArrayList<Compte> getClassementComptes(){
        return score.getClassement();
    }
}
