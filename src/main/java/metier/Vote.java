package metier;

import modele.classes.Citation;
import modele.classes.Compte;
import modele.classes.Film;

import java.util.*;

import static modele.bd.CompteMongo.getComptesBD;
import static modele.bd.CompteMongo.majCompteBD;
import static modele.bd.FilmMongo.getFilmsBD;
import static modele.bd.FilmMongo.majFilmBD;

/**
 * Created by Ulysse Blaineau on 09/03/18
 * Classe permettant de rajouter au score, et d'avoir le score final de chaque film
 */
public class Vote {

    private static Map<Film, Integer> map = new HashMap<Film, Integer>();

    /**
     * Fonction permettant de rajouter un point au score du film
     * @param film
     * @return
     */
    public static void addScoreFilm(Film film){
        film.setScore(film.getScore() + 1);
    }

    /**
     * Fonction permettant de récuperer le premier film du classement
     * @return
     */
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

    /**
     * Fonction permettant de réinitialiser les votes des comptes et donc le score des films
     */
    public static void reinitialiserVotes(){
        ArrayList<Compte> comptes = getComptesBD();
        for (Compte compte : comptes){
            compte.reinitialiseVote();
            majCompteBD(compte);
        }

        ArrayList<Film> films = getFilmsBD();
        for (Film film : films){
            film.setScore(0);
            majFilmBD(film);
        }

    }

    /**
     * Fonction mettant en lien une citation (du jour) avec le film le plus voté
     * @param citation
     * @return la citation à jour
     */
    public static Citation lienFilmCitation(Citation citation){
        if (citation.estCitationJour()){
            citation.setFilm(getPremierFilm());
        }

        return citation;
    }

}
