package controle;

import modele.classes.Compte;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Ulysse Blaineau on 23/02/18.
 */
@RestController
public class CompteController {

    // Prends un login en entrée, créer un compte puis retourne son token
    @RequestMapping("/newUser")
    public String newUser(@RequestParam(value="login", defaultValue = "Login") String login){
        Compte compte = new Compte(login, "", "", "", "");
        return compte.getToken();
    }

    // Prends un token en entrée pour retourner un compte
    @RequestMapping("/getUser")
    public String getUser(@RequestParam(value="token") String token){
        return null;
    }
}
