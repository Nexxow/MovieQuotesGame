package controle;

import modele.bd.Connexion;
import modele.classes.Compte;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Classe permettant de faire l'interface api pour pour les comptes
 * Created by Ulysse Blaineau on 23/02/18.
 */
@RestController
public class CompteController {

    Connexion co = new Connexion();

    /**
     * Prends un login en entrée, créer un compte puis retourne son token
     * @param login
     *            Le login du compte que l'on veut créer
     * @return Token du compte
     */
    @RequestMapping("/newUser")
    public Compte newUser(@RequestParam(value="login", defaultValue = "Login") String login, @RequestParam String mdp, @RequestParam String mail, @RequestParam String genrePrefere,
                          @RequestParam String lienAvatar){
        Compte compte = new Compte(login + "", mail + "", genrePrefere + "", mdp + "", lienAvatar + "");

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
     * @param login
     * @param mdp
     * @param mail
     * @param genrePrefere
     * @param lienAvatar
     * @param citationFav
     * @param token
     * @return le compte mis à jour
     */
    @RequestMapping("/majCompte")
    public Compte majCompte(@RequestParam(value="login", defaultValue = "Login") String login, @RequestParam String mdp, @RequestParam String mail, @RequestParam String genrePrefere,
                            @RequestParam String lienAvatar, @RequestParam String citationFav, @RequestParam String token){
        Compte compte = new Compte(login, mail, genrePrefere, mdp, lienAvatar, citationFav, token);
        co.majCompteBD(compte);

        return co.getCompteBD(token);
    }
}
