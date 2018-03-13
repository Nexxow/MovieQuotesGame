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
     * @return compte
     */
    @RequestMapping(value="/newUser", method = RequestMethod.POST)
    public Compte newUser(@RequestBody String json){
        System.out.println(json);
        Compte temp = new Gson().fromJson(json, Compte.class);
        Compte compte = new Compte(temp.getPseudo(), temp.getMail(), temp.getGenrePrefere(), temp.getMdp(), temp.getLienAvatar());

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
     * @param pseudo
     * @param mail
     * @param genrePrefere
     * @param mdp
     * @param lienAvatar
     * @return compte
     */
    @RequestMapping(value="/majCompte", method = RequestMethod.POST)
    public Compte majCompte(@RequestParam("pseudo") String pseudo, @RequestParam("mail") String mail,
                            @RequestParam("genrePrefere") String genrePrefere,
                            @RequestParam("mdp") String mdp, @RequestParam("lienAvatar") String lienAvatar){

        Compte compte = new Compte(pseudo, mail, genrePrefere, mdp, lienAvatar);

        co.majCompteBD(compte);

        return co.getCompteBD(compte.getToken());
    }

    @RequestMapping("/getClassementComptes")
    public ArrayList<Compte> getClassementComptes(){
        return score.getClassement();
    }

    // Prends un token en entrée pour retourner un compte
    @RequestMapping("/newUser2")
    public Compte newUser2(){
        Compte c = new Compte("login", "mail", "genre", "mdp", "lien");
        co.ajoutCompteBD(c);
        return c;
    }
}
