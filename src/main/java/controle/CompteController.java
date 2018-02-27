package controle;

import modele.classes.Compte;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import static modele.bd.CompteMongo.getCompteBD;
import static modele.bd.CompteMongo.getComptesBD;

/**
 * Created by Ulysse Blaineau on 23/02/18.
 */
@RestController
public class CompteController {

    // Prends un login en entrée, créer un compte puis retourne son token
    @RequestMapping("/newUser")
    public String newUser(@RequestParam(value="login", defaultValue = "Login") String login){
        Compte compte = new Compte(login, login + "@gmail.com", "slasher", "oui", "oui");
        return compte.getToken();
    }

    // Prends un token en entrée pour retourner un compte
    @RequestMapping("/getUser")
    public Compte getUser(@RequestParam(value="token") String token){
        return getCompteBD(token);
    }

    // Retourne la liste de toutes les citations
    @RequestMapping("/getComptes")
    public ArrayList<Compte> getComptes(){

        return getComptesBD();
    }
}
