package controle;

import modele.classes.Compte;
import org.bson.Document;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static modele.bd.CompteMongo.getCompteBD;

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
}
