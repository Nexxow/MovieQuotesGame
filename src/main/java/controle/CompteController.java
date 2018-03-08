package controle;

import modele.classes.Compte;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import static modele.bd.CompteMongo.ajoutCompteBD;
import static modele.bd.CompteMongo.getCompteBD;
import static modele.bd.CompteMongo.getComptesBD;

/**
 * Classe permettant de faire l'interface api pour pour les comptes
 * Created by Ulysse Blaineau on 23/02/18.
 */
@RestController
public class CompteController {

    /**
     * Prends un login en entrée, créer un compte puis retourne son token
     * @param login
     *            Le login du compte que l'on veut créer
     * @return Token du compte
     */
    @RequestMapping("/newUser")
    public String newUser(@RequestParam(value="login", defaultValue = "Login") String login){
        Compte compte = new Compte(login + "", login + "@gmail.com", "slasher", "oui", "oui");

        ajoutCompteBD(compte);

        return compte.getToken();
    }

    /**
     * Avoir un utilisateur selon son token
     * @param token
     * @return compte en json
     */
    // Prends un token en entrée pour retourner un compte
    @RequestMapping("/getUser")
    public Compte getUser(@RequestParam(value="token") String token){
        return getCompteBD(token);
    }

    /**
     * Méthode renvoyant la liste de toutes les citations existantes
     * @return liste de comptes en json
     */
    @RequestMapping("/getComptes")
    public ArrayList<Compte> getComptes(){

        return getComptesBD();
    }
}
