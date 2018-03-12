package metier;

import modele.classes.Citation;
import modele.classes.Compte;
import modele.classes.Film;

import java.util.*;

import static modele.bd.CompteMongo.getComptesBD;
import static modele.bd.CompteMongo.majCompteBD;

/**
 * Created by Ulysse Blaineau on 09/03/18
 * Classe permettant de rajouter au score, et d'avoir le score final de chaque film
 */
public class Vote {

    private static Map<Film, Integer> map = new HashMap<Film, Integer>();

    /**
     *
     * @param film
     * @return
     */
    public static void addScoreFilm(Film film){
        // On regarde si la map contient déjà le film, dans ce cas on rajoute un vote
        if (map.containsKey(film)) {
            map.replace(film, map.get(film) + 1);
        }
        // sinon on l'ajoute avec un vote
        else {
            map.put(film, 1);
        }
    }

    public static int getScore(Film film){
        if (map.containsKey(film)){
            return map.get(film);
        }
        else {
            return 0;
        }
    }

    public static Film getPremierFilm(){
        Map.Entry<Film, Integer> maxEntry = null;
        for (Map.Entry<Film, Integer> entry : map.entrySet()){
            if (maxEntry == null || entry.getValue() > maxEntry.getValue()){
                maxEntry = entry;
            }
        }
        if (maxEntry != null) {
            Film premierFilm = maxEntry.getKey();
            return premierFilm;
        }
        else {
            return null;
        }
    }

    public void reinitialiserVotes(){
        ArrayList<Compte> comptes = getComptesBD();
        for (Compte compte : comptes){
            compte.reinitialiseVote();
            majCompteBD(compte);
        }
    }

    public static Citation lienFilmCitation(Citation citation){
        if (citation.estCitationJour()){
            citation.setFilm(getPremierFilm());
        }
        return citation;
    }

}
