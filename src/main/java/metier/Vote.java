package metier;

import modele.classes.Citation;
import modele.classes.Compte;
import modele.classes.Film;

import java.util.*;

import static modele.bd.CompteMongo.getComptesBD;
import static modele.bd.CompteMongo.majCompteBD;
import static modele.bd.FilmMongo.getFilmsBD;

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
        film.setScore(film.getScore() + 1);
    }

    public static Film getPremierFilm(){
        ArrayList<Film> films = getFilmsBD();

        Collections.sort(films, new Comparator<Film>() {
            @Override
            public int compare(Film o1, Film o2) {
                return o1.compareTo(o2);
            }
        });
        return films.get(0);
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
