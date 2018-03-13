package controle;

import metier.RecupCitation;
import metier.RecupFilms;
import modele.bd.Connexion;
import modele.classes.Citation;
import modele.classes.Film;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.*;
import java.util.Date;


/**
 * Classe permettant de faire l'interface api pour les films
 * Created by Ulysse Blaineau on 27/02/18.
 */
@RestController
public class TestFilmController {

    Connexion co = new Connexion();

    /**
     * Retourne la liste de tous les films
     * @return les films en json
     */
    @RequestMapping("/testFilms")

    public ArrayList<Film> getFilms(){
        new RecupFilms().ajouterFilmsMongo();
        return co.getFilmsBD();
    }

    @RequestMapping(value ="/testUser", method = RequestMethod.POST)
    @ResponseBody
    public Citation test(@RequestBody String test){
        System.out.println(test);
        return new Citation(test, new Date());
    }

}